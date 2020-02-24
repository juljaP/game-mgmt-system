package julja.gms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import julja.gms.dao.PhotoBoardDao;
import julja.gms.domain.Game;
import julja.gms.domain.PhotoBoard;

public class PhotoBoardAddServlet implements Servlet {

  PhotoBoardDao photoBoardDao;

  public PhotoBoardAddServlet(PhotoBoardDao photoBoardDao) {
    this.photoBoardDao = photoBoardDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    PhotoBoard photoBoard = new PhotoBoard();
    Game game = new Game();

    out.println("제목 : \n!{}!");
    photoBoard.setTitle(in.nextLine());
    out.println("게임 번호 : \n!{}!");
    game.setNo(Integer.parseInt(in.nextLine()));
    photoBoard.setGame(game);

    if (photoBoardDao.insert(photoBoard) > 0) {
      out.println("사진을 저장했습니다.");
    } else {
      out.println("게임 번호가 유효하지 않습니다.");
    }
  }

}
