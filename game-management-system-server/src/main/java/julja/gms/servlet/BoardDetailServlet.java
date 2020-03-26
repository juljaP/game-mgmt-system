package julja.gms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import julja.gms.domain.Board;
import julja.gms.service.BoardService;
import julja.util.Prompt;
import julja.util.RequestMapping;

@Component
public class BoardDetailServlet {

  BoardService boardService;

  public BoardDetailServlet(BoardService boardService) {
    this.boardService = boardService;
  }

  @RequestMapping("/board/detail")
  public void service(Scanner in, PrintStream out) throws Exception {

    int no = Prompt.getInt(in, out, "번호? ");

    Board b = boardService.get(no);

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
