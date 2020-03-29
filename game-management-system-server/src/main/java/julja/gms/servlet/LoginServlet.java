package julja.gms.servlet;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import julja.gms.domain.User;
import julja.gms.service.UserService;
import julja.util.RequestMapping;

@Component
public class LoginServlet {

  UserService userService;

  public LoginServlet(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping("/auth/login")
  public void service(Map<String, String> params, PrintStream out) throws Exception {

    Map<String, Object> user = new HashMap<String, Object>();
    user.put("email", params.get("email"));
    user.put("password", params.get("password"));
    User u = userService.login(user);

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    if (u != null) {
      out.println("<meta http-equiv='refresh' content='2;url=/game/list'>");
    } else {
      out.println("<meta http-equiv='refresh' content='2;url=/auth/loginForm'>");
    }
    out.println("<title>로그인</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>로그인 결과</h1>");

    if (u != null) {
      out.printf("<p>'%s'님 환영합니다.</p>\n", u.getUserName());
    } else {
      out.println("<p>사용자 정보가 유효하지 않습니다.</p>");
    }
    out.println("</body>");
    out.println("</html>");
  }
}
