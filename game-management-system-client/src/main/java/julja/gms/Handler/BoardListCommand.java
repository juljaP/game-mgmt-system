package julja.gms.Handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import julja.gms.domain.Board;

public class BoardListCommand implements Command {

  ObjectInputStream in;
  ObjectOutputStream out;

  public BoardListCommand(ObjectInputStream in, ObjectOutputStream out) {
    this.in = in;
    this.out = out;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void execute() {

    try {
      out.writeUTF("/board/list");
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      List<Board> boards = (List<Board>) in.readObject();

      for (Board b : boards) {
        System.out.printf("[%d] %s | %s | %d \n", b.getNo(), b.getBbsName(), b.getBbsText(),
            b.getBbsHits());
      }

    } catch (Exception e) {
      System.out.println("통신 오류 발생.");
    }
  }
}
