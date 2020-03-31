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
import julja.gms.domain.Game;
import julja.gms.domain.PhotoBoard;
import julja.gms.service.GameService;
import julja.gms.service.PhotoBoardService;

@WebServlet("/photoBoard/list")
public class PhotoBoardListServlet extends GenericServlet {
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
      Game game = gameService.findByNo(no);

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.printf("<title>[%s]의 사진</title>", game.getGameName());
      out.println("</head>");
      out.println("<body>");
      out.printf("<h1>[%s]의 사진</h1>", game.getGameName());
      out.printf("<a href='addForm?gameNo=%d'>사진 추가</a><br>\n", game.getNo());
      out.println("<table border='1'>");
      out.println("<tr>");
      out.println("<th>번호</th>");
      out.println("<th>제목</th>");
      out.println("<th>등록일</th>");
      out.println("<th>조회수</th>");
      out.println("</th>");

      List<PhotoBoard> photoboards = photoBoardService.findAllByNo(no);

      for (PhotoBoard photoBoard : photoboards) {
        out.printf(
            "<tr><td>%d</td> <td><a href='detail?no=%d&gameNo=%d'>%s</a></td> <td>%s</td> <td>%d</td> </tr>\n",
            photoBoard.getNo(), photoBoard.getNo(), game.getNo(), photoBoard.getTitle(),
            photoBoard.getCreadtedDate(), photoBoard.getHits());
      }
      out.println("</table>");

      out.println("</body>");
      out.println("</html>");
    } catch (Exception e) {
      new ServletException(e);
    }
  }
}
