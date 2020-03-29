package julja.gms.servlet;

import java.io.PrintStream;
import java.util.Map;
import org.springframework.stereotype.Component;
import julja.gms.domain.PhotoBoard;
import julja.gms.domain.PhotoFile;
import julja.gms.service.PhotoBoardService;
import julja.util.RequestMapping;

@Component
public class PhotoBoardDetailServlet {

  PhotoBoardService photoBoardService;

  public PhotoBoardDetailServlet(PhotoBoardService photoBoardService) {
    this.photoBoardService = photoBoardService;
  }

  @RequestMapping("/photoboard/detail")
  public void service(Map<String, String> params, PrintStream out) throws Exception {
    int no = Integer.parseInt(params.get("no"));

    PhotoBoard photoBoard = photoBoardService.findByNo(no);

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("   <meta charset='UTF-8'>");
    out.println("   <title>사진 상세정보</title>");
    out.println("</head>");
    out.println("<body>");
    out.printf("   <h1>[%s] 사진 상제정보</h1>", photoBoard.getGame().getGameName());

    if (photoBoard != null) {
      out.println("<form action='/photoboard/update'>");
      out.printf("<p>번호: <input name='no' readonly type='text' readonly value='%d'><br>\n",
          photoBoard.getNo());
      out.printf("제목 : <input name='title' type='text' value='%s'><br>\n", photoBoard.getTitle());
      out.printf("등록일: %s<br>\n", photoBoard.getCreadtedDate());
      out.printf("조회수: %d<br>\n", photoBoard.getHits());
      out.printf("게임명 : %s<br></p>\n", photoBoard.getGame().getGameName());
      out.println("<hr>");
      out.println("사진 파일 : <br>");
      out.println("<ul>\n");

      for (PhotoFile photoFile : photoBoard.getFiles()) {
        out.printf("  <li>%s</li>\n", photoFile.getFilepath());
      }
      out.println("</ul>");

      out.println("사진: <input name='photo1' type='file'><br>");
      out.println("사진: <input name='photo2' type='file'><br>");
      out.println("사진: <input name='photo3' type='file'><br>");
      out.println("사진: <input name='photo4' type='file'><br>");
      out.println("사진: <input name='photo5' type='file'><br>");

      out.println("<p><button>변경</button>");
      out.printf("<a href='/photoboard/delete?no=%d&gameNo=%d'>삭제</a></p>\n", photoBoard.getNo(),
          photoBoard.getGame().getNo());
      out.println("</form>");

    }

    out.println("</body>");
    out.println("</html>");
  }
}
