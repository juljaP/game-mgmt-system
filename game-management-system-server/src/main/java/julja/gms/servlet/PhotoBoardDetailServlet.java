package julja.gms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import julja.gms.dao.PhotoBoardDao;
import julja.gms.domain.PhotoBoard;

public class PhotoBoardDetailServlet implements Servlet {

  PhotoBoardDao photoBoardDao;

  public PhotoBoardDetailServlet(PhotoBoardDao photoBoardDao) {
    this.photoBoardDao = photoBoardDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    out.println("번호? \n!{}!");
    out.flush();
    int no = Integer.parseInt(in.nextLine());

    PhotoBoard photoBoard = photoBoardDao.findByNo(no);

    if (photoBoard != null) {
      out.println("제목 : " + photoBoard.getTitle());
      out.println("작성일 : " + photoBoard.getCreadtedDate());
      out.println("조회수 : " + photoBoard.getHits());
      out.println("게임명 : " + photoBoard.getGame().getGameName());
    } else {
      out.println("해당 번호의 사진 게시글이 없습니다.");
    }
  }

}
