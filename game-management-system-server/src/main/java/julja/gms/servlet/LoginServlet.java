package julja.gms.servlet;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import julja.gms.domain.User;
import julja.gms.service.UserService;
import julja.util.Prompt;
import julja.util.RequestMapping;

@Component
public class LoginServlet {

  UserService userService;

  public LoginServlet(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping("/auth/login")
  public void service(Scanner in, PrintStream out) throws Exception {
    String email = Prompt.getString(in, out, "이메일? ");
    String password = Prompt.getString(in, out, "암호? ");

    Map<String, Object> params = new HashMap<>();
    params.put("email", email);
    params.put("password", password);

    User u = userService.login(params);
    if (u != null) {
      out.printf("'%s'님 환영합니다.\n", u.getUserName());
    } else {
      out.println("사용자 정보가 유효하지 않습니다.");
    }
  }
}
