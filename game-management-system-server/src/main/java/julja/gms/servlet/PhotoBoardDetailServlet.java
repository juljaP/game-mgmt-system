package julja.gms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import julja.gms.domain.PhotoBoard;
import julja.gms.domain.PhotoFile;
import julja.gms.service.PhotoBoardService;
import julja.util.Prompt;

@Component("/photoboard/detail")
public class PhotoBoardDetailServlet implements Servlet {

  PhotoBoardService photoBoardService;

  public PhotoBoardDetailServlet(PhotoBoardService photoBoardService) {
    this.photoBoardService = photoBoardService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = Prompt.getInt(in, out, "번호? ");

    PhotoBoard photoBoard = photoBoardService.findByNo(no);

    if (photoBoard != null) {
      out.println("제목 : " + photoBoard.getTitle());
      out.println("작성일 : " + photoBoard.getCreadtedDate());
      out.println("조회수 : " + photoBoard.getHits());
      out.println("게임명 : " + photoBoard.getGame().getGameName());


      out.println("사진 파일 : ");
      for (PhotoFile photoFile : photoBoard.getFiles()) {
        out.printf(">%s\n", photoFile.getFilepath());
        out.flush();
      }
    }
  }
}
