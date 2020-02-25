package julja.gms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import julja.gms.dao.UserDao;
import julja.gms.domain.User;
import julja.util.Prompt;

public class UserAddServlet implements Servlet {

  UserDao userDao;

  public UserAddServlet(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    User user = new User();

    user.setUserEmail(Prompt.getString(in, out, "이메일 : "));
    user.setUserPW(Prompt.getString(in, out, "비밀번호 : "));
    user.setUserName(Prompt.getString(in, out, "회원명 : "));

    if (userDao.insert(user) > 0) {
      out.println("유저를 입력하였습니다.");
    } else {
      out.println("유저 정보 입력을 실패하였습니다.");
    }
  }

}
