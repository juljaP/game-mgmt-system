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

@WebServlet("/board/detail")
public class BoardDetailServlet extends GenericServlet {
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
      Board b = boardService.get(Integer.parseInt(req.getParameter("no")));

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>게시글 상세정보</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>게시글 상세정보</h1>");

      if (b != null) {
        out.printf("번호: %d<br>\n", b.getNo());
        out.printf("제목: %s<br>\n", b.getBbsName());
        out.printf("내용: %s<br>\n", b.getBbsText());
        out.printf("등록일: %s<br>\n", b.getToday());
        out.printf("조회수: %d<br>\n", b.getBbsHits());
        out.printf("<p><a href='delete?no=%d'>삭제</a>\n", b.getNo());
        out.printf("<a href='updateForm?no=%d'>변경</a></p>\n", b.getNo());
      } else {
        out.println("<p>해당 번호의 게시물이 없습니다.</p>");
      }
      out.println("</body>");
      out.println("</html>");
    } catch (Exception e) {
      new ServletException(e);
    }
  }
}
