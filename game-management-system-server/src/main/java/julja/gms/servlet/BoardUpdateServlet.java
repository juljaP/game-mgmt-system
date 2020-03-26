package julja.gms.servlet;

import java.io.PrintStream;
import java.util.Map;
import org.springframework.stereotype.Component;
import julja.gms.domain.Board;
import julja.gms.service.BoardService;
import julja.util.RequestMapping;

@Component
public class BoardUpdateServlet {

  BoardService boardService;

  public BoardUpdateServlet(BoardService boardService) {
    this.boardService = boardService;
  }

  @RequestMapping("/board/update")
  public void service(Map<String, String> params, PrintStream out) throws Exception {

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh' content='2;url=/board/list'>");
    out.println("<title>게시글 변경</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>게시글 변경 결과</h1>");

    Board board = new Board();
    board.setNo(Integer.parseInt(params.get("no")));
    board.setBbsName(params.get("bbsName"));
    board.setBbsText(params.get("bbsText"));

    if (boardService.update(board) > 0) {
      out.println("<p>게시글을 변경하였습니다.</p>");
    } else {
      out.println("<p>데이터 저장 실패.</p>");
    }
    out.println("</body>");
    out.println("</html>");
  }

}
