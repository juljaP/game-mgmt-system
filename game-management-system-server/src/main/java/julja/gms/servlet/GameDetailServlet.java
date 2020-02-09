package julja.gms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import julja.gms.dao.json.GameJsonFileDao;
import julja.gms.domain.Game;

public class GameDetailServlet implements Servlet {

  GameJsonFileDao gameDao;

  public GameDetailServlet(GameJsonFileDao gameDao) {
    this.gameDao = gameDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {

    int no = in.readInt();

    Game game = gameDao.findByNo(no);

    if (game != null) {
      out.writeUTF("OK");
      out.writeObject(game);
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 게임이 없습니다.");
    }

  }

}
