package julja.gms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import org.springframework.context.ApplicationContext;
import julja.gms.domain.User;
import julja.gms.service.UserService;

@WebServlet("/user/search")
public class UserSearchServlet extends GenericServlet {
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

      String keyword = req.getParameter("keyword");
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
    } catch (Exception e) {
      new ServletException(e);
    }
  }
}
