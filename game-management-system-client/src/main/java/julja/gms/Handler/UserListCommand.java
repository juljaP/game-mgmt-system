package julja.gms.Handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import julja.gms.domain.User;

public class UserListCommand implements Command {

  ObjectInputStream in;
  ObjectOutputStream out;

  public UserListCommand(ObjectInputStream in, ObjectOutputStream out) {
    this.in = in;
    this.out = out;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void execute() {

    try {
      out.writeUTF("/user/list");
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }
      List<User> users = (List<User>) in.readObject();
      for (User u : users) {
        System.out.printf("[%d] %s | %s | %s\n", u.getNo(), u.getUserEmail(), u.getUserName(),
            u.getUserResisteredDate());
      }
    } catch (Exception e) {
      System.out.println("통신 오류 발생.");
    }
  }
}
