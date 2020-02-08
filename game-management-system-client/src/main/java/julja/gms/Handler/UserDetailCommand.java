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
    try {
      int no = prompt.inputInt("게시글 번호? ");
      out.writeUTF("/user/detail");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();

      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }
      User u = (User) in.readObject();
      System.out.printf("이메일 : %s\n", u.getUserEmail());
      System.out.printf("비밀번호 : %s\n", u.getUserPW());
      System.out.printf("회원명 : %s\n", u.getUserName());
      System.out.printf("가입일 : %s\n", u.getUserResisteredDate());
    } catch (Exception e) {
      System.out.println("명령 실행 중 오류 발생");
    }
  }

}
