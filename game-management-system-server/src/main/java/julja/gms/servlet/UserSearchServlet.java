package julja.gms.servlet;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import julja.gms.domain.User;
import julja.gms.service.UserService;
import julja.util.RequestMapping;

@Component
public class UserSearchServlet {

  UserService userService;

  public UserSearchServlet(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping("/user/search")
  public void service(Map<String, String> params, PrintWriter out) throws Exception {

    String keyword = params.get("keyword");
    List<User> users = userService.search(keyword);

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>유저 검색</title>");
    out.println("</head>");

    out.println("<body>");
    out.println("<h1>유저 검색</h1>");
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
  }

}
