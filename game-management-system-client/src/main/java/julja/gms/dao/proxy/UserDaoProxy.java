package julja.gms.dao.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import julja.gms.domain.User;

public class UserDaoProxy {

  ObjectInputStream in;
  ObjectOutputStream out;

  public UserDaoProxy(ObjectInputStream in, ObjectOutputStream out) {
    this.in = in;
    this.out = out;
  }

  public int insert(User user) throws Exception {
    out.writeUTF("/user/add");
    out.writeObject(user);
    out.flush();

    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return 1;
  }

  @SuppressWarnings("unchecked")
  public List<User> findAll() throws Exception {
    out.writeUTF("/user/list");
    out.flush();

    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return (List<User>) in.readObject();
  }

  public User findByNo(int no) throws Exception {
    out.writeUTF("/user/detail");
    out.writeInt(no);
    out.flush();

    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return (User) in.readObject();
  }

  public int update(User user) throws Exception {
    out.writeUTF("/user/update");
    out.writeObject(user);
    out.flush();

    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return 1;
  }

  public int delete(int no) throws Exception {
    out.writeUTF("/user/delete");
    out.writeInt(no);
    out.flush();

    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return 1;
  }

}