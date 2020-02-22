package julja.gms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import julja.gms.dao.BoardDao;
import julja.gms.domain.Board;

public class BoardUpdateServlet implements Servlet {

  BoardDao boardDao;

  public BoardUpdateServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    out.println("번호? \n!{}!");
    out.flush();
    int no = Integer.parseInt(in.nextLine());

    Board old = boardDao.findByNo(no);

    if (old == null) {
      out.println("해당 번호의 게시글이 존재하지 않습니다.");
      return;
    }

    Board board = new Board();
    board.setNo(no);
    out.println(String.format("제목(%s) : \n!{}!", old.getBbsName()));
    board.setBbsName(in.nextLine());
    out.println(String.format("내용(%s) : \n!{}!", old.getBbsText()));
    board.setBbsText(in.nextLine());
    board.setBbsHits(old.getBbsHits());
    board.setToday(old.getToday());

    if (boardDao.update(board) > 0) {
      out.println("게시글을 변경하였습니다.");
    } else {
      out.println("데이터 저장 실패.");
    }
  }

}
