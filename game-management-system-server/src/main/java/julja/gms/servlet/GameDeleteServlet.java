package julja.gms.servlet;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import julja.gms.domain.PhotoBoard;
import julja.gms.service.GameService;
import julja.gms.service.PhotoBoardService;
import julja.util.RequestMapping;

@Component
public class GameDeleteServlet {

  GameService gameService;
  PhotoBoardService photoBoardService;

  public GameDeleteServlet(GameService gameService, PhotoBoardService photoBoardService) {
    this.gameService = gameService;
    this.photoBoardService = photoBoardService;
  }

  @RequestMapping("/game/delete")
  public void service(Map<String, String> params, PrintWriter out) throws Exception {

    int no = Integer.parseInt(params.get("no"));

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("   <meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh' content='2;url=/game/list'>");
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
  }

}
