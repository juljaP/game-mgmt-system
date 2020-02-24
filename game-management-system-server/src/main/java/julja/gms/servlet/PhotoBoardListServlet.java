package julja.gms.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import julja.gms.dao.PhotoBoardDao;
import julja.gms.domain.PhotoBoard;

public class PhotoBoardListServlet implements Servlet {

  PhotoBoardDao photoBoardDao;

  public PhotoBoardListServlet(PhotoBoardDao photoBoardDao) {
    this.photoBoardDao = photoBoardDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    out.println("게임번호? \n!{}!");
    out.flush();

    int no = Integer.parseInt(in.nextLine());
    PhotoBoard game = photoBoardDao.findByGameNo(no);
    out.println("게임명 : " + game.getGame().getGameName());

    List<PhotoBoard> photoboards = photoBoardDao.findAllByNo(no);

    for (PhotoBoard photoBoard : photoboards) {
      out.printf("[%d] %s | %s | %d \n", photoBoard.getNo(), photoBoard.getTitle(),
          photoBoard.getCreadtedDate(), photoBoard.getHits());
    }
  }

}
