package julja.gms.Handler;

import java.sql.Date;
import java.util.List;
import julja.gms.domain.User;
import julja.util.Prompt;

public class UserAddCommand implements Command {

  Prompt prompt;
  List<User> userList;

  public UserAddCommand(Prompt prompt, List<User> list) {
    this.prompt = prompt;
    userList = list;
  }

  @Override
  public void execute() {
    User u = new User();
    u.setUserNum(userList.size() + 1);

    u.setUserEmail(prompt.inputString("이메일 : "));
    u.setUserPW(prompt.inputString("비밀번호 : "));
    u.setUserName(prompt.inputString("회원명 : "));
    System.out.print("가입일 : ");
    u.setUserResisteredDate(new Date(System.currentTimeMillis()));
    System.out.println(u.getUserResisteredDate());
    userList.add(u);
    System.out.println("저장하였습니다.");
  }

  private int indexOfUser(int num) {
    for (int i = 0; i < this.userList.size(); i++) {
      if (this.userList.get(i).getUserNum() == num) {
        return i;
      }
    }
    return -1;
  }

}
