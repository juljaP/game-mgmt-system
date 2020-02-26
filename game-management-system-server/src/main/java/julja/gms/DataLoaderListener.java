package julja.gms;

import java.util.Map;
import julja.gms.context.ApplicationContextListener;
import julja.gms.dao.mariadb.BoardDaoImpl;
import julja.gms.dao.mariadb.GameDaoImpl;
import julja.gms.dao.mariadb.PhotoBoardDaoImpl;
import julja.gms.dao.mariadb.PhotoFileDaoImpl;
import julja.gms.dao.mariadb.UserDaoImpl;
import julja.util.ConnectionFactory;

public class DataLoaderListener implements ApplicationContextListener {

  @Override
  public void contextInitailized(Map<String, Object> context) {
    try {

      String jdbcUrl = "jdbc:mariadb://localhost:3306/gmsdb";
      String username = "study";
      String password = "1111";
      ConnectionFactory conFactory = new ConnectionFactory(jdbcUrl, username, password);

      context.put("gameDao", new GameDaoImpl(conFactory));
      context.put("userDao", new UserDaoImpl(conFactory));
      context.put("boardDao", new BoardDaoImpl(conFactory));
      context.put("photoBoardDao", new PhotoBoardDaoImpl(conFactory));
      context.put("photoFileDao", new PhotoFileDaoImpl(conFactory));

      System.out.println("데이터를 불러왔습니다.");
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {

    System.out.println("데이터를 저장하였습니다.");
  }

}
