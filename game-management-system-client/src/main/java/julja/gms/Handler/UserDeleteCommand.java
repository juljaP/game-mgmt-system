package julja.gms.Handler;

import java.util.List;
import julja.gms.domain.User;
import julja.util.Prompt;

public class UserDeleteCommand implements Command {

  Prompt prompt;
  List<User> userList;

  public UserDeleteCommand(Prompt prompt, List<User> list) {
    this.prompt = prompt;
    userList = list;
  }

  @Override
  public void execute() {
    int index = indexOfUser(prompt.inputInt("회원 번호? "));
    if (index == -1) {
      System.out.println("해당하는 유저가 존재하지 않습니다.");
      return;
    }
    userList.remove(index);
    System.out.println("회원 정보를 삭제하였습니다.");
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
