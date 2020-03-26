package julja.gms.service;

import java.util.List;
import julja.gms.domain.Board;

public interface BoardService {

  int add(Board board) throws Exception;

  List<Board> list() throws Exception;

  Board get(int no) throws Exception;

  int update(Board board) throws Exception;

  int delete(int no) throws Exception;

}
