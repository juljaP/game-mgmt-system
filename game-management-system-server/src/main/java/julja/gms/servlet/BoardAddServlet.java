package julja.gms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import julja.gms.dao.BoardDao;
import julja.gms.domain.Board;

public class BoardAddServlet implements Servlet {

  BoardDao boardDao;

  public BoardAddServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    Board board = new Board();

    out.println("제목 : \n!{}!");
    board.setBbsName(in.nextLine());
    out.println("내용 : \n!{}!");
    board.setBbsText(in.nextLine());

    if (boardDao.insert(board) > 0) {
      out.println("게시글을 입력하였습니다.");
    } else {
      out.println("게시글 입력에 실패했습니다.");
    }
  }

}
