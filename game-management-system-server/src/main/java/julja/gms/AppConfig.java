package julja.gms;

import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import julja.gms.dao.BoardDao;
import julja.gms.dao.GameDao;
import julja.gms.dao.PhotoBoardDao;
import julja.gms.dao.PhotoFileDao;
import julja.gms.dao.UserDao;
import julja.sql.MybatisDaoFactory;
import julja.sql.PlatformTransactionManager;
import julja.sql.SqlSessionFactoryProxy;

public class AppConfig {

  public AppConfig() {
    // TODO Auto-generated constructor stub
  }

  @Bean
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    InputStream inputStream = Resources.getResourceAsStream("julja/gms/conf/mybatis-config.xml");
    return new SqlSessionFactoryProxy(new SqlSessionFactoryBuilder().build(inputStream));
  }

  @Bean
  public MybatisDaoFactory daoFactory(SqlSessionFactory sqlSessionFactory) {
    return new MybatisDaoFactory(sqlSessionFactory);
  }

  @Bean
  public PlatformTransactionManager txManager(SqlSessionFactory sqlSessionFactory) {
    return new PlatformTransactionManager(sqlSessionFactory);
  }

  @Bean
  public GameDao gameDao(MybatisDaoFactory daoFactory) {
    return daoFactory.createDao(GameDao.class);
  }

  @Bean
  public UserDao userDao(MybatisDaoFactory daoFactory) {
    return daoFactory.createDao(UserDao.class);
  }

  @Bean
  public BoardDao boardDao(MybatisDaoFactory daoFactory) {
    return daoFactory.createDao(BoardDao.class);
  }

  @Bean
  public PhotoBoardDao photoBoardDao(MybatisDaoFactory daoFactory) {
    return daoFactory.createDao(PhotoBoardDao.class);
  }

  public PhotoFileDao photoFileDao(MybatisDaoFactory daoFactory) {
    return daoFactory.createDao(PhotoFileDao.class);
  }

}
