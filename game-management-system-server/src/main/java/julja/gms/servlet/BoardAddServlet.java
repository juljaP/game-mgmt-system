package julja.gms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import julja.gms.domain.Board;
import julja.gms.service.BoardService;
import julja.util.Prompt;
import julja.util.RequestMapping;

@Component
public class BoardAddServlet {

  BoardService boardService;

  public BoardAddServlet(BoardService boardService) {
    this.boardService = boardService;
  }

  @RequestMapping("/board/add")
  public void service(Scanner in, PrintStream out) throws Exception {

    Board board = new Board();
    board.setBbsName(Prompt.getString(in, out, "제목 : "));
    board.setBbsText(Prompt.getString(in, out, "내용 : "));

    if (boardService.add(board) > 0) {
      out.println("게시글을 입력하였습니다.");
    } else {
      out.println("게시글 입력에 실패했습니다.");
    }
  }

}
