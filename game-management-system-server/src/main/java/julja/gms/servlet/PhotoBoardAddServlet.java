package julja.gms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import julja.gms.domain.PhotoFile;
import julja.gms.service.GameService;
import julja.gms.service.PhotoBoardService;

@WebServlet("/photoBoard/add")
public class PhotoBoardAddServlet extends GenericServlet {
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

      Game game = gameService.findByNo(Integer.parseInt(req.getParameter("gameNo")));
      PhotoBoard photoBoard = new PhotoBoard();
      photoBoard.setTitle(req.getParameter("title"));
      photoBoard.setGame(game);

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");

      out.println("<meta http-equiv='refresh' content='2;url=../game/list'>");
      out.println("<title>사진 게시글 등록</title>");
      out.println("</head>");
      out.println("<h1>사진 입력 결과</h1>");
      out.println("<body>");

      List<PhotoFile> photoFiles = new ArrayList<>();

      for (int i = 1; i < 6; i++) {
        PhotoFile photoFile = new PhotoFile();
        if (req.getParameter("photo" + i).length() > 0) {
          photoFile.setNo(game.getNo());
          photoFile.setFilepath(req.getParameter("photo" + i));
          photoFiles.add((i - 1), photoFile);
        }
      }

      photoBoard.setFiles(photoFiles);

      photoBoardService.insert(photoBoard);
      out.println("<p>새 사진 게시글을 등록했습니다.</p>");
      out.println("</body>");
      out.println("</html>");
    } catch (Exception e) {
      new ServletException(e);
    }
  }
}
