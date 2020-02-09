package julja.gms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import julja.gms.domain.Game;

public class GameAddServlet implements Servlet {

  List<Game> list = null;

  public GameAddServlet(List<Game> list) {
    this.list = list;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    try {
      Game game = (Game) in.readObject();

      int i = 0;
      for (; i < list.size(); i++) {
        if (list.get(i).getNo() == game.getNo()) {
          break;
        }
      }
      if (i == list.size()) {
        list.add(game);
        out.writeUTF("OK");
      } else {
        out.writeUTF("FAIL");
        out.writeUTF("같은 번호의 수업이 있습니다.");
      }
    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF(e.getMessage());
    }
  }

}
