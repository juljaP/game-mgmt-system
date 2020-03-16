package julja.gms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import julja.gms.service.GameService;
import julja.util.Component;
import julja.util.Prompt;

@Component("/game/delete")
public class GameDeleteServlet implements Servlet {

  GameService gameService;

  public GameDeleteServlet(GameService gameService) {
    this.gameService = gameService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    int no = Prompt.getInt(in, out, "번호? ");

    if (gameService.delete(no) > 0) {
      out.println("해당 품번의 게임을 삭제하였습니다.");
    } else {
      out.println("해당 품번의 게임이 없습니다.");
    }
  }

}
