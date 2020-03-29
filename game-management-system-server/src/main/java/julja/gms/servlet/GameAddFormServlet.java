package julja.gms.servlet;

import java.io.PrintStream;
import java.util.Map;
import org.springframework.stereotype.Component;
import julja.gms.service.GameService;
import julja.util.RequestMapping;

@Component
public class GameAddFormServlet {

  GameService gameService;

  public GameAddFormServlet(GameService gameService) {
    this.gameService = gameService;
  }

  @RequestMapping("/game/addForm")
  public void service(Map<String, String> params, PrintStream out) throws Exception {

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>게임 등록</title>");
    out.println("</head>");

    out.println("<body>");
    out.println("<h1>게임 등록</h1>");
    out.println("<form action='/game/add'>");
    out.println("게임명: <input name='gameName' type='text'><br>");
    out.println("제작사: <input name='gameProduction' type='text'><br>");
    out.println("발매일: <input name='gameDate' type='Date'><br>");
    out.println("플랫폼: <input name='gamePlatform' type='text'><br>");
    out.println("장르: <input name='gameGenre' type='text'><br>");
    out.println("사진: <input name='photo' type='text'><br>");
    out.println("작화: <input name='gameIllust' type='text'><br>");
    out.println("음성: <input name='gameVoice' type='text'><br>");
    out.println("<button>제출</button>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }

}
