package julja.gms.Handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import julja.gms.domain.User;
import julja.util.Prompt;

public class UserDetailCommand implements Command {

  ObjectInputStream in;
  ObjectOutputStream out;
  Prompt prompt;

  public UserDetailCommand(ObjectInputStream in, ObjectOutputStream out, Prompt prompt) {
    this.in = in;
    this.out = out;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    int index = indexOfUser(prompt.inputInt("회원 번호? "));
    if (index == -1) {
      System.out.println("해당하는 유저가 존재하지 않습니다.");
      return;
    }
    User u = userList.get(index);
    System.out.printf("이메일 : %s\n", u.getUserEmail());
    System.out.printf("비밀번호 : %s\n", u.getUserPW());
    System.out.printf("회원명 : %s\n", u.getUserName());
    System.out.printf("가입일 : %s\n", u.getUserResisteredDate());
  }

  private int indexOfUser(int num) {
    for (int i = 0; i < this.userList.size(); i++) {
      if (this.userList.get(i).getNo() == num) {
        return i;
      }
    }
    return -1;
  }

}
