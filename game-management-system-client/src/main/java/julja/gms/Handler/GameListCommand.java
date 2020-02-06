package julja.gms.Handler;

import java.util.Iterator;
import java.util.List;
import julja.gms.domain.Game;

public class GameListCommand implements Command {

  List<Game> gameList;

  public GameListCommand(List<Game> list) {
    gameList = list;
  }

  @Override
  public void execute() {
    Iterator<Game> iterator = gameList.iterator();
    while (iterator.hasNext()) {
      Game g = iterator.next();
      System.out.printf("[%d] %s | %s | %s | %s\n", g.getGameNum(), g.getGameName(),
          g.getGameProduction(), g.getGameDate(), g.getGameGenre());
    }
  }

}

