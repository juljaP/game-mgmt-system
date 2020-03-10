package julja.gms.dao.mariadb;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import julja.gms.dao.UserDao;
import julja.gms.domain.User;

public class UserDaoImpl implements UserDao {

  SqlSessionFactory sqlSessionFactory;

  public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public int insert(User user) throws Exception {

    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.insert("UserMapper.insertUser", user);
      sqlSession.commit();
      return count;
    }
  }

  @Override
  public List<User> findAll() throws Exception {

    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("UserMapper.selectAll");
    }
  }

  @Override
  public User findByNo(int no) throws Exception {

    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectOne("UserMapper.selectDetail", no);
    }
  }

  @Override
  public List<User> findByKeyword(String keyword) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("UserMapper.selectByKeyword", "%" + keyword + "%");
    }
  }

  @Override
  public int update(User user) throws Exception {

    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.update("UserMapper.updateUser", user);
      sqlSession.commit();
      return count;
    }
  }

  @Override
  public int delete(int no) throws Exception {

    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.delete("UserMapper.deleteUser", no);
      sqlSession.commit();
      return count;
    }
  }

  @Override
  public User findByEmailAndPassword(String email, String password) throws Exception {

    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      HashMap<String, String> params = new HashMap<>();
      params.put("email", email);
      params.put("password", password);
      return sqlSession.selectOne("UserMapper.login", params);
    }
  }

}
