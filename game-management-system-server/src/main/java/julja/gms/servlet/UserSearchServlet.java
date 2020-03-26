package julja.gms.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import julja.gms.domain.User;
import julja.gms.service.UserService;
import julja.util.Prompt;
import julja.util.RequestMapping;

@Component
public class UserSearchServlet {

  UserService userService;

  public UserSearchServlet(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping("/user/search")
  public void service(Scanner in, PrintStream out) throws Exception {

    String keyword = Prompt.getString(in, out, "검색어? ");

    List<User> users = userService.search(keyword);
    for (User u : users) {
      out.printf("[%d] %s | %s | %s\n", u.getNo(), u.getUserEmail(), u.getUserName(),
          u.getUserResisteredDate());
    }
  }

}
