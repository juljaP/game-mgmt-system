package julja.gms.Handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import julja.gms.domain.Board;
import julja.util.Prompt;

public class BoardDetailCommand implements Command {

  ObjectInputStream in;
  ObjectOutputStream out;
  Prompt prompt;

  public BoardDetailCommand(ObjectInputStream in, ObjectOutputStream out, Prompt prompt) {
    this.in = in;
    this.out = out;
    this.prompt = prompt;
  }


  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("게시글 번호? ");
      out.writeUTF("/board/detail");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();

      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }
      Board b = (Board) in.readObject();
      System.out.println("제목 : " + b.getBbsName());
      System.out.println("내용 : " + b.getBbsText());
      System.out.println("조회수 : " + b.getBbsHits());
    } catch (Exception e) {
      System.out.println("명령 실행 중 오류 발생");
    }
  }
}
