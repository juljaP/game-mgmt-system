package julja.gms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import julja.gms.domain.Board;

public class BoardAddServlet implements Servlet {

  List<Board> list = null;

  public BoardAddServlet(List<Board> list) {
    this.list = list;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    try {
      Board board = (Board) in.readObject();
      int i = 0;
      for (; i < list.size(); i++) {
        if (list.get(i).getNo() == board.getNo()) {
          break;
        }
      }
      if (i == list.size()) {
        list.add(board);
        out.writeUTF("OK");
      } else {
        out.writeUTF("FAIL");
        out.writeUTF("같은 번호의 게시물이 있습니다.");
      }
    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF(e.getMessage());
    }
  }

}
