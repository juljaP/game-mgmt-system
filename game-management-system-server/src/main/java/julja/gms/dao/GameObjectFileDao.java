package julja.gms.dao;

import java.util.List;
import julja.gms.domain.Game;

public class GameObjectFileDao extends AbstractObjectFileDao<Game> {

  public GameObjectFileDao(String filename) {
    super(filename);
  }

  public int insert(Game game) throws Exception {

    if (indexOf(game.getNo()) > -1) {
      return 0;
    }

    list.add(game);
    saveData();
    return 1;
  }

  public List<Game> findAll() throws Exception {
    return list;
  }

  public Game findByNo(int no) throws Exception {
    int index = indexOf(no);
    if (index == -1) {
      return null;
    }
    return list.get(index);
  }

  public int update(Game game) throws Exception {
    int index = indexOf(game.getNo());

    if (index == -1) {
      return 0;
    }

    list.set(index, game);
    saveData();
    return 1;
  }

  public int delete(int no) throws Exception {
    int index = indexOf(no);
    if (index == -1) {
      return 0;
    }

    list.remove(index);
    saveData();
    return 1;
  }


  @Override
  protected <K> int indexOf(K key) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == (int) key) {
        return i;
      }
    }
    return -1;
  }

}
