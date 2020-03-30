package julja.gms.servlet;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import julja.gms.domain.Game;
import julja.gms.service.GameService;
import julja.util.RequestMapping;

@Component
public class GameSearchServlet {

  GameService gameService;

  public GameSearchServlet(GameService gameService) {
    this.gameService = gameService;
  }

  @RequestMapping("/game/search")
  public void service(Map<String, String> params, PrintWriter out) throws Exception {

    Map<String, Object> params1 = new HashMap<>();

    String keyword = params.get("gameName");
    if (keyword.length() > 0) {
      params1.put("gameName", keyword);
    }
    keyword = params.get("gameProduction");
    if (keyword.length() > 0) {
      params1.put("gameProduction", keyword);
    }
    keyword = params.get("gamePlatform");
    if (keyword.length() > 0) {
      params1.put("gamePlatform", keyword);
    }
    keyword = params.get("gameGenre");
    if (keyword.length() > 0) {
      params1.put("gameGenre", keyword);
    }
    keyword = params.get("gameIllust");
    if (keyword.length() > 0) {
      params1.put("gameIllust", keyword);
    }
    keyword = params.get("gameVoice");
    if (keyword.length() > 0) {
      params1.put("gameVoice", keyword);
    }
    List<Game> games = gameService.findByKeyword(params1);

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("   <meta charset='UTF-8'>");
    out.println("   <title>게임 검색 결과</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("   <h1>게임 검색 결과</h1>");

    out.println("<table border='1'>");
    out.println("<tr>");
    out.println("<th>번호</th>");
    out.println("<th>게임명</th>");
    out.println("<th>플랫폼</th>");
    out.println("<th>장르</th>");
    out.println("<th>발매일</th>");
    out.println("</tr>");
    for (Game g : games) {
      out.printf(
          "<tr><td>%d</td> <td><a href='/game/detail?no=%d'>%s</a></td> <td>%s</td> <td>%s</td> <td>%s</td></tr>\n",
          g.getNo(), g.getNo(), g.getGameName(), g.getGamePlatform(), g.getGameGenre(),
          g.getGameDate());
    }
    out.println("</body>");
    out.println("</html>");
  }

}
