package julja.gms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/user/addForm")
public class UserAddFormServlet extends GenericServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {
    try {
      res.setContentType("text/html;charset=UTF-8");
      PrintWriter out = res.getWriter();

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>회원 등록</title>");
      out.println("</head>");

      out.println("<body>");
      out.println("<h1>회원 등록</h1>");

      out.println("<table border='1'>");
      out.println("<form action='add'>");
      out.println("<p>이메일 : <input name='userEmail' type='email'/><br>\n");
      out.println("이름 : <input name='userName' type='text'/><br>\n");
      out.println("패스워드 : <input name='userPW' type='password'/></p>\n");
      out.println("<button>등록</button>");
      out.println("</form>");
      out.println("</body>");
      out.println("</html>");
    } catch (Exception e) {
      new ServletException(e);
    }
  }
}
