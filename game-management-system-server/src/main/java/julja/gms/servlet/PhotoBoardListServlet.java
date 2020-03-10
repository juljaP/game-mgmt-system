package julja.gms.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import julja.gms.dao.GameDao;
import julja.gms.dao.PhotoBoardDao;
import julja.gms.domain.Game;
import julja.gms.domain.PhotoBoard;
import julja.util.Prompt;

public class PhotoBoardListServlet implements Servlet {

  PhotoBoardDao photoBoardDao;
  GameDao gameDao;

  public PhotoBoardListServlet(PhotoBoardDao photoBoardDao, GameDao gameDao) {
    this.photoBoardDao = photoBoardDao;
    this.gameDao = gameDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = Prompt.getInt(in, out, "게임 번호? ");
    Game game = gameDao.findByNo(no);
    if (game == null) {
      out.println("해당하는 번호의 게임이 존재하지 않습니다.");
      return;
    }

    out.println("게임명 : " + game.getGameName());

    List<PhotoBoard> photoboards = photoBoardDao.findAllByNo(no);

    for (PhotoBoard photoBoard : photoboards) {
      out.printf("[%d] %s | %s | %d \n", photoBoard.getNo(), photoBoard.getTitle(),
          photoBoard.getCreadtedDate(), photoBoard.getHits());
    }
  }

}
