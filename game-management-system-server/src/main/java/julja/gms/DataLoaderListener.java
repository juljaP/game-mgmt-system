package julja.gms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;
import julja.gms.context.ApplicationContextListener;
import julja.gms.dao.BoardDao;
import julja.gms.dao.GameDao;
import julja.gms.dao.PhotoBoardDao;
import julja.gms.dao.PhotoFileDao;
import julja.gms.dao.UserDao;
import julja.gms.dao.mariadb.BoardDaoImpl;
import julja.gms.dao.mariadb.GameDaoImpl;
import julja.gms.dao.mariadb.PhotoBoardDaoImpl;
import julja.gms.dao.mariadb.PhotoFileDaoImpl;
import julja.gms.dao.mariadb.UserDaoImpl;

public class DataLoaderListener implements ApplicationContextListener {

  public static Connection con;

  @Override
  public void contextInitailized(Map<String, Object> context) {
    try {
      Class.forName("org.mariadb.jdbc.Driver");
      con = DriverManager.getConnection( //
          "jdbc:mariadb://localhost:3306/gmsdb", "study", "1111");

      UserDao userDao = new UserDaoImpl(con);
      GameDao gameDao = new GameDaoImpl(con);
      BoardDao boardDao = new BoardDaoImpl(con);
      PhotoBoardDao photoBoardDao = new PhotoBoardDaoImpl(con);
      PhotoFileDao photoFileDao = new PhotoFileDaoImpl(con);

      context.put("gameDao", gameDao);
      context.put("userDao", userDao);
      context.put("boardDao", boardDao);
      context.put("photoBoardDao", photoBoardDao);
      context.put("photoFileDao", photoFileDao);

      System.out.println("데이터를 불러왔습니다.");
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    try {
      con.close();
    } catch (Exception e) {
    }
    System.out.println("데이터를 저장하였습니다.");
  }

}
