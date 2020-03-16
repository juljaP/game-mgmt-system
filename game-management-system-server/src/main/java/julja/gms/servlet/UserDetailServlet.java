package julja.gms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import julja.gms.domain.User;
import julja.gms.service.UserService;
import julja.util.Component;
import julja.util.Prompt;

@Component("/user/detail")
public class UserDetailServlet implements Servlet {

  UserService userService;

  public UserDetailServlet(UserService userService) {
    this.userService = userService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    int no = Prompt.getInt(in, out, "번호? ");

    User u = userService.findByNo(no);
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
