package julja.gms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import julja.gms.dao.UserObjectFileDao;
import julja.gms.domain.User;

public class UserUpdateServlet implements Servlet {

  UserObjectFileDao userDao;

  public UserUpdateServlet(UserObjectFileDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    User user = (User) in.readObject();

    if (userDao.update(user) > 0) {
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 유저가 없습니다.");
    }
  }

}
