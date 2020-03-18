package julja.gms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import julja.gms.service.PhotoBoardService;
import julja.util.Prompt;

@Component("/photoboard/delete")
public class PhotoBoardDeleteServlet implements Servlet {

  PhotoBoardService photoBoardService;

  public PhotoBoardDeleteServlet(PhotoBoardService photoBoardService) {
    this.photoBoardService = photoBoardService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = Prompt.getInt(in, out, "번호? ");
    photoBoardService.delete(no);
  }
}
