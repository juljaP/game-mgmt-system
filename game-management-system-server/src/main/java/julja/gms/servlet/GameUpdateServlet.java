package julja.gms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import julja.gms.dao.GameDao;
import julja.gms.domain.Game;

public class GameUpdateServlet implements Servlet {

  GameDao gameDao;

  public GameUpdateServlet(GameDao gameDao) {
    this.gameDao = gameDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Game game = (Game) in.readObject();

    if (gameDao.update(game) > 0) {
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 품번의 게임이 없습니다.");
    }
  }

}
