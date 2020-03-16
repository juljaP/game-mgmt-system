package julja.gms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import julja.gms.domain.User;
import julja.gms.service.UserService;
import julja.util.Component;
import julja.util.Prompt;

@Component("/user/update")
public class UserUpdateServlet implements Servlet {

  UserService userService;

  public UserUpdateServlet(UserService userService) {
    this.userService = userService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    int no = Prompt.getInt(in, out, "번호? ");

    User old = userService.findByNo(no);

    if (old == null) {
      out.println("해당 번호의 유저가 없습니다.");
      return;
    }

    User user = new User();

    user.setNo(no);
    user.setUserEmail(Prompt.getString(in, out, String.format("이메일(%s) : ", old.getUserEmail()),
        old.getUserEmail()));
    user.setUserPW(
        Prompt.getString(in, out, String.format("비밀번호(%s) : ", old.getUserPW()), old.getUserPW()));
    user.setUserName(Prompt.getString(in, out, String.format("회원명(%s) : ", old.getUserName()),
        old.getUserName()));
    user.setUserResisteredDate(old.getUserResisteredDate());
    if (userService.update(user) > 0) {
      out.println("유저 정보를 변경하였습니다.");
    } else {
      out.println("데이터 저장 실패.");
    }
  }

}
