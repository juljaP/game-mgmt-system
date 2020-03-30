package julja.gms.servlet;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import julja.gms.domain.Game;
import julja.gms.domain.PhotoBoard;
import julja.gms.service.GameService;
import julja.gms.service.PhotoBoardService;
import julja.util.RequestMapping;

@Component
public class PhotoBoardListServlet {

  PhotoBoardService photoBoardService;
  GameService gameService;

  public PhotoBoardListServlet(PhotoBoardService photoBoardService, GameService gameService) {
    this.photoBoardService = photoBoardService;
    this.gameService = gameService;
  }

  @RequestMapping("/photoboard/list")
  public void service(Map<String, String> params, PrintWriter out) throws Exception {
    int no = Integer.parseInt(params.get("no"));
    Game game = gameService.findByNo(no);

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.printf("<title>[%s]의 사진</title>", game.getGameName());
    out.println("</head>");
    out.println("<body>");
    out.printf("<h1>[%s]의 사진</h1>", game.getGameName());
    out.printf("<a href='/photoboard/addForm?gameNo=%d'>사진 추가</a><br>\n", game.getNo());
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
          "<tr><td>%d</td> <td><a href='/photoboard/detail?no=%d&gameNo=%d'>%s</a></td> <td>%s</td> <td>%d</td> </tr>\n",
          photoBoard.getNo(), photoBoard.getNo(), game.getNo(), photoBoard.getTitle(),
          photoBoard.getCreadtedDate(), photoBoard.getHits());
    }
    out.println("</table>");

    out.println("</body>");
    out.println("</html>");
  }

}
