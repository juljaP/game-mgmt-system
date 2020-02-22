package julja.gms.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import julja.gms.dao.UserDao;
import julja.gms.domain.User;

public class UserListServlet implements Servlet {

  UserDao userDao;

  public UserListServlet(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    List<User> users = userDao.findAll();
    for (User u : users) {
      out.printf("[%d] %s | %s | %s\n", u.getNo(), u.getUserEmail(), u.getUserName(),
          u.getUserResisteredDate());
    }
  }

}
