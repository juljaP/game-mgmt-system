package julja.gms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import julja.gms.dao.BoardDao;
import julja.gms.domain.Board;

public class BoardDetailServlet implements Servlet {

  BoardDao boardDao;

  public BoardDetailServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    out.println("번호? \n!{}!");
    out.flush();
    int no = Integer.parseInt(in.nextLine());

    Board b = boardDao.findByNo(no);

    if (b != null) {
      out.println("제목 : " + b.getBbsName());
      out.println("내용 : " + b.getBbsText());
      out.println("등록일 : " + b.getToday());
      out.println("조회수 : " + b.getBbsHits());
    } else {
      out.println("해당 번호의 게시물이 없습니다.");
    }
  }

}
