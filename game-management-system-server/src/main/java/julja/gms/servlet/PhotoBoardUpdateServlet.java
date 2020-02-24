package julja.gms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import julja.gms.dao.PhotoBoardDao;
import julja.gms.domain.Game;
import julja.gms.domain.PhotoBoard;

public class PhotoBoardUpdateServlet implements Servlet {

  PhotoBoardDao photoBoardDao;

  public PhotoBoardUpdateServlet(PhotoBoardDao photoBoardDao) {
    this.photoBoardDao = photoBoardDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    out.println("번호? \n!{}!");
    out.flush();
    int no = Integer.parseInt(in.nextLine());

    PhotoBoard old = photoBoardDao.findByNo(no);

    if (old == null) {
      out.println("해당 번호의 사진 게시글이 없습니다.");
      return;
    }
    Game game = new Game();
    game.setNo(no);

    PhotoBoard photoBoard = new PhotoBoard();
    photoBoard.setNo(no);
    out.println(String.format("제목(%s) : \n!{}!", old.getTitle()));
    photoBoard.setTitle(in.nextLine());
    // photoBoard.setCreadtedDate(old.getCreadtedDate());
    // photoBoard.setHits(old.getHits());
    // photoBoard.setGame(game);

    if (photoBoardDao.update(photoBoard) > 0) {
      out.println("사진 변경했습니다.");
    } else {
      out.println("데이터 저장 실패.");
    }
  }

}
