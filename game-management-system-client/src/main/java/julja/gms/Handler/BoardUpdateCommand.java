package julja.gms.Handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import julja.gms.domain.Board;
import julja.util.Prompt;

public class BoardUpdateCommand implements Command {

  ObjectInputStream in;
  ObjectOutputStream out;
  Prompt prompt;

  public BoardUpdateCommand(ObjectInputStream in, ObjectOutputStream out, Prompt prompt) {
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

      Board oldBoard = (Board) in.readObject();
      Board newBoard = new Board();
      newBoard.setNo(oldBoard.getNo());
      System.out.println("제목 : " + oldBoard.getBbsName());
      newBoard.setBbsName(oldBoard.getBbsName());
      newBoard.setBbsText(prompt.inputString(String.format("내용(%s)? ", oldBoard.getBbsText()),
          oldBoard.getBbsText()));
      newBoard.setBbsHits(oldBoard.getBbsHits());
      newBoard.setToday(new Date(System.currentTimeMillis()));

      if (oldBoard.equals(newBoard)) {
        System.out.println("게시글 변경을 취소했습니다.");
        return;
      }
      out.writeUTF("/board/update");
      out.writeObject(newBoard);
      out.flush();

      response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }
      System.out.println("게시글을 변경했습니다.");
    } catch (Exception e) {
      System.out.println("명령 실행 중 오류 발생!");
    }
  }
}
