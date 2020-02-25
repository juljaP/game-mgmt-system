package julja.gms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import julja.gms.dao.BoardDao;
import julja.gms.domain.Board;
import julja.util.Prompt;

public class BoardUpdateServlet implements Servlet {

  BoardDao boardDao;

  public BoardUpdateServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    int no = Prompt.getInt(in, out, "번호? ");

    Board old = boardDao.findByNo(no);

    if (old == null) {
      out.println("해당 번호의 게시글이 존재하지 않습니다.");
      return;
    }

    Board board = new Board();
    board.setNo(no);
    board.setBbsName(
        Prompt.getString(in, out, String.format("제목(%s) : ", old.getBbsName()), old.getBbsName()));
    board.setBbsText(
        Prompt.getString(in, out, String.format("내용(%s) : ", old.getBbsText()), old.getBbsText()));
    board.setBbsHits(old.getBbsHits());
    board.setToday(old.getToday());

    if (boardDao.update(board) > 0) {
      out.println("게시글을 변경하였습니다.");
    } else {
      out.println("데이터 저장 실패.");
    }
  }

}
