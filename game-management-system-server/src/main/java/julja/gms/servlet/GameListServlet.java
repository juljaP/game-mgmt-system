package julja.gms.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import julja.gms.domain.Game;
import julja.gms.service.GameService;

public class GameListServlet implements Servlet {

  GameService gameService;

  public GameListServlet(GameService gameService) {
    this.gameService = gameService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    List<Game> games = gameService.findAll();

    for (Game g : games) {
      out.printf("[%d] %s | %s | %s | %s\n", g.getNo(), g.getGameName(), g.getGamePlatform(),
          g.getGameGenre(), g.getGameDate());
    }
  }

}
