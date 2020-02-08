package julja.gms.Handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import julja.util.Prompt;

public class UserDeleteCommand implements Command {

  ObjectInputStream in;
  ObjectOutputStream out;
  Prompt prompt;

  public UserDeleteCommand(ObjectInputStream in, ObjectOutputStream out, Prompt prompt) {
    this.in = in;
    this.out = out;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("회원 번호? ");
      out.writeUTF("/user/delete");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();

      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }
      System.out.println("회원을 삭제했습니다.");
    } catch (Exception e) {
      System.out.println("명령 처리 중 오류 발생");
    }
  }

}
