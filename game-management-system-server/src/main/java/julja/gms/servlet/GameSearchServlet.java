package julja.gms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import org.springframework.context.ApplicationContext;
import julja.gms.domain.Game;
import julja.gms.service.GameService;

@WebServlet("/game/search")
public class GameSearchServlet extends GenericServlet {
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
      Map<String, Object> params1 = new HashMap<>();

      String keyword = req.getParameter("gameName");
      if (keyword.length() > 0) {
        params1.put("gameName", keyword);
      }
      keyword = req.getParameter("gameProduction");
      if (keyword.length() > 0) {
        params1.put("gameProduction", keyword);
      }
      keyword = req.getParameter("gamePlatform");
      if (keyword.length() > 0) {
        params1.put("gamePlatform", keyword);
      }
      keyword = req.getParameter("gameGenre");
      if (keyword.length() > 0) {
        params1.put("gameGenre", keyword);
      }
      keyword = req.getParameter("gameIllust");
      if (keyword.length() > 0) {
        params1.put("gameIllust", keyword);
      }
      keyword = req.getParameter("gameVoice");
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
            "<tr><td>%d</td> <td><a href='detail?no=%d'>%s</a></td> <td>%s</td> <td>%s</td> <td>%s</td></tr>\n",
            g.getNo(), g.getNo(), g.getGameName(), g.getGamePlatform(), g.getGameGenre(),
            g.getGameDate());
      }
      out.println("</body>");
      out.println("</html>");
    } catch (Exception e) {
      new ServletException(e);
    }
  }
}
