package julja.gms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import julja.gms.dao.json.GameJsonFileDao;

public class GameListServlet implements Servlet {

  GameJsonFileDao gameDao;

  public GameListServlet(GameJsonFileDao gameDao) {
    this.gameDao = gameDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(gameDao.findAll());
  }

}
