package julja.gms.handler;

import java.util.List;
import julja.gms.dao.GameDao;
import julja.gms.domain.Game;

public class GameListCommand implements Command {

  GameDao gameDao;

  public GameListCommand(GameDao gameDao) {
    this.gameDao = gameDao;
  }

  @Override
  public void execute() {

    try {
      List<Game> games = gameDao.findAll();

      for (Game g : games) {
        System.out.printf("[%d] %s | %s | %s | %s\n", g.getNo(), g.getGameName(),
            g.getGamePlatform(), g.getGameGenre(), g.getGameDate());
      }
    } catch (Exception e) {
      System.out.println("통신 오류 발생.");
    }
  }
}

