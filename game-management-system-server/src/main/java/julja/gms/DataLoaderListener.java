package julja.gms;

import java.util.Map;
import julja.gms.context.ApplicationContextListener;
import julja.gms.dao.BoardDao;
import julja.gms.dao.GameDao;
import julja.gms.dao.UserDao;
import julja.gms.dao.json.BoardJsonFileDao;
import julja.gms.dao.json.GameJsonFileDao;
import julja.gms.dao.json.UserJsonFileDao;

public class DataLoaderListener implements ApplicationContextListener {

  @Override
  public void contextInitailized(Map<String, Object> context) {

    UserDao userDao = new UserJsonFileDao("./user.json");
    GameDao gameDao = new GameJsonFileDao("./game.json");
    BoardDao boardDao = new BoardJsonFileDao("./board.json");

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
