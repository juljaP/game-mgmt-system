package julja.gms.service;

import java.util.List;
import java.util.Map;
import julja.gms.domain.Game;

public interface GameService {

  int insert(Game game) throws Exception;

  List<Game> findAll() throws Exception;

  List<Game> findByKeyword(Map<String, Object> params) throws Exception;

  Game findByNo(int no) throws Exception;

  int update(Game game) throws Exception;

  int delete(int no) throws Exception;

}
