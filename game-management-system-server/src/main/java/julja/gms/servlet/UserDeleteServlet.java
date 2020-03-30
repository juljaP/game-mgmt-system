package julja.gms.servlet;

import java.io.PrintWriter;
import java.util.Map;
import org.springframework.stereotype.Component;
import julja.gms.service.UserService;
import julja.util.RequestMapping;

@Component
public class UserDeleteServlet {

  UserService userService;

  public UserDeleteServlet(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping("/user/delete")
  public void service(Map<String, String> params, PrintWriter out) throws Exception {

    int no = Integer.parseInt(params.get("no"));

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh' content='2;url=/user/list'>");
    out.println("<title>회원 삭제</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>회원 삭제 결과</h1>");
    if (userService.delete(no) > 0) {
      out.println("<p>해당 번호의 유저를 삭제하였습니다.</p>");
    } else {
      out.println("<p>해당 번호의 유저가 없습니다.</p>");
    }
    out.println("</body>");
    out.println("</html>");
  }

}
