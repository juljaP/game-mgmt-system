package julja.gms.dao;

import java.util.List;
import julja.gms.domain.Game;

public interface GameDao {

  public int insert(Game game) throws Exception;

  public List<Game> findAll() throws Exception;

  public Game findByNo(int no) throws Exception;

  public int update(Game game) throws Exception;

  public int delete(int no) throws Exception;

}
