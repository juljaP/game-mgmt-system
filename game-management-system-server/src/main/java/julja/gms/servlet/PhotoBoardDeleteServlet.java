package julja.gms.servlet;

import java.io.PrintStream;
import java.sql.Connection;
import java.util.Scanner;
import julja.gms.dao.PhotoBoardDao;
import julja.gms.dao.PhotoFileDao;
import julja.util.ConnectionFactory;
import julja.util.Prompt;

public class PhotoBoardDeleteServlet implements Servlet {

  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;
  ConnectionFactory conFactory;

  public PhotoBoardDeleteServlet(PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao,
      ConnectionFactory conFactory) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
    this.conFactory = conFactory;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = Prompt.getInt(in, out, "번호? ");
    Connection con = conFactory.getConnection();
    con.setAutoCommit(false);

    try {
      photoFileDao.deleteAll(no);

      if (photoBoardDao.delete(no) == 0) {
        throw new Exception("해당 번호의 사진 게시글이 없습니다.");
      }
      con.commit();
      out.println("사진 게시글을 삭제했습니다.");
    } catch (Exception e) {
      con.rollback();
      out.println(e.getMessage());
    } finally {
      con.setAutoCommit(true);
    }
  }

}
