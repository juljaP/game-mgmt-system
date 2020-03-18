package julja.gms.service.impl;

import java.util.List;
import org.springframework.stereotype.Component;
import julja.gms.dao.BoardDao;
import julja.gms.domain.Board;
import julja.gms.service.BoardService;

@Component
public class BoardServiceImpl implements BoardService {

  BoardDao boardDao;

  public BoardServiceImpl(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public int insert(Board board) throws Exception {
    return boardDao.insert(board);
  }

  @Override
  public List<Board> findAll() throws Exception {
    return boardDao.findAll();
  }

  @Override
  public Board findByNo(int no) throws Exception {
    return boardDao.findByNo(no);
  }

  @Override
  public int update(Board board) throws Exception {
    return boardDao.update(board);
  }

  @Override
  public int delete(int no) throws Exception {
    return boardDao.delete(no);
  }

}
