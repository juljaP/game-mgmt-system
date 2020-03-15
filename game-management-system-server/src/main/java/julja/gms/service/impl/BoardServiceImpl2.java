package julja.gms.service.impl;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import julja.gms.dao.BoardDao;
import julja.gms.domain.Board;
import julja.gms.service.BoardService;

public class BoardServiceImpl2 implements BoardService {

  SqlSessionFactory sqlSessionFactory;

  public BoardServiceImpl2(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public int insert(Board board) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
      return boardDao.insert(board);
    }
  }

  @Override
  public List<Board> findAll() throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
      return boardDao.findAll();
    }
  }

  @Override
  public Board findByNo(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
      return boardDao.findByNo(no);
    }
  }

  @Override
  public int update(Board board) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
      return boardDao.update(board);
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
      return boardDao.delete(no);
    }
  }

}
