package julja.gms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import julja.gms.domain.User;

public class UserDetailServlet implements Servlet {

  List<User> list = null;

  public UserDetailServlet(List<User> list) {
    this.list = list;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    try {
      int no = in.readInt();
      User user = null;
      for (User u : list) {
        if (u.getNo() == no) {
          user = u;
          break;
        }
      }
      if (user != null) {
        out.writeUTF("OK");
        out.writeObject(user);
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
