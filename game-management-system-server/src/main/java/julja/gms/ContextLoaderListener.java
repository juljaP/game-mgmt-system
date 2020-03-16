package julja.gms;

import java.io.InputStream;
import java.util.HashMap;
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
import julja.sql.MybatisDaoFactory;
import julja.sql.PlatformTransactionManager;
import julja.sql.SqlSessionFactoryProxy;
import julja.util.ApplicationContext;

public class ContextLoaderListener implements ApplicationContextListener {

  @Override
  public void contextInitailized(Map<String, Object> context) {
    try {

      HashMap<String, Object> beans = new HashMap<>();

      // MyBatis
      InputStream inputStream = Resources.getResourceAsStream("julja/gms/conf/mybatis-config.xml");
      SqlSessionFactory sqlSessionFactory =
          new SqlSessionFactoryProxy((new SqlSessionFactoryBuilder().build(inputStream)));

      MybatisDaoFactory daoFactory = new MybatisDaoFactory(sqlSessionFactory);

      // 이 메서드를 호출한 쪽(App)에서 DAO 객체를 사용할 수 있도록 Map 객체에 담아둔다.
      beans.put("gameDao", daoFactory.createDao(GameDao.class));
      beans.put("userDao", daoFactory.createDao(UserDao.class));
      beans.put("boardDao", daoFactory.createDao(BoardDao.class));
      beans.put("photoBoardDao", daoFactory.createDao(PhotoBoardDao.class));
      beans.put("photoFileDao", daoFactory.createDao(PhotoFileDao.class));

      context.put("sqlSessionFactory", sqlSessionFactory);

      PlatformTransactionManager txManager = new PlatformTransactionManager(sqlSessionFactory);
      context.put("txManager", txManager);

      ApplicationContext appCtx = new ApplicationContext(//
          "julja.gms", // 새로 생성할 객체의 패키지
          beans // 기존에 따로 생성한 객체 목록
      );
      context.put("iocContainer", appCtx);
      appCtx.printBeans();
      System.out.println("데이터를 불러왔습니다.");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {}

}
