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
import julja.gms.service.PhotoBoardService;

@WebServlet("/photoBoard/delete")
public class PhotoBoardDeleteServlet extends GenericServlet {
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
      PhotoBoardService photoBoardService = iocContainer.getBean(PhotoBoardService.class);

      int no = Integer.parseInt(req.getParameter("no"));
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");

      out.println("<meta http-equiv='refresh' content='2;url=../game/list'>");
      out.println("<title>사진 게시글 삭제</title>");
      out.println("</head>");
      out.println("<h1>사진 삭제 결과</h1>");
      out.println("<body>");
      photoBoardService.delete(no);
      out.println("<p>사진 게시글을 삭제하였습니다.</p>");
      out.println("</body>");
      out.println("</html>");
    } catch (Exception e) {
      new ServletException(e);
    }
  }
}
