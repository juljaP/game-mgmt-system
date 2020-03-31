package julja.gms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import org.springframework.context.ApplicationContext;
import julja.gms.domain.Game;
import julja.gms.service.GameService;

@WebServlet("/photoBoard/addForm")
public class PhotoBoardAddFormServlet extends GenericServlet {
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

      Game game = gameService.findByNo(Integer.parseInt(req.getParameter("gameNo")));

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>사진 게시글 등록</title>");
      out.println("</head>");

      out.println("<body>");
      out.println("<h1>사진 입력</h1>");
      out.println("<form action='add'>");
      out.printf("게임번호: <input name='gameNo' readonly type='number' value='%d'><br>", game.getNo());
      out.printf("게임명: <input name='gameName' readonly type='text' value='%s'><br>",
          game.getGameName());
      out.printf("제목: <br>");
      out.println("<textarea name='title' rows='5' cols='60'></textarea><br>");

      out.println("<hr>");
      out.println("사진 파일 <br>");
      out.println("사진: <input name='photo1' type='file'><br>");
      out.println("사진: <input name='photo2' type='file'><br>");
      out.println("사진: <input name='photo3' type='file'><br>");
      out.println("사진: <input name='photo4' type='file'><br>");
      out.println("사진: <input name='photo5' type='file'><br>");

      out.println("<p><button>변경</button>");
      out.println("</form>");

      out.println("</body>");
      out.println("</html>");
    } catch (Exception e) {
      new ServletException(e);
    }
  }
}
