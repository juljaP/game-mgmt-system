package julja.gms.servlet;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import julja.gms.dao.PhotoBoardDao;
import julja.gms.dao.PhotoFileDao;
import julja.gms.domain.PhotoBoard;
import julja.gms.domain.PhotoFile;
import julja.sql.DataSource;
import julja.sql.PlatformTransactionManager;
import julja.util.Prompt;

public class PhotoBoardUpdateServlet implements Servlet {

  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;
  DataSource conFactory;
  PlatformTransactionManager txManager;

  public PhotoBoardUpdateServlet(PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao,
      DataSource conFactory, PlatformTransactionManager txManager) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
    this.conFactory = conFactory;
    this.txManager = txManager;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    int no = Prompt.getInt(in, out, "번호? ");

    PhotoBoard old = photoBoardDao.findByNo(no);

    if (old == null) {
      out.println("해당 번호의 사진 게시글이 없습니다.");
      return;
    }

    PhotoBoard photoBoard = new PhotoBoard();
    photoBoard.setNo(no);
    photoBoard.setTitle(
        Prompt.getString(in, out, String.format("제목(%s) : ", old.getTitle()), old.getTitle()));

    txManager.beginTransaction();

    try {
      if (photoBoardDao.update(photoBoard) == 0) {
        throw new Exception("사진 게시글 변경에 실패했습니다.");
      }
      printFiles(out, no);

      out.println("사진은 일부만 변경할 수 없습니다.");
      out.println("전체를 새로 등록해야 합니다.");
      String response = Prompt.getString(in, out, "사진을 변경하시겠습니까?(Y/N)");

      if (response.equalsIgnoreCase("Y")) {
        photoFileDao.deleteAll(no);

        List<PhotoFile> photoFiles = uploadFiles(in, out);

        for (PhotoFile photoFile : photoFiles) {
          photoFile.setBoardNo(photoBoard.getNo());
          photoFileDao.insert(photoFile);
        }
        txManager.commit();
        out.println("사진 게시글을 변경했습니다.");
      }
    } catch (Exception e) {
      txManager.rollback();
      out.println(e.getMessage());
    }

  }

  private void printFiles(PrintStream out, int no) throws Exception {
    out.println("사진 파일 : ");
    List<PhotoFile> oldPhotoFiles = photoFileDao.findAll(no);

    for (PhotoFile photoFile : oldPhotoFiles) {
      out.printf(">%s\n", photoFile.getFilepath());
    }
  }

  private List<PhotoFile> uploadFiles(Scanner in, PrintStream out) {

    List<PhotoFile> photoFiles = new ArrayList<>();

    out.println("최소 한 개의 사진 파일을 등록해야 합니다.");
    out.println("파일명 입력 없이 그냥 엔터를 치면 파일 추가를 마칩니다.");

    while (true) {
      String filepath = Prompt.getString(in, out, "사진 파일? ");

      if (filepath.length() == 0) {
        if (photoFiles.size() > 0) {
          break;
        } else {
          out.println("최소 한 개의 사진 파일을 등록해야 합니다.");
          continue;
        }
      }
      photoFiles.add(new PhotoFile().setFilepath(filepath));
    }
    return photoFiles;
  }

}
