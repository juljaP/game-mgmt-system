package julja.gms.servlet;

import java.io.PrintStream;
import java.util.Map;
import org.springframework.stereotype.Component;
import julja.gms.service.BoardService;
import julja.util.RequestMapping;

@Component
public class BoardAddFormServlet {

  BoardService boardService;

  public BoardAddFormServlet(BoardService boardService) {
    this.boardService = boardService;
  }

  @RequestMapping("/board/addform")
  public void service(Map<String, String> params, PrintStream out) throws Exception {

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>게시글 등록</title>");
    out.println("</head>");

    out.println("<body>");
    out.println("<h1>게시글 등록</h1>");
    out.println("<form action='/board/add'>");
    out.println("제목: <input name='bbsName' type='text'><br>");
    out.println("내용: <br>");
    out.println("<textarea name='bbsText' rows='5' cols='60'></textarea><br>");
    out.println("<button>제출</button>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }

}
