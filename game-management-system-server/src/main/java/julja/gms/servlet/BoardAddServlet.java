package julja.gms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import julja.gms.domain.Board;
import julja.gms.service.BoardService;
import julja.util.Component;
import julja.util.Prompt;

@Component("/board/add")
public class BoardAddServlet implements Servlet {

  BoardService boardService;

  public BoardAddServlet(BoardService boardService) {
    this.boardService = boardService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    Board board = new Board();
    board.setBbsName(Prompt.getString(in, out, "제목 : "));
    board.setBbsText(Prompt.getString(in, out, "내용 : "));

    if (boardService.insert(board) > 0) {
      out.println("게시글을 입력하였습니다.");
    } else {
      out.println("게시글 입력에 실패했습니다.");
    }
  }

}
