package julja.gms.servlet;

import java.io.PrintStream;
import java.sql.Date;
import java.util.Map;
import org.springframework.stereotype.Component;
import julja.gms.domain.Game;
import julja.gms.service.GameService;
import julja.util.RequestMapping;

@Component
public class GameUpdateServlet {

  GameService gameService;

  public GameUpdateServlet(GameService gameService) {
    this.gameService = gameService;
  }

  @RequestMapping("/game/update")
  public void service(Map<String, String> params, PrintStream out) throws Exception {

    int no = Integer.parseInt(params.get("no"));

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("   <meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh' content='2;url=/game/list'>");
    out.println("   <title>게임정보 변경</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("   <h1>게임정보 변경</h1>");

    Game game = new Game();
    game.setNo(no);
    game.setGameName(params.get("gameName"));
    game.setGameProduction(params.get("gameProduction"));
    game.setGameDate(Date.valueOf(params.get("gameDate")));
    game.setGamePlatform(params.get("gamePlatform"));
    game.setGameGenre(params.get("gameGenre"));
    game.setGameIllust(params.get("gameIllust"));
    game.setGameVoice(params.get("gameVoice"));
    game.setPhoto(params.get("photo"));

    if (gameService.update(game) > 0) {
      out.println("<p>게임 정보를 변경하였습니다.</p>");
    } else {
      out.println("<p>데이터 저장 실패.</p>");
    }
    out.println("</body>");
    out.println("</html>");
  }

}
