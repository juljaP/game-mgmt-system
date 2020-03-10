package julja.gms.dao.mariadb;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import julja.gms.dao.GameDao;
import julja.gms.domain.Game;

public class GameDaoImpl implements GameDao {

  SqlSessionFactory sqlSessionFactory;

  public GameDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public int insert(Game game) throws Exception {

    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.insert("GameMapper.insertGame", game);
      sqlSession.commit();
      return count;
    }
  }

  @Override
  public List<Game> findAll() throws Exception {

    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("GameMapper.selectAll");
    }
  }

  @Override
  public Game findByNo(int no) throws Exception {

    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectOne("GameMapper.selectDetail", no);
    }
  }

  @Override
  public int update(Game game) throws Exception {

    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.update("GameMapper.updateGame", game);
      sqlSession.commit();
      return count;
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.delete("GameMapper.deleteGame", no);
      sqlSession.commit();
      return count;
    }
  }

}
