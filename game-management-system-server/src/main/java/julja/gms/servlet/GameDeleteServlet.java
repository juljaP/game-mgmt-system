package julja.gms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import julja.gms.domain.Game;

public class GameDeleteServlet implements Servlet {

  List<Game> list = null;

  public GameDeleteServlet(List<Game> list) {
    this.list = list;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    try {
      int no = in.readInt();

      int index = -1;
      for (int i = 0; i < list.size(); i++) {
        if (list.get(i).getNo() == no) {
          index = i;
          break;
        }
      }
      if (index != -1) {
        list.remove(index);
        out.writeUTF("OK");
      } else {
        out.writeUTF("FAIL");
        out.writeUTF("해당 번호의 게시물이 없습니다.");
      }
    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF(e.getMessage());
    }
  }

}
