package julja.gms.servlet;

import java.io.PrintStream;
import java.util.Map;
import org.springframework.stereotype.Component;
import julja.gms.domain.Board;
import julja.gms.service.BoardService;
import julja.util.RequestMapping;

@Component
public class BoardAddServlet {

  BoardService boardService;

  public BoardAddServlet(BoardService boardService) {
    this.boardService = boardService;
  }

  @RequestMapping("/board/add")
  public void service(Map<String, String> params, PrintStream out) throws Exception {

    Board board = new Board();
    board.setBbsName(params.get("bbsName"));
    board.setBbsText(params.get("bbsText"));

    boardService.add(board);

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh' content='2;url=/board/list'>");
    out.println("<title>게시글 입력</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>게시물 입력 결과</h1>");
    out.println("<p>새 게시글을 등록했습니다.</p>");
    out.println("</body>");
    out.println("</html>");
  }

}
