package julja.gms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import julja.gms.domain.User;
import julja.gms.service.UserService;
import julja.util.Prompt;

public class LoginServlet implements Servlet {

  UserService userService;

  public LoginServlet(UserService userService) {
    this.userService = userService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    String email = Prompt.getString(in, out, "이메일? ");
    String password = Prompt.getString(in, out, "암호? ");

    User u = userService.findByEmailAndPassword(email, password);
    if (u != null) {
      out.printf("'%s'님 환영합니다.\n", u.getUserName());
    } else {
      out.println("사용자 정보가 유효하지 않습니다.");
    }
  }
}
