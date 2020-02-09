package julja.gms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import julja.gms.dao.UserObjectFileDao;

public class UserDeleteServlet implements Servlet {

  UserObjectFileDao userDao;

  public UserDeleteServlet(UserObjectFileDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();

    if (userDao.delete(no) > 0) {
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 유저가 없습니다.");
    }
  }

}
