package julja.gms;

import java.util.Map;
import julja.gms.context.ApplicationContextListener;
import julja.gms.dao.BoardObjectFileDao;
import julja.gms.dao.GameObjectFileDao;
import julja.gms.dao.UserObjectFileDao;

public class DataLoaderListener implements ApplicationContextListener {

  @Override
  public void contextInitailized(Map<String, Object> context) {

    UserObjectFileDao userDao = new UserObjectFileDao("./user.ser");
    GameObjectFileDao gameDao = new GameObjectFileDao("./game.ser");
    BoardObjectFileDao boardDao = new BoardObjectFileDao("./board.ser");

    context.put("gameDao", gameDao);
    context.put("userDao", userDao);
    context.put("boardDao", boardDao);

    System.out.println("데이터를 불러왔습니다.");

  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {

    System.out.println("데이터를 저장하였습니다.");

  }

}
