package julja.gms.servlet;

import java.io.PrintStream;
import java.util.Map;
import org.springframework.stereotype.Component;
import julja.gms.domain.Board;
import julja.gms.service.BoardService;
import julja.util.RequestMapping;

@Component
public class BoardDetailServlet {

  BoardService boardService;

  public BoardDetailServlet(BoardService boardService) {
    this.boardService = boardService;
  }

  @RequestMapping("/board/detail")
  public void service(Map<String, String> params, PrintStream out) throws Exception {


    Board b = boardService.get(Integer.parseInt(params.get("no")));

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>게시글 상세정보</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>게시글 상세정보</h1>");

    if (b != null) {
      out.printf("번호: %d<br>\n", b.getNo());
      out.printf("제목: %s<br>\n", b.getBbsName());
      out.printf("내용: %s<br>\n", b.getBbsText());
      out.printf("등록일: %s<br>\n", b.getToday());
      out.printf("조회수: %d<br>\n", b.getBbsHits());
      out.printf("<p><a href='/board/delete?no=%d'>삭제</a>\n", b.getNo());
      out.printf("<a href='/board/updateform?no=%d'>변경</a></p>\n", b.getNo());
    } else {
      out.println("<p>해당 번호의 게시물이 없습니다.</p>");
    }
    out.println("</body>");
    out.println("</html>");
  }

}
