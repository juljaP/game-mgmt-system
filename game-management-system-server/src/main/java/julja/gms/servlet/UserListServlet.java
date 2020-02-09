package julja.gms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import julja.gms.dao.json.UserJsonFileDao;

public class UserListServlet implements Servlet {

  UserJsonFileDao userDao;

  public UserListServlet(UserJsonFileDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(userDao.findAll());
  }

}
