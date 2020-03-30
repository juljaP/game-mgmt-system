package julja.gms.servlet;

import java.io.PrintWriter;
import java.util.Map;
import org.springframework.stereotype.Component;
import julja.gms.service.UserService;
import julja.util.RequestMapping;

@Component
public class UserAddFormServlet {

  UserService userService;

  public UserAddFormServlet(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping("/user/addForm")
  public void service(Map<String, String> params, PrintWriter out) throws Exception {

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>회원 등록</title>");
    out.println("</head>");

    out.println("<body>");
    out.println("<h1>회원 등록</h1>");

    out.println("<table border='1'>");
    out.println("<form action='/user/add'>");
    out.println("<p>이메일 : <input name='userEmail' type='email'/><br>\n");
    out.println("이름 : <input name='userName' type='text'/><br>\n");
    out.println("패스워드 : <input name='userPW' type='password'/></p>\n");
    out.println("<button>등록</button>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");

  }

}
