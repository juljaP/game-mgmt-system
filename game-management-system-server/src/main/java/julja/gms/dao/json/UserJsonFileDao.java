package julja.gms.dao.json;

import java.util.List;
import julja.gms.domain.User;

public class UserJsonFileDao extends AbstractJsonFileDao<User> {

  public UserJsonFileDao(String filename) {
    super(filename);
  }

  public int insert(User user) throws Exception {

    if (indexOf(user.getNo()) > -1) {
      return 0;
    }

    list.add(user);
    saveData();
    return 1;
  }

  public List<User> findAll() throws Exception {
    return list;
  }

  public User findByNo(int no) throws Exception {
    int index = indexOf(no);
    if (index == -1) {
      return null;
    }
    return list.get(index);
  }

  public int update(User user) throws Exception {
    int index = indexOf(user.getNo());

    if (index == -1) {
      return 0;
    }

    list.set(index, user);
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
