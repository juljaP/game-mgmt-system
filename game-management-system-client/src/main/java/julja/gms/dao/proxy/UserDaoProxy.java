package julja.gms.dao.proxy;

import java.util.List;
import julja.gms.dao.UserDao;
import julja.gms.domain.User;

public class UserDaoProxy implements UserDao {

  DaoProxyHelper daoProxyHelper;

  public UserDaoProxy(String host, int port) {
    daoProxyHelper = new DaoProxyHelper(host, port);
  }

  @Override
  public int insert(User user) throws Exception {
    return (int) daoProxyHelper.request((in, out) -> {
      out.writeUTF("/user/add");
      out.writeObject(user);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        throw new Exception(in.readUTF());
      }
      return 1;
    });

  }

  @Override
  @SuppressWarnings("unchecked")
  public List<User> findAll() throws Exception {
    return (List<User>) daoProxyHelper.request((in, out) -> {
      out.writeUTF("/user/list");
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        throw new Exception(in.readUTF());
      }
      return (List<User>) in.readObject();
    });
  }

  @Override
  public User findByNo(int no) throws Exception {
    return (User) daoProxyHelper.request((in, out) -> {
      out.writeUTF("/user/detail");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        throw new Exception(in.readUTF());
      }
      return (User) in.readObject();
    });

  }

  @Override
  public int update(User user) throws Exception {
    return (int) daoProxyHelper.request((in, out) -> {
      out.writeUTF("/user/update");
      out.writeObject(user);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        throw new Exception(in.readUTF());
      }
      return 1;
    });
  }

  @Override
  public int delete(int no) throws Exception {
    return (int) daoProxyHelper.request((in, out) -> {
      out.writeUTF("/user/delete");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        throw new Exception(in.readUTF());
      }
      return 1;
    });

  }

}
