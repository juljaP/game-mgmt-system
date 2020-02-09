package julja.gms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import julja.gms.dao.UserDao;
import julja.gms.domain.User;

public class UserAddServlet implements Servlet {

  UserDao userDao;

  public UserAddServlet(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    User user = (User) in.readObject();

    if (userDao.insert(user) > 0) {
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("같은 번호의 유저가 있습니다.");
    }
  }

}
