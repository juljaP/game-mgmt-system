package julja.gms.dao.mariadb;

import java.util.List;
import java.util.Map;
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
      return count;
    }
  }

  @Override
  public List<Game> findAll() throws Exception {

    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("GameMapper.selectGame");
    }
  }

  @Override
  public Game findByNo(int no) throws Exception {

    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectOne("GameMapper.selectDetail", no);
    }
  }

  @Override
  public List<Game> findByKeyword(Map<String, Object> params) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("GameMapper.selectGame", params);
    }
  }

  @Override
  public int update(Game game) throws Exception {

    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.update("GameMapper.updateGame", game);
      return count;
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.delete("GameMapper.deleteGame", no);
      return count;
    }
  }

}
