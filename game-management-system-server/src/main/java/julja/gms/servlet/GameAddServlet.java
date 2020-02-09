package julja.gms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import julja.gms.dao.GameObjectFileDao;
import julja.gms.domain.Game;

public class GameAddServlet implements Servlet {

  GameObjectFileDao gameDao;

  public GameAddServlet(GameObjectFileDao gameDao) {
    this.gameDao = gameDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Game game = (Game) in.readObject();

    if (gameDao.insert(game) > 0) {
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("같은 품번의 게임이 있습니다.");
    }
  }

}
