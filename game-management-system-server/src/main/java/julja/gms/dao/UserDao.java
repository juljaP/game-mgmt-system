package julja.gms.dao;

import java.util.List;
import java.util.Map;
import julja.gms.domain.User;

public interface UserDao {

  int insert(User user) throws Exception;

  List<User> findAll() throws Exception;

  User findByNo(int no) throws Exception;

  int update(User user) throws Exception;

  int delete(int no) throws Exception;

  default List<User> findByKeyword(String keyword) throws Exception {
    return null;
  }

  default User findByEmailAndPassword(Map<String, Object> params) throws Exception {
    return null;
  }

}
