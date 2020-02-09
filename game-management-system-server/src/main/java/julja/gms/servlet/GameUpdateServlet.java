package julja.gms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import julja.gms.domain.Game;

public class GameUpdateServlet implements Servlet {

  List<Game> list = null;

  public GameUpdateServlet(List<Game> list) {
    this.list = list;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    try {
      Game game = (Game) in.readObject();
      int index = -1;
      for (int i = 0; i < list.size(); i++) {
        if (list.get(i).getNo() == game.getNo()) {
          index = i;
          break;
        }
      }
      if (index != -1) {
        list.set(index, game);
        out.writeUTF("OK");
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
