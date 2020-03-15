package julja.gms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import julja.gms.service.BoardService;
import julja.util.Prompt;

public class BoardDeleteServlet implements Servlet {

  BoardService boardService;

  public BoardDeleteServlet(BoardService boardService) {
    this.boardService = boardService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    int no = Prompt.getInt(in, out, "번호? ");

    if (boardService.delete(no) > 0) {
      out.println("해당 번호의 게시물을 삭제하였습니다.");
    } else {
      out.println("해당 번호의 게시물이 없습니다.");
    }
  }

}
