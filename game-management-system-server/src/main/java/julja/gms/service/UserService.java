package julja.gms.service;

import java.util.List;
import julja.gms.domain.User;

public interface UserService {

  int insert(User user) throws Exception;

  List<User> findAll() throws Exception;

  User findByNo(int no) throws Exception;

  int update(User user) throws Exception;

  int delete(int no) throws Exception;

  default List<User> findByKeyword(String keyword) throws Exception {
    return null;
  }

  default User findByEmailAndPassword(String email, String password) throws Exception {
    return null;
  }

}
