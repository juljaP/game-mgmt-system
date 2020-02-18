package julja.gms.dao.proxy;

import java.util.List;
import julja.gms.dao.BoardDao;
import julja.gms.domain.Board;

public class BoardDaoProxy implements BoardDao {

  DaoProxyHelper daoProxyHelper;

  public BoardDaoProxy(String host, int port) {
    daoProxyHelper = new DaoProxyHelper(host, port);
  }

  @Override
  public int insert(Board board) throws Exception {
    return (int) daoProxyHelper.request((in, out) -> {
      out.writeUTF("/board/add");
      out.writeObject(board);
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
  public List<Board> findAll() throws Exception {
    return (List<Board>) daoProxyHelper.request((in, out) -> {
      out.writeUTF("/board/list");
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        throw new Exception(in.readUTF());
      }
      return (List<Board>) in.readObject();
    });

  }

  @Override
  public Board findByNo(int no) throws Exception {
    return (Board) daoProxyHelper.request((in, out) -> {
      out.writeUTF("/board/detail");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();

      if (response.equals("FAIL")) {
        throw new Exception(in.readUTF());
      }
      return (Board) in.readObject();
    });
  }

  @Override
  public int update(Board board) throws Exception {
    return (int) daoProxyHelper.request((in, out) -> {
      out.writeUTF("/board/update");
      out.writeObject(board);
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
      out.writeUTF("/board/delete");
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
