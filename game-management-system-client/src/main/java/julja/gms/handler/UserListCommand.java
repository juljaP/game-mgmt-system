package julja.gms.handler;

import java.util.List;
import julja.gms.dao.proxy.UserDaoProxy;
import julja.gms.domain.User;

public class UserListCommand implements Command {

  UserDaoProxy userDao;

  public UserListCommand(UserDaoProxy userDao) {
    this.userDao = userDao;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void execute() {

    try {

      List<User> users = userDao.findAll();
      for (User u : users) {
        System.out.printf("[%d] %s | %s | %s\n", u.getNo(), u.getUserEmail(), u.getUserName(),
            u.getUserResisteredDate());
      }
    } catch (Exception e) {
      System.out.println("통신 오류 발생.");
    }
  }
}
