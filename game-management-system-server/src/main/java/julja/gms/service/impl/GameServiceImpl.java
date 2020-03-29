package julja.gms.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import julja.gms.dao.GameDao;
import julja.gms.domain.Game;
import julja.gms.service.GameService;

@Component
public class GameServiceImpl implements GameService {

  GameDao gameDao;

  public GameServiceImpl(GameDao gameDao) {
    this.gameDao = gameDao;
  }

  @Override
  public int insert(Game game) throws Exception {
    return gameDao.insert(game);
  }

  @Override
  public List<Game> findAll() throws Exception {
    return gameDao.findAll();
  }

  @Override
  public Game findByNo(int no) throws Exception {
    return gameDao.findByNo(no);
  }

  @Override
  public int update(Game game) throws Exception {
    return gameDao.update(game);
  }

  @Override
  public int delete(int no) throws Exception {
    return gameDao.delete(no);
  }

  @Override
  public List<Game> findByKeyword(Map<String, Object> params) throws Exception {
    return gameDao.findAll(params);
  }

}
