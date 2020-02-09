package julja.gms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import julja.gms.domain.User;

public class UserUpdateServlet implements Servlet {

  List<User> list = null;

  public UserUpdateServlet(List<User> list) {
    this.list = list;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    try {
      User user = (User) in.readObject();
      int index = -1;
      for (int i = 0; i < list.size(); i++) {
        if (list.get(i).getNo() == user.getNo()) {
          index = i;
          break;
        }
      }
      if (index != -1) {
        list.set(index, user);
        out.writeUTF("OK");
      } else {
        out.writeUTF("FAIL");
        out.writeUTF("해당 번호의 회원이 없습니다.");
      }
    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF(e.getMessage());
    }
  }

}
