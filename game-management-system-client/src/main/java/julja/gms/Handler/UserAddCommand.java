package julja.gms.Handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import julja.gms.domain.User;
import julja.util.Prompt;

public class UserAddCommand implements Command {

  ObjectInputStream in;
  ObjectOutputStream out;
  Prompt prompt;

  public UserAddCommand(ObjectInputStream in, ObjectOutputStream out, Prompt prompt) {
    this.in = in;
    this.out = out;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    User u = new User();
    u.setNo(prompt.inputInt("번호 : "));
    u.setUserEmail(prompt.inputString("이메일 : "));
    u.setUserPW(prompt.inputString("비밀번호 : "));
    u.setUserName(prompt.inputString("회원명 : "));
    System.out.print("가입일 : ");
    u.setUserResisteredDate(new Date(System.currentTimeMillis()));
    System.out.println(u.getUserResisteredDate());
    System.out.println();

    try {
      out.writeUTF("/user/add");
      out.writeObject(u);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      System.out.println("저장하였습니다.");

    } catch (Exception e) {
      System.out.println("통신 오류 발생.");
    }
  }

}
