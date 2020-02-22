package julja.gms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import julja.gms.dao.UserDao;
import julja.gms.domain.User;

public class UserDetailServlet implements Servlet {

  UserDao userDao;

  public UserDetailServlet(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    out.println("번호? \n!{}!");
    out.flush();
    int no = Integer.parseInt(in.nextLine());

    User u = userDao.findByNo(no);
    if (u != null) {
      out.printf("회원명 : %s\n", u.getUserName());
      out.printf("비밀번호 : %s\n", u.getUserPW());
      out.printf("이메일 : %s\n", u.getUserEmail());
      out.printf("가입일 : %s\n", u.getUserResisteredDate());
    } else {
      out.println("해당 번호의 유저가 없습니다.");
    }
  }

}
