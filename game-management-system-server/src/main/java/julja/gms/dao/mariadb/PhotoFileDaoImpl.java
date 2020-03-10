package julja.gms.dao.mariadb;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import julja.gms.dao.PhotoFileDao;
import julja.gms.domain.PhotoFile;

public class PhotoFileDaoImpl implements PhotoFileDao {

  SqlSessionFactory sqlSessionFactory;

  public PhotoFileDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public int insert(PhotoFile photoFile) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.insert("PhotoFileMapper.insertPhotoFile", photoFile);
      sqlSession.commit();
      return count;
    }
  }

  @Override
  public List<PhotoFile> findAll(int boardNo) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("PhotoFileMapper.selectAll", boardNo);
    }
  }

  @Override
  public int deleteAll(int boardNo) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.delete("PhotoFileMapper.deletePhotoFile", boardNo);
      sqlSession.commit();
      return count;
    }
  }
}
