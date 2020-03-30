package julja.gms.servlet;

import java.io.PrintWriter;
import java.sql.Date;
import java.util.Map;
import org.springframework.stereotype.Component;
import julja.gms.domain.User;
import julja.gms.service.UserService;
import julja.util.RequestMapping;

@Component
public class UserUpdateServlet {

  UserService userService;

  public UserUpdateServlet(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping("/user/update")
  public void service(Map<String, String> params, PrintWriter out) throws Exception {

    int no = Integer.parseInt(params.get("no"));
    User user = userService.get(no);

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh' content='2;url=/user/list'>");
    out.println("<title>회원 정보 수정</title>");
    out.println("</head>");

    out.println("<body>");
    out.println("<h1>회원 정보 수정 결과</h1>");

    user.setNo(no);
    user.setUserEmail(params.get("userEmail"));
    user.setUserPW(params.get("userPW"));
    user.setUserName(params.get("userName"));
    user.setUserResisteredDate(Date.valueOf(params.get("userResisteredDate")));

    if (userService.update(user) > 0) {
      out.println("<p>유저 정보를 변경하였습니다.</p>");
    } else {
      out.println("<p>데이터 저장 실패.</p>");
    }
    out.println("</body>");
    out.println("</html>");
  }

}
