package julja.gms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import julja.gms.dao.UserDao;
import julja.gms.domain.User;

public class UserAddServlet implements Servlet {

  UserDao userDao;

  public UserAddServlet(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    User user = new User();

    out.println("이메일 : \n!{}!");
    user.setUserEmail(in.nextLine());
    out.println("비밀번호 : \n!{}!");
    user.setUserPW(in.nextLine());
    out.println("회원명 : \n!{}!");
    user.setUserName(in.nextLine());

    if (userDao.insert(user) > 0) {
      out.println("유저를 입력하였습니다.");
    } else {
      out.println("유저 정보 입력을 실패하였습니다.");
    }
  }

}
