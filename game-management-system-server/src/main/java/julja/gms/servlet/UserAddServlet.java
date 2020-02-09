package julja.gms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import julja.gms.dao.json.UserJsonFileDao;
import julja.gms.domain.User;

public class UserAddServlet implements Servlet {

  UserJsonFileDao userDao;

  public UserAddServlet(UserJsonFileDao userDao) {
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
