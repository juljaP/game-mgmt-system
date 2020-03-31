package julja.gms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import org.springframework.context.ApplicationContext;
import julja.gms.domain.User;
import julja.gms.service.UserService;

@WebServlet("/user/update")
public class UserUpdateServlet extends GenericServlet {
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
      User user = userService.get(no);

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<meta http-equiv='refresh' content='2;url=list'>");
      out.println("<title>회원 정보 수정</title>");
      out.println("</head>");

      out.println("<body>");
      out.println("<h1>회원 정보 수정 결과</h1>");

      user.setNo(no);
      user.setUserEmail(req.getParameter("userEmail"));
      user.setUserPW(req.getParameter("userPW"));
      user.setUserName(req.getParameter("userName"));
      user.setUserResisteredDate(Date.valueOf(req.getParameter("userResisteredDate")));

      if (userService.update(user) > 0) {
        out.println("<p>유저 정보를 변경하였습니다.</p>");
      } else {
        out.println("<p>데이터 저장 실패.</p>");
      }
      out.println("</body>");
      out.println("</html>");
    } catch (Exception e) {
      new ServletException(e);
    }
  }
}
