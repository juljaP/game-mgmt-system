package julja.gms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import julja.gms.dao.UserDao;

public class UserDeleteServlet implements Servlet {

  UserDao userDao;

  public UserDeleteServlet(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    out.println("번호? \n!{}!");
    out.flush();
    int no = Integer.parseInt(in.nextLine());

    if (userDao.delete(no) > 0) {
      out.println("해당 번호의 유저를 삭제하였습니다.");
    } else {
      out.println("해당 번호의 유저가 없습니다.");
    }
  }

}
