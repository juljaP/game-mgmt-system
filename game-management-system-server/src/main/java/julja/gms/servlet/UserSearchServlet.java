package julja.gms.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import julja.gms.domain.User;
import julja.gms.service.UserService;
import julja.util.Prompt;

@Component("/user/search")
public class UserSearchServlet implements Servlet {

  UserService userService;

  public UserSearchServlet(UserService userService) {
    this.userService = userService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    String keyword = Prompt.getString(in, out, "검색어? ");

    List<User> users = userService.findByKeyword(keyword);
    for (User u : users) {
      out.printf("[%d] %s | %s | %s\n", u.getNo(), u.getUserEmail(), u.getUserName(),
          u.getUserResisteredDate());
    }
  }

}
