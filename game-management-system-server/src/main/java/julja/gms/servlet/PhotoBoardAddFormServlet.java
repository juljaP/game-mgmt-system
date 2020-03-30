package julja.gms.servlet;

import java.io.PrintWriter;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;
import julja.gms.domain.Game;
import julja.gms.service.GameService;
import julja.gms.service.PhotoBoardService;
import julja.util.RequestMapping;

@Component
public class PhotoBoardAddFormServlet {

  PhotoBoardService photoBoardService;
  GameService gameService;

  TransactionTemplate transactionTemplate;

  public PhotoBoardAddFormServlet(PhotoBoardService photoBoardService, GameService gameService) {
    this.photoBoardService = photoBoardService;
    this.gameService = gameService;
  }

  @RequestMapping("/photoboard/addForm")
  public void service(Map<String, String> params, PrintWriter out) throws Exception {

    Game game = gameService.findByNo(Integer.parseInt(params.get("gameNo")));

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>사진 게시글 등록</title>");
    out.println("</head>");

    out.println("<body>");
    out.println("<h1>사진 입력</h1>");
    out.println("<form action='/photoboard/add'>");
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
  }
}
