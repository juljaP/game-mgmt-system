package julja.gms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import julja.gms.domain.Game;

public class GameDetailServlet implements Servlet {

  List<Game> list = null;

  public GameDetailServlet(List<Game> list) {
    this.list = list;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    try {
      int no = in.readInt();
      Game game = null;
      for (Game g : list) {
        if (g.getNo() == no) {
          game = g;
          break;
        }
      }
      if (game != null) {
        out.writeUTF("OK");
        out.writeObject(game);
      } else {
        out.writeUTF("FAIL");
        out.writeUTF("해당 번호의 수업이 없습니다.");
      }
    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF(e.getMessage());
    }
  }

}
