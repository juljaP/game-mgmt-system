package julja.gms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import julja.gms.dao.json.GameJsonFileDao;

public class GameDeleteServlet implements Servlet {

  GameJsonFileDao gameDao;

  public GameDeleteServlet(GameJsonFileDao gameDao) {
    this.gameDao = gameDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {

    int no = in.readInt();

    if (gameDao.delete(no) > 0) {
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 품번의 게임이 없습니다.");
    }
  }

}
