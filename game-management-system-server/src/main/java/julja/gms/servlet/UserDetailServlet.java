package julja.gms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import julja.gms.dao.UserDao;
import julja.gms.domain.User;

public class UserDetailServlet implements Servlet {

  UserDao userDao;

  public UserDetailServlet(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {

    int no = in.readInt();
    User user = userDao.findByNo(no);
    if (user != null) {
      out.writeUTF("OK");
      out.writeObject(user);
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 유저가 없습니다.");
    }
  }

}
