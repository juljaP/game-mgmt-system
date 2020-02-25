package julja.gms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import julja.gms.dao.BoardDao;
import julja.gms.domain.Board;
import julja.util.Prompt;

public class BoardAddServlet implements Servlet {

  BoardDao boardDao;

  public BoardAddServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    Board board = new Board();
    board.setBbsName(Prompt.getString(in, out, "제목 : "));
    board.setBbsText(Prompt.getString(in, out, "내용 : "));

    if (boardDao.insert(board) > 0) {
      out.println("게시글을 입력하였습니다.");
    } else {
      out.println("게시글 입력에 실패했습니다.");
    }
  }

}
