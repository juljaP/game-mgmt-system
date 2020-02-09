package julja.gms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import julja.gms.dao.BoardObjectFileDao;
import julja.gms.domain.Board;

public class BoardAddServlet implements Servlet {

  BoardObjectFileDao boardDao;

  public BoardAddServlet(BoardObjectFileDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Board board = (Board) in.readObject();

    if (boardDao.insert(board) > 0) {
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("같은 번호의 게시물이 있습니다.");
    }
  }

}
