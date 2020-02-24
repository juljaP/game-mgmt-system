package julja.gms.dao;

import java.util.List;
import julja.gms.domain.Game;

public interface GameDao {

  int insert(Game game) throws Exception;

  List<Game> findAll() throws Exception;

  Game findByNo(int no) throws Exception;

  int update(Game game) throws Exception;

  int delete(int no) throws Exception;

}
