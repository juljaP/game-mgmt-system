package julja.gms.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import julja.gms.domain.User;
import julja.gms.service.UserService;
import julja.util.RequestMapping;

@Component
public class UserListServlet {

  UserService userService;

  public UserListServlet(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping("/user/list")
  public void service(Map<String, String> params, PrintStream out) throws Exception {
    List<User> users = userService.list();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>유저 목록</title>");
    out.println("</head>");

    out.println("<body>");
    out.println("<h1>유저 목록</h1>");
    out.println("<a href='/user/addForm'>새 회원</a><br>");
    out.println("<table border='1'>");
    out.println("<tr>");
    out.println("<th>번호</th>");
    out.println("<th>이메일</th>");
    out.println("<th>이름</th>");
    out.println("<th>등록일</th>");
    out.println("</tr>");

    for (User u : users) {
      out.printf(
          "<tr><td>%d</td> <td><a href='/user/detail?no=%d'>%s</a></td> <td>%s</td> <td>%s</td></tr>\n",
          u.getNo(), u.getNo(), u.getUserEmail(), u.getUserName(), u.getUserResisteredDate());
    }
    out.println("</table>");
    out.println("<hr>");

    out.println("<form action='/user/search'>");
    out.println("<p>검색어 : <input name='keyword' type='text'/>");
    out.println("<button>검색</button></p>");
    out.println("</form>");

    out.println("</body>");
    out.println("</html>");
  }
}
