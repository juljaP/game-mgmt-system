package julja.gms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import julja.gms.dao.UserObjectFileDao;

public class UserListServlet implements Servlet {

  UserObjectFileDao userDao;

  public UserListServlet(UserObjectFileDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(userDao.findAll());
  }

}
