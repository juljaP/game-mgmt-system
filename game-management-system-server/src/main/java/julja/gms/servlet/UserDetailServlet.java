package julja.gms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import org.springframework.context.ApplicationContext;
import julja.gms.domain.User;
import julja.gms.service.UserService;

@WebServlet("/user/detail")
public class UserDetailServlet extends GenericServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {
    try {
      res.setContentType("text/html;charset=UTF-8");
      PrintWriter out = res.getWriter();

      ServletContext servletContext = req.getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");

      UserService userService = iocContainer.getBean(UserService.class);
      int no = Integer.parseInt(req.getParameter("no"));
      User u = userService.get(no);

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>유저 상세 정보</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>유저 상세 정보</h1>");
      if (u != null) {
        out.printf("<form action='update'");
        out.printf("<p>번호 : <input name='no' readonly type='number' value='%d'/><br>\n", u.getNo());
        out.printf("이메일 : <input name='userEmail' type='email' value='%s'/><br>\n",
            u.getUserEmail());
        out.printf("암호 : <input name='userPW' type='password' value='%s'/><br>\n", u.getUserPW());
        out.printf("이름 : <input name='userName' type='text' value='%s'/><br>\n", u.getUserName());
        out.printf("가입일 : <input name='userResisteredDate' type='date' value='%s'/>\n</p>",
            u.getUserResisteredDate());
        out.printf("<button>수정</button>");
        out.printf("<a href='delete?no=%d'>삭제</a>\n", u.getNo());

        out.printf("</form>");
      } else {
        out.println("<p>해당 번호의 유저가 없습니다.</p>");
      }
      out.println("</body>");
      out.println("</html>");
    } catch (Exception e) {
      new ServletException(e);
    }
  }
}
