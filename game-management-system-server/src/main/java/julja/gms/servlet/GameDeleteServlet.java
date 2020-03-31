package julja.gms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import org.springframework.context.ApplicationContext;
import julja.gms.domain.PhotoBoard;
import julja.gms.service.GameService;
import julja.gms.service.PhotoBoardService;

@WebServlet("/game/delete")
public class GameDeleteServlet extends GenericServlet {
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
      PhotoBoardService photoBoardService = iocContainer.getBean(PhotoBoardService.class);

      int no = Integer.parseInt(req.getParameter("no"));

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("   <meta charset='UTF-8'>");
      out.println("<meta http-equiv='refresh' content='2;url=list'>");
      out.println("   <title>게임 삭제</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("   <h1>게임 삭제 결과</h1>");

      List<PhotoBoard> photoBoards = photoBoardService.findAllByNo(no);
      for (PhotoBoard photoBoard : photoBoards) {
        photoBoardService.delete(photoBoard.getNo());
      }

      if (gameService.delete(no) > 0) {
        out.println("<p>해당 품번의 게임을 삭제하였습니다.</p>");
      } else {
        out.println("<p>해당 품번의 게임이 없습니다.</p>");
      }
      out.println("</body>");
      out.println("</html>");
    } catch (Exception e) {
      new ServletException(e);
    }
  }
}
