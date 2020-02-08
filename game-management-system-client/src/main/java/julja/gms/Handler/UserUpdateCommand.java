package julja.gms.Handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import julja.gms.domain.User;
import julja.util.Prompt;

public class UserUpdateCommand implements Command {

  ObjectInputStream in;
  ObjectOutputStream out;
  Prompt prompt;

  public UserUpdateCommand(ObjectInputStream in, ObjectOutputStream out, Prompt prompt) {
    this.in = in;
    this.out = out;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("회원 번호? ");

      out.writeUTF("/user/detail");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();

      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      User oldUser = (User) in.readObject();
      User newUser = new User();
      newUser.setNo(oldUser.getNo());
      newUser.setUserEmail(prompt.inputString(String.format("이메일(%s) : ", oldUser.getUserEmail()),
          oldUser.getUserEmail()));
      newUser.setUserPW(prompt.inputString(String.format("비밀번호(%s) : ", oldUser.getUserPW()),
          oldUser.getUserPW()));
      newUser.setUserName(prompt.inputString(String.format("회원명(%s) : ", oldUser.getUserName()),
          oldUser.getUserName()));
      newUser.setUserResisteredDate(oldUser.getUserResisteredDate());

      if (oldUser.equals(newUser)) {
        System.out.println("유저 변경을 취소했습니다.");
        return;
      }
      out.writeUTF("/user/update");
      out.writeObject(newUser);
      out.flush();

      response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }
      System.out.println("회원 정보를 수정하였습니다.");
    } catch (Exception e) {
      System.out.println("명령 실행 중 오류 발생!");
    }
  }
}
