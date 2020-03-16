package julja.gms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import julja.gms.domain.User;
import julja.gms.service.UserService;
import julja.util.Component;
import julja.util.Prompt;

@Component("/user/add")
public class UserAddServlet implements Servlet {

  UserService userService;

  public UserAddServlet(UserService userService) {
    this.userService = userService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    User user = new User();

    user.setUserEmail(Prompt.getString(in, out, "이메일 : "));
    user.setUserPW(Prompt.getString(in, out, "비밀번호 : "));
    user.setUserName(Prompt.getString(in, out, "회원명 : "));

    if (userService.insert(user) > 0) {
      out.println("유저를 입력하였습니다.");
    } else {
      out.println("유저 정보 입력을 실패하였습니다.");
    }
  }

}
