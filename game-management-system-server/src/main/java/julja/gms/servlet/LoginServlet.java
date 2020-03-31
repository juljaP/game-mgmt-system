package julja.gms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import org.springframework.context.ApplicationContext;
import julja.gms.domain.User;
import julja.gms.service.UserService;

@WebServlet("/auth/login")
public class LoginServlet extends GenericServlet {
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

      Map<String, Object> user = new HashMap<String, Object>();
      user.put("email", req.getParameter("email"));
      user.put("password", req.getParameter("password"));
      User u = userService.login(user);

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      if (u != null) {
        out.println("<meta http-equiv='refresh' content='2;url=../game/list'>");
      } else {
        out.println("<meta http-equiv='refresh' content='2;url=loginForm'>");
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
    } catch (Exception e) {
      new ServletException(e);
    }
  }
}
