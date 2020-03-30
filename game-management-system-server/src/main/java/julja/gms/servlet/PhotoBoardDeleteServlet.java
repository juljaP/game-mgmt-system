package julja.gms.servlet;

import java.io.PrintStream;
import java.util.Map;
import org.springframework.stereotype.Component;
import julja.gms.service.PhotoBoardService;
import julja.util.RequestMapping;

@Component
public class PhotoBoardDeleteServlet {

  PhotoBoardService photoBoardService;

  public PhotoBoardDeleteServlet(PhotoBoardService photoBoardService) {
    this.photoBoardService = photoBoardService;
  }

  @RequestMapping("/photoboard/delete")
  public void service(Map<String, String> params, PrintStream out) throws Exception {
    int no = Integer.parseInt(params.get("no"));
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");

    out.println("<meta http-equiv='refresh' content='2;url=/game/list'>");
    out.println("<title>사진 게시글 삭제</title>");
    out.println("</head>");
    out.println("<h1>사진 삭제 결과</h1>");
    out.println("<body>");
    photoBoardService.delete(no);
    out.println("<p>사진 게시글을 삭제하였습니다.</p>");
    out.println("</body>");
    out.println("</html>");
  }
}
