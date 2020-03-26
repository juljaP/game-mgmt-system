package julja.gms.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import julja.gms.domain.User;
import julja.gms.service.UserService;
import julja.util.RequestMapping;

@Component
public class UserListServlet {

  UserService userService;

  public UserListServlet(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping("/user/list")
  public void service(Scanner in, PrintStream out) throws Exception {
    List<User> users = userService.list();
    for (User u : users) {
      out.printf("[%d] %s | %s | %s\n", u.getNo(), u.getUserEmail(), u.getUserName(),
          u.getUserResisteredDate());
    }
  }

}
