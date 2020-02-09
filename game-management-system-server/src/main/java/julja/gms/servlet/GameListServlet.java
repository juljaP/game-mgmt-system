package julja.gms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import julja.gms.dao.GameObjectFileDao;

public class GameListServlet implements Servlet {

  GameObjectFileDao gameDao;

  public GameListServlet(GameObjectFileDao gameDao) {
    this.gameDao = gameDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(gameDao.findAll());
  }

}
