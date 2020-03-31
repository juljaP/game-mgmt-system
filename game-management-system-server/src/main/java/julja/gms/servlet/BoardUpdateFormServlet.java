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
import julja.gms.domain.Board;
import julja.gms.service.BoardService;

@WebServlet("/board/updateForm")
public class BoardUpdateFormServlet extends GenericServlet {
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

      BoardService boardService = iocContainer.getBean(BoardService.class);

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>게시글 변경</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>게시글 변경</h1>");

      Board board = boardService.get(Integer.parseInt(req.getParameter("no")));
      out.println("<form action='update'>");
      out.printf("번호: <input name='no' readonly type='text' value='%d'><br>\n", board.getNo());
      out.printf("제목: <input name='bbsName' type='text' value='%s'><br>\n", board.getBbsName());
      out.println("내용: <br>");
      out.printf("<textarea name='bbsText' rows='5' cols='60'>%s</textarea><br>\n",
          board.getBbsText());
      out.println("<button>제출</button>");
      out.println("</form>");
      out.println("</body>");
      out.println("</html>");
    } catch (Exception e) {
      new ServletException(e);
    }
  }

}
