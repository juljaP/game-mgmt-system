package julja.gms.servlet;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import julja.gms.domain.Game;
import julja.gms.domain.PhotoBoard;
import julja.gms.domain.PhotoFile;
import julja.gms.service.GameService;
import julja.gms.service.PhotoBoardService;
import julja.util.RequestMapping;

@Component
public class PhotoBoardUpdateServlet {

  PhotoBoardService photoBoardService;
  GameService gameService;

  public PhotoBoardUpdateServlet(PhotoBoardService photoBoardService, GameService gameService) {
    this.photoBoardService = photoBoardService;
    this.gameService = gameService;
  }

  @RequestMapping("/photoboard/update")
  public void service(Map<String, String> params, PrintStream out) throws Exception {

    int boardNo = Integer.parseInt(params.get("no"));
    PhotoBoard photoBoard = photoBoardService.findByNo(boardNo);

    photoBoardService.delete(boardNo);

    Game game = photoBoard.getGame();
    photoBoard.setTitle(params.get("title"));
    photoBoard.setGame(game);

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");

    out.println("<meta http-equiv='refresh' content='2;url=/game/list'>");
    out.println("<title>사진 게시글 수정</title>");
    out.println("</head>");
    out.println("<h1>사진 수정 결과</h1>");
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
    out.println("<p>사진 게시글을 수정했습니다.</p>");
    out.println("</body>");
    out.println("</html>");
  }

}
