package julja.gms.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import julja.gms.dao.GameDao;
import julja.gms.domain.Game;

public class GameListServlet implements Servlet {

  GameDao gameDao;

  public GameListServlet(GameDao gameDao) {
    this.gameDao = gameDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    List<Game> games = gameDao.findAll();

    for (Game g : games) {
      out.printf("[%d] %s | %s | %s | %s\n", g.getNo(), g.getGameName(), g.getGamePlatform(),
          g.getGameGenre(), g.getGameDate());
    }
  }

}
