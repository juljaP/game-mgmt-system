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

@WebServlet("/board/update")
public class BoardUpdateServlet extends GenericServlet {
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
      out.println("<meta http-equiv='refresh' content='2;url=list'>");
      out.println("<title>게시글 변경</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>게시글 변경 결과</h1>");

      Board board = new Board();
      board.setNo(Integer.parseInt(req.getParameter("no")));
      board.setBbsName(req.getParameter("bbsName"));
      board.setBbsText(req.getParameter("bbsText"));

      if (boardService.update(board) > 0) {
        out.println("<p>게시글을 변경하였습니다.</p>");
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
