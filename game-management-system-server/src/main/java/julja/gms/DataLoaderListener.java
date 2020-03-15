package julja.gms;

import java.io.InputStream;
import java.util.Map;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
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
import julja.gms.service.impl.BoardServiceImpl;
import julja.gms.service.impl.GameServiceImpl;
import julja.gms.service.impl.PhotoBoardServiceImpl;
import julja.gms.service.impl.UserServiceImpl;
import julja.sql.PlatformTransactionManager;
import julja.sql.SqlSessionFactoryProxy;

public class DataLoaderListener implements ApplicationContextListener {

  @Override
  public void contextInitailized(Map<String, Object> context) {
    try {

      // MyBatis
      InputStream inputStream = Resources.getResourceAsStream("julja/gms/conf/mybatis-config.xml");
      SqlSessionFactory sqlSessionFactory =
          new SqlSessionFactoryProxy((new SqlSessionFactoryBuilder().build(inputStream)));
      GameDao gameDao = new GameDaoImpl(sqlSessionFactory);
      UserDao userDao = new UserDaoImpl(sqlSessionFactory);
      BoardDao boardDao = new BoardDaoImpl(sqlSessionFactory);
      PhotoBoardDao photoBoardDao = new PhotoBoardDaoImpl(sqlSessionFactory);
      PhotoFileDao photoFileDao = new PhotoFileDaoImpl(sqlSessionFactory);

      context.put("sqlSessionFactory", sqlSessionFactory);

      PlatformTransactionManager txManager = new PlatformTransactionManager(sqlSessionFactory);
      context.put("txManager", txManager);

      context.put("gameService", new GameServiceImpl(gameDao));
      context.put("photoBoardService",
          new PhotoBoardServiceImpl(photoBoardDao, photoFileDao, txManager));
      context.put("boardService", new BoardServiceImpl(boardDao));
      context.put("userService", new UserServiceImpl(userDao));

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
