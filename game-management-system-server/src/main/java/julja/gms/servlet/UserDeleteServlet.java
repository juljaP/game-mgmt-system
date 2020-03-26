package julja.gms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import julja.gms.service.UserService;
import julja.util.Prompt;
import julja.util.RequestMapping;

@Component
public class UserDeleteServlet {

  UserService userService;

  public UserDeleteServlet(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping("/user/delete")
  public void service(Scanner in, PrintStream out) throws Exception {

    int no = Prompt.getInt(in, out, "번호? ");

    if (userService.delete(no) > 0) {
      out.println("해당 번호의 유저를 삭제하였습니다.");
    } else {
      out.println("해당 번호의 유저가 없습니다.");
    }
  }

}
