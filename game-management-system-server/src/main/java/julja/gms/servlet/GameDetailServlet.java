package julja.gms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import julja.gms.domain.Game;
import julja.gms.service.GameService;
import julja.util.Prompt;

@Component("/game/detail")
public class GameDetailServlet implements Servlet {

  GameService gameService;

  public GameDetailServlet(GameService gameService) {
    this.gameService = gameService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    int no = Prompt.getInt(in, out, "번호? ");

    Game g = gameService.findByNo(no);

    if (g != null) {
      out.printf("게임명 : %s\n", g.getGameName());
      out.printf("제작사 : %s\n", g.getGameProduction());
      out.printf("플랫폼 : %s\n", g.getGamePlatform());
      out.printf("장르 : %s\n", g.getGameGenre());
      out.printf("발매일 : %s\n", g.getGameDate());
      out.printf("작화 : %s\n", g.getGameIllust());
      out.printf("음성 : %s\n", g.getGameVoice());
      out.printf("사진 : %s\n", g.getPhoto());
    } else {
      out.println("해당 번호의 게임이 없습니다.");
    }

  }

}
