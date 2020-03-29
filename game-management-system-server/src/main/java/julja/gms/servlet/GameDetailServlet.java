package julja.gms.servlet;

import java.io.PrintStream;
import java.util.Map;
import org.springframework.stereotype.Component;
import julja.gms.domain.Game;
import julja.gms.service.GameService;
import julja.util.RequestMapping;

@Component
public class GameDetailServlet {

  GameService gameService;

  public GameDetailServlet(GameService gameService) {
    this.gameService = gameService;
  }

  @RequestMapping("/game/detail")
  public void service(Map<String, String> params, PrintStream out) throws Exception {

    int no = Integer.parseInt(params.get("no"));
    Game g = gameService.findByNo(no);

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("   <meta charset='UTF-8'>");
    out.println("   <title>게임 상세정보</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("   <h1>게임 상세정보</h1>");

    if (g != null) {
      out.println("<form action='/game/update'>");
      out.printf("<p>게임번호 : <input name='no' readonly type='text' value='%s'/><br>\n", g.getNo());
      out.printf("게임명 : <input name='gameName' type='text' value='%s'/><br>\n", g.getGameName());
      out.printf("제작사 : <input name='gameProduction' type='text' value='%s'/><br>\n",
          g.getGameProduction());
      out.printf("플랫폼 : <input name='gamePlatform' type='text' value='%s'/><br>\n",
          g.getGamePlatform());
      out.printf("장르 : <input name='gameGenre' type='text' value='%s'/><br>\n", g.getGameGenre());
      out.printf("발매일 : <input name='gameDate' type='Date' value='%s'/><br>\n", g.getGameDate());
      out.printf("작화 : <input name='gameIllust' type='text' value='%s'/><br>\n", g.getGameIllust());
      out.printf("음성 : <input name='gameVoice' type='text' value='%s'/><br>\n", g.getGameVoice());
      out.printf("사진 : <input name='photo' type='text' value='%s'/></p>\n", g.getPhoto());
      out.print("<button>변경</button>");
      out.printf("<a href='/game/delete?no=%d'>삭제</a>\n", g.getNo());
      out.println("</form>");
    } else {
      out.println("<p>해당 번호의 게임이 없습니다.</p>");
    }
    out.println("</body>");
    out.println("</html>");

  }

}
