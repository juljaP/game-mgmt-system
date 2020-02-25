package julja.gms.servlet;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import julja.gms.dao.GameDao;
import julja.gms.dao.PhotoBoardDao;
import julja.gms.dao.PhotoFileDao;
import julja.gms.domain.Game;
import julja.gms.domain.PhotoBoard;
import julja.gms.domain.PhotoFile;

public class PhotoBoardAddServlet implements Servlet {

  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;
  GameDao gameDao;

  public PhotoBoardAddServlet(PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao,
      GameDao gameDao) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
    this.gameDao = gameDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    PhotoBoard photoBoard = new PhotoBoard();

    out.println("제목 : \n!{}!");
    out.flush();
    photoBoard.setTitle(in.nextLine());

    out.println("게임 번호 : \n!{}!");
    out.flush();
    int no = Integer.parseInt(in.nextLine());

    Game game = gameDao.findByNo(no);

    if (game == null) {
      out.println("게임 번호가 유효하지 않습니다.");
      return;
    }

    photoBoard.setGame(game);

    if (photoBoardDao.insert(photoBoard) > 0) {

      List<PhotoFile> photoFiles = new ArrayList<>();

      out.println("최소 한 개의 사진 파일을 등록해야 합니다.");
      out.println("파일명 입력 없이 그냥 엔터를 치면 파일 추가를 마칩니다.");

      while (true) {
        out.println("사진 파일? \n!{}!");
        out.flush();
        String filepath = in.nextLine();

        if (filepath.length() == 0) {
          if (photoFiles.size() > 0) {
            break;
          } else {
            out.println("최소 한 개의 사진 파일을 등록해야 합니다.");
            continue;
          }
        }
        photoFiles.add(new PhotoFile(filepath, photoBoard.getNo()));
      }

      for (PhotoFile photoFile : photoFiles) {
        photoFileDao.insert(photoFile);
      }

      if (photoFiles.size() > 0) {
        out.println("새 사진 게시글을 등록했습니다.");
      } else {
        out.println("새 사진 게시글 등록에 실패했습니다.");
      }
    }
  }
}
