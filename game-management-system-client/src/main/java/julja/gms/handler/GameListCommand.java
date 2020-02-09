package julja.gms.handler;

import java.util.List;
import julja.gms.dao.proxy.GameDaoProxy;
import julja.gms.domain.Game;

public class GameListCommand implements Command {

  GameDaoProxy gameDao;

  public GameListCommand(GameDaoProxy gameDao) {
    this.gameDao = gameDao;
  }

  @Override
  public void execute() {

    try {
      List<Game> games = gameDao.findAll();

      for (Game g : games) {
        System.out.printf("[%d] %s | %s | %s | %s\n", g.getNo(), g.getGameName(),
            g.getGameProduction(), g.getGameDate(), g.getGameGenre());
      }
    } catch (Exception e) {
      System.out.println("통신 오류 발생.");
    }
  }
}

