package julja.gms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import julja.gms.domain.User;

public class UserAddServlet implements Servlet {

  List<User> list = null;

  public UserAddServlet(List<User> list) {
    this.list = list;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    try {
      User user = (User) in.readObject();
      int i = 0;
      for (; i < list.size(); i++) {
        if (list.get(i).getNo() == user.getNo()) {
          break;
        }
      }
      if (i == list.size()) {
        list.add(user);
        out.writeUTF("OK");
      } else {
        out.writeUTF("FAIL");
        out.writeUTF("같은 번호의 회원이 있습니다.");
      }
    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF(e.getMessage());
    }
  }

}
