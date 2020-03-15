package julja.gms.dao.mariadb;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import julja.gms.dao.PhotoBoardDao;
import julja.gms.domain.PhotoBoard;

public class PhotoBoardDaoImpl implements PhotoBoardDao {

  SqlSessionFactory sqlSessionFactory;

  public PhotoBoardDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public int insert(PhotoBoard photoBoard) throws Exception {

    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.insert("PhotoBoardMapper.insertPhotoBoard", photoBoard);
      return count;
    }
  }

  @Override
  public List<PhotoBoard> findAllByNo(int GameNo) throws Exception {

    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("PhotoBoardMapper.selectPhotoBoard", GameNo);
    }
  }

  @Override
  public PhotoBoard findByNo(int no) throws Exception {

    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {

      return sqlSession.selectOne("PhotoBoardMapper.selectDetail", no);
    }
  }

  @Override
  public int update(PhotoBoard photoBoard) throws Exception {

    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.update("PhotoBoardMapper.updatePhotoBoard", photoBoard);
      return count;
    }
  }

  @Override
  public int delete(int no) throws Exception {

    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.delete("PhotoBoardMapper.deletePhotoBoard", no);
      return count;
    }
  }

}
