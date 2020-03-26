package julja.gms.servlet;

import java.io.PrintStream;
import java.util.Map;
import org.springframework.stereotype.Component;
import julja.gms.domain.Board;
import julja.gms.service.BoardService;
import julja.util.RequestMapping;

@Component
public class BoardUpdateFormServlet {

  BoardService boardService;

  public BoardUpdateFormServlet(BoardService boardService) {
    this.boardService = boardService;
  }

  @RequestMapping("/board/updateform")
  public void service(Map<String, String> params, PrintStream out) throws Exception {

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>게시글 변경</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>게시글 변경</h1>");

    Board board = boardService.get(Integer.parseInt(params.get("no")));
    out.println("<form action='/board/update'>");
    out.printf("번호: <input name='no' readonly type='text' value='%d'><br>\n", board.getNo());
    out.printf("제목: <input name='bbsName' type='text' value='%s'><br>\n", board.getBbsName());
    out.println("내용: <br>");
    out.printf("<textarea name='bbsText' rows='5' cols='60'>%s</textarea><br>\n",
        board.getBbsText());
    out.println("<button>제출</button>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }

}
