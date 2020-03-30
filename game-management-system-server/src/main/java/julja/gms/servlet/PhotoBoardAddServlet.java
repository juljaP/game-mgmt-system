package julja.gms.servlet;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;
import julja.gms.domain.Game;
import julja.gms.domain.PhotoBoard;
import julja.gms.domain.PhotoFile;
import julja.gms.service.GameService;
import julja.gms.service.PhotoBoardService;
import julja.util.RequestMapping;

@Component
public class PhotoBoardAddServlet {

  PhotoBoardService photoBoardService;
  GameService gameService;

  TransactionTemplate transactionTemplate;

  public PhotoBoardAddServlet(PhotoBoardService photoBoardService, GameService gameService) {
    this.photoBoardService = photoBoardService;
    this.gameService = gameService;
  }

  @RequestMapping("/photoboard/add")
  public void service(Map<String, String> params, PrintWriter out) throws Exception {

    Game game = gameService.findByNo(Integer.parseInt(params.get("gameNo")));
    PhotoBoard photoBoard = new PhotoBoard();
    photoBoard.setTitle(params.get("title"));
    photoBoard.setGame(game);

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");

    out.println("<meta http-equiv='refresh' content='2;url=/game/list'>");
    out.println("<title>사진 게시글 등록</title>");
    out.println("</head>");
    out.println("<h1>사진 입력 결과</h1>");
    out.println("<body>");

    List<PhotoFile> photoFiles = new ArrayList<>();

    for (int i = 1; i < 6; i++) {
      PhotoFile photoFile = new PhotoFile();
      if (params.get("photo" + i).length() > 0) {
        photoFile.setNo(game.getNo());
        photoFile.setFilepath(params.get("photo" + i));
        photoFiles.add((i - 1), photoFile);
      }
    }

    photoBoard.setFiles(photoFiles);

    photoBoardService.insert(photoBoard);
    out.println("<p>새 사진 게시글을 등록했습니다.</p>");
    out.println("</body>");
    out.println("</html>");
  }

}
