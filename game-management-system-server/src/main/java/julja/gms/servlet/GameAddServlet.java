package julja.gms.servlet;

import java.io.PrintStream;
import java.sql.Date;
import java.util.Map;
import org.springframework.stereotype.Component;
import julja.gms.domain.Game;
import julja.gms.service.GameService;
import julja.util.RequestMapping;

@Component
public class GameAddServlet {

  GameService gameService;

  public GameAddServlet(GameService gameService) {
    this.gameService = gameService;
  }

  @RequestMapping("/game/add")
  public void service(Map<String, String> params, PrintStream out) throws Exception {

    Game game = new Game();
    game.setGameName(params.get("gameName"));
    game.setGameProduction(params.get("gameProduction"));
    game.setGameDate(Date.valueOf(params.get("gameDate")));
    game.setGamePlatform(params.get("gamePlatform"));
    game.setGameGenre(params.get("gameGenre"));
    game.setPhoto(params.get("photo"));
    game.setGameIllust(params.get("gameIllust"));
    game.setGameVoice(params.get("gameVoice"));

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh' content='2;url=/game/list'>");
    out.println("<title>게임 등록</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>게임 등록</h1>");
    if (gameService.insert(game) > 0) {
      out.println("<p>게임 정보를 입력하였습니다.</p>");
    } else {
      out.println("<p>게임 정보 입력을 실패했습니다.</p>");
    }
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }

}
