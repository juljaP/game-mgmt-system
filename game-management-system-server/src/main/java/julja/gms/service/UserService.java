package julja.gms.service;

import java.util.List;
import java.util.Map;
import julja.gms.domain.User;

public interface UserService {

  int add(User user) throws Exception;

  List<User> list() throws Exception;

  User get(int no) throws Exception;

  int update(User user) throws Exception;

  int delete(int no) throws Exception;

  List<User> search(String keyword) throws Exception;

  User login(Map<String, Object> params) throws Exception;
}
