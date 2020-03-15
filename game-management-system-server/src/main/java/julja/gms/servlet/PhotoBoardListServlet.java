package julja.gms.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import julja.gms.domain.Game;
import julja.gms.domain.PhotoBoard;
import julja.gms.service.GameService;
import julja.gms.service.PhotoBoardService;
import julja.util.Prompt;

public class PhotoBoardListServlet implements Servlet {

  PhotoBoardService photoBoardService;
  GameService gameService;

  public PhotoBoardListServlet(PhotoBoardService photoBoardService, GameService gameService) {
    this.photoBoardService = photoBoardService;
    this.gameService = gameService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = Prompt.getInt(in, out, "게임 번호? ");
    Game game = gameService.findByNo(no);
    if (game == null) {
      out.println("해당하는 번호의 게임이 존재하지 않습니다.");
      return;
    }

    out.println("게임명 : " + game.getGameName());

    List<PhotoBoard> photoboards = photoBoardService.findAllByNo(no);

    for (PhotoBoard photoBoard : photoboards) {
      out.printf("[%d] %s | %s | %d \n", photoBoard.getNo(), photoBoard.getTitle(),
          photoBoard.getCreadtedDate(), photoBoard.getHits());
    }
  }

}
