package julja.gms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import org.springframework.context.ApplicationContext;
import julja.gms.domain.Game;
import julja.gms.service.GameService;

@WebServlet("/game/update")
public class GameUpdateServlet extends GenericServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {
    try {
      res.setContentType("text/html;charset=UTF-8");
      PrintWriter out = res.getWriter();

      ServletContext servletContext = req.getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");

      GameService gameService = iocContainer.getBean(GameService.class);

      int no = Integer.parseInt(req.getParameter("no"));

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("   <meta charset='UTF-8'>");
      out.println("<meta http-equiv='refresh' content='2;url=list'>");
      out.println("   <title>게임정보 변경</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("   <h1>게임정보 변경</h1>");

      Game game = new Game();
      game.setNo(no);
      game.setGameName(req.getParameter("gameName"));
      game.setGameProduction(req.getParameter("gameProduction"));
      game.setGameDate(Date.valueOf(req.getParameter("gameDate")));
      game.setGamePlatform(req.getParameter("gamePlatform"));
      game.setGameGenre(req.getParameter("gameGenre"));
      game.setGameIllust(req.getParameter("gameIllust"));
      game.setGameVoice(req.getParameter("gameVoice"));
      game.setPhoto(req.getParameter("photo"));

      if (gameService.update(game) > 0) {
        out.println("<p>게임 정보를 변경하였습니다.</p>");
      } else {
        out.println("<p>데이터 저장 실패.</p>");
      }
      out.println("</body>");
      out.println("</html>");
    } catch (Exception e) {
      new ServletException(e);
    }
  }
}
