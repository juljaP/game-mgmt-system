package julja.gms.servlet;

import java.io.PrintWriter;
import java.util.Map;
import org.springframework.stereotype.Component;
import julja.gms.domain.User;
import julja.gms.service.UserService;
import julja.util.RequestMapping;

@Component
public class UserAddServlet {

  UserService userService;

  public UserAddServlet(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping("/user/add")
  public void service(Map<String, String> params, PrintWriter out) throws Exception {

    User user = new User();
    user.setUserEmail(params.get("userEmail"));
    user.setUserPW(params.get("userPW"));
    user.setUserName(params.get("userName"));

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh' content='2;url=/user/list'>");
    out.println("<title>회원 등록</title>");
    out.println("</head>");

    out.println("<body>");
    out.println("<h1>회원 등록 결과</h1>");

    if (userService.add(user) > 0) {
      out.println("<p>유저를 입력하였습니다.</p>");
    } else {
      out.println("<p>유저 정보 입력을 실패하였습니다.</p>");
    }
    out.println("</body>");
    out.println("</html>");
  }

}
