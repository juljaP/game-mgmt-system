package julja.gms.dao.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import julja.gms.domain.Game;

public class GameDaoProxy {

  DaoProxyHelper daoProxyHelper;

  public GameDaoProxy(String host, int port) {
    daoProxyHelper = new DaoProxyHelper(host, port);
  }

  public int insert(Game game) throws Exception {

    return (int) daoProxyHelper.request((in, out) -> {
      out.writeUTF("/game/add");
      out.writeObject(game);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        throw new Exception(in.readUTF());
      }
      return 1;
    });
  }

  @SuppressWarnings("unchecked")
  public List<Game> findAll() throws Exception {
    return (List<Game>) daoProxyHelper.request((in, out) -> {
      out.writeUTF("/game/list");
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        throw new Exception(in.readUTF());
      }
      return (List<Game>) in.readObject();
    });

  }

  public Game findByNo(int no) throws Exception {

    return (Game) daoProxyHelper.request(new Worker() {

      @Override
      public Object execute(ObjectInputStream in, ObjectOutputStream out) throws Exception {
        out.writeUTF("/game/detail");
        out.writeInt(no);
        out.flush();

        String response = in.readUTF();

        if (response.equals("FAIL")) {
          throw new Exception(in.readUTF());
        }
        return in.readObject();
      }
    });

  }

  public int update(Game game) throws Exception {
    return (int) daoProxyHelper.request((in, out) -> {
      out.writeUTF("/game/update");
      out.writeObject(game);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        throw new Exception(in.readUTF());
      }
      return 1;
    });

  }

  public int delete(int no) throws Exception {
    return (int) daoProxyHelper.request((in, out) -> {
      out.writeUTF("/game/delete");
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
