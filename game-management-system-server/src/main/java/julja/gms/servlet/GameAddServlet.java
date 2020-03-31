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

@WebServlet("/game/add")
public class GameAddServlet extends GenericServlet {
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

      Game game = new Game();
      game.setGameName(req.getParameter("gameName"));
      game.setGameProduction(req.getParameter("gameProduction"));
      game.setGameDate(Date.valueOf(req.getParameter("gameDate")));
      game.setGamePlatform(req.getParameter("gamePlatform"));
      game.setGameGenre(req.getParameter("gameGenre"));
      game.setPhoto(req.getParameter("photo"));
      game.setGameIllust(req.getParameter("gameIllust"));
      game.setGameVoice(req.getParameter("gameVoice"));

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<meta http-equiv='refresh' content='2;url=list'>");
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
    } catch (Exception e) {
      new ServletException(e);
    }
  }
}
