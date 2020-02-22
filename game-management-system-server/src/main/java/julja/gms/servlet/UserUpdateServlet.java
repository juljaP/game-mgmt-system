package julja.gms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import julja.gms.dao.UserDao;
import julja.gms.domain.User;

public class UserUpdateServlet implements Servlet {

  UserDao userDao;

  public UserUpdateServlet(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    out.println("번호? \n!{}!");
    out.flush();
    int no = Integer.parseInt(in.nextLine());

    User old = userDao.findByNo(no);

    if (old == null) {
      out.println("해당 번호의 유저가 없습니다.");
      return;
    }

    User user = new User();

    user.setNo(no);
    out.println(String.format("이메일(%s) : \n!{}!", old.getUserEmail()));
    user.setUserEmail(in.nextLine());
    out.println(String.format("비밀번호(%s) : \n!{}!", old.getUserPW()));
    user.setUserPW(in.nextLine());
    out.println(String.format("회원명(%s) : \n!{}!", old.getUserName()));
    user.setUserName(in.nextLine());
    user.setUserResisteredDate(old.getUserResisteredDate());
    if (userDao.update(user) > 0) {
      out.println("유저 정보를 변경하였습니다.");
    } else {
      out.println("데이터 저장 실패.");
    }
  }

}
