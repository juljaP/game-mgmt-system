package julja.gms;

import java.io.InputStream;
import java.util.Map;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.eomcs.sql.MybatisDaoFactory;
import julja.gms.context.ApplicationContextListener;
import julja.gms.dao.GameDao;
import julja.gms.dao.PhotoBoardDao;
import julja.gms.dao.PhotoFileDao;
import julja.gms.dao.UserDao;
import julja.gms.service.impl.BoardServiceImpl2;
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

      MybatisDaoFactory daoFactory = new MybatisDaoFactory(sqlSessionFactory);

      GameDao gameDao = daoFactory.createDao(GameDao.class);
      UserDao userDao = daoFactory.createDao(UserDao.class);
      // BoardDao boardDao = daoFactory.createDao(BoardDao.class);
      PhotoBoardDao photoBoardDao = daoFactory.createDao(PhotoBoardDao.class);
      PhotoFileDao photoFileDao = daoFactory.createDao(PhotoFileDao.class);

      context.put("sqlSessionFactory", sqlSessionFactory);

      PlatformTransactionManager txManager = new PlatformTransactionManager(sqlSessionFactory);
      context.put("txManager", txManager);

      context.put("gameService", new GameServiceImpl(gameDao));
      context.put("photoBoardService",
          new PhotoBoardServiceImpl(photoBoardDao, photoFileDao, txManager));
      context.put("boardService", new BoardServiceImpl2(sqlSessionFactory));
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
