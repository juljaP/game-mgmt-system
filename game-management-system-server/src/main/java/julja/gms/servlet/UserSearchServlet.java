package julja.gms.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import julja.gms.dao.UserDao;
import julja.gms.domain.User;

public class UserSearchServlet implements Servlet {

  UserDao userDao;

  public UserSearchServlet(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    out.println("검색어? \n!{}!");
    out.flush();
    String keyword = in.nextLine();

    List<User> users = userDao.findByKeyword(keyword);
    for (User u : users) {
      out.printf("[%d] %s | %s | %s\n", u.getNo(), u.getUserEmail(), u.getUserName(),
          u.getUserResisteredDate());
    }
  }

}