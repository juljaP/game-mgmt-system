package julja.gms.dao;

import java.util.List;
import julja.gms.domain.Board;

public interface BoardDao {

  int insert(Board board) throws Exception;

  List<Board> findAll() throws Exception;

  Board findByNo(int no) throws Exception;

  int update(Board board) throws Exception;

  int delete(int no) throws Exception;

}
