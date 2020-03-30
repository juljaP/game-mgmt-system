package julja.gms.servlet;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import julja.gms.domain.Game;
import julja.gms.service.GameService;
import julja.util.RequestMapping;

@Component
public class GameListServlet {

  GameService gameService;

  public GameListServlet(GameService gameService) {
    this.gameService = gameService;
  }

  @RequestMapping("/game/list")
  public void service(Map<String, String> params, PrintWriter out) throws Exception {
    List<Game> games = gameService.findAll();


    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("   <meta charset='UTF-8'>");
    out.println("   <title>게임 목록</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("   <h1>게임</h1>");
    out.println("   <a href='/game/addForm'>새 게임</a><br>");
    out.println("   <table border='1'>");
    out.println("       <tr>");
    out.println("           <th>번호</th>");
    out.println("            <th>게임명</th>");
    out.println("           <th>플랫폼</th>");
    out.println("           <th>장르</th>");
    out.println("           <th>발매일</th>");
    out.println("       </tr>");

    for (Game g : games) {
      out.printf(
          "<tr><td>%d</td> <td><a href='/game/detail?no=%d'>%s</a></td> <td>%s</td> <td>%s</td> <td>%s</td></tr>\n",
          g.getNo(), g.getNo(), g.getGameName(), g.getGamePlatform(), g.getGameGenre(),
          g.getGameDate());
    }
    out.println("   </table>");

    out.println("<hr>");
    out.println("<form action='/game/search'>");
    out.println("<p>게임명 : <input name='gameName' type='text'/><br>");
    out.println("제작사 : <input name='gameProduction' type='text'/><br>");
    out.println("플랫폼 : <input name='gamePlatform' type='text'/><br>");
    out.println("장르 : <input name='gameGenre' type='text'/><br>");
    out.println("일러스트레이터 : <input name='gameIllust' type='text'/><br>");
    out.println("성우 : <input name='gameVoice' type='text'/></p>");
    out.println("<button>검색</button></p>");
    out.println("</form>");

    out.println("</body>");
    out.println("</html>");
  }

}
