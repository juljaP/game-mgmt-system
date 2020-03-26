package julja.gms.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import julja.gms.dao.UserDao;
import julja.gms.domain.User;
import julja.gms.service.UserService;

@Component
public class UserServiceImpl implements UserService {

  UserDao userDao;

  public UserServiceImpl(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public int add(User user) throws Exception {
    return userDao.insert(user);
  }

  @Override
  public List<User> list() throws Exception {
    return userDao.findAll();
  }

  @Override
  public User get(int no) throws Exception {
    return userDao.findByNo(no);
  }

  @Override
  public int update(User user) throws Exception {
    return userDao.update(user);
  }

  @Override
  public int delete(int no) throws Exception {
    return userDao.delete(no);
  }

  @Override
  public List<User> search(String keyword) throws Exception {
    return userDao.findByKeyword(keyword);
  }

  @Override
  public User login(Map<String, Object> params) throws Exception {
    return userDao.findByEmailAndPassword(params);
  }

}
