package julja.gms.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import julja.gms.dao.PhotoBoardDao;
import julja.gms.dao.PhotoFileDao;
import julja.gms.domain.PhotoBoard;
import julja.gms.domain.PhotoFile;
import julja.util.Prompt;

public class PhotoBoardDetailServlet implements Servlet {

  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;

  public PhotoBoardDetailServlet(PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = Prompt.getInt(in, out, "번호? ");

    PhotoBoard photoBoard = photoBoardDao.findByNo(no);

    if (photoBoard != null) {
      out.println("제목 : " + photoBoard.getTitle());
      out.println("작성일 : " + photoBoard.getCreadtedDate());
      out.println("조회수 : " + photoBoard.getHits());
      out.println("게임명 : " + photoBoard.getGame().getGameName());

      out.println("사진 파일 : ");
      List<PhotoFile> photoFiles = photoFileDao.findAll(no);

      for (PhotoFile photoFile : photoFiles) {
        out.printf(">%s\n", photoFile.getFilepath());
        out.flush();
      }
    } else {
      out.println("해당 번호의 사진 게시글이 없습니다.");
    }
  }

}
