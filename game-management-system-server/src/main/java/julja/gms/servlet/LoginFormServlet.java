package julja.gms.servlet;

import java.io.PrintStream;
import java.util.Map;
import org.springframework.stereotype.Component;
import julja.gms.service.UserService;
import julja.util.RequestMapping;

@Component
public class LoginFormServlet {

  UserService userService;

  public LoginFormServlet(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping("/auth/loginForm")
  public void service(Map<String, String> params, PrintStream out) throws Exception {
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>로그인</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>로그인</h1>");
    out.println("<form action='/auth/login'>");
    out.println("이메일 : <input name='email' type='email'/><br>");
    out.println("비밀번호 : <input name='password' type='password'/><br>\n");
    out.println("<button>로그인</button>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }
}
