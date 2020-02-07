package julja.gms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import julja.gms.Handler.BoardAddCommand;
import julja.gms.Handler.BoardDeleteCommand;
import julja.gms.Handler.BoardDetailCommand;
import julja.gms.Handler.BoardListCommand;
import julja.gms.Handler.BoardUpdateCommand;
import julja.gms.Handler.Command;
import julja.gms.Handler.GameAddCommand;
import julja.gms.Handler.GameDeleteCommand;
import julja.gms.Handler.GameDetailCommand;
import julja.gms.Handler.GameListCommand;
import julja.gms.Handler.GameUpdateCommand;
import julja.gms.Handler.UserAddCommand;
import julja.gms.Handler.UserDeleteCommand;
import julja.gms.Handler.UserDetailCommand;
import julja.gms.Handler.UserListCommand;
import julja.gms.Handler.UserUpdateCommand;
import julja.util.Prompt;

public class ClientApp {

  Scanner sc = new Scanner(System.in);
  Prompt prompt = new Prompt(sc);


  public void service() {

    String serverAddr = null;
    int port = 0;
    Scanner sc = new Scanner(System.in);

    try {
      System.out.print("서버: ");
      serverAddr = sc.nextLine();
      System.out.print("포트: ");
      port = Integer.parseInt(sc.nextLine());
    } catch (Exception e) {
      System.out.println("서버 주소 혹은 포트 번호가 유효하지 않습니다.");
      sc.close();
      return;
    }

    try (Socket socket = new Socket(serverAddr, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
      System.out.println("서버와 연결되었습니다.");

      processCommand(in, out);

      System.out.println("서버와 연결을 끊었습니다.");

    } catch (Exception e) {
      System.out.print("예외 발생: ");
      e.printStackTrace();
    }

    sc.close();
  }

  private void processCommand(ObjectInputStream in, ObjectOutputStream out) {

    Deque<String> stack = new ArrayDeque<>();
    Queue<String> queue = new LinkedList<>();

    HashMap<String, Command> commandMap = new HashMap<>();

    commandMap.put("/board/list", new BoardListCommand(in, out));
    commandMap.put("/board/add", new BoardAddCommand(in, out, prompt));
    commandMap.put("/board/detail", new BoardDetailCommand(in, out, prompt));
    commandMap.put("/board/update", new BoardUpdateCommand(in, out, prompt));
    commandMap.put("/board/delete", new BoardDeleteCommand(in, out, prompt));
    commandMap.put("/lesson/list", new GameListCommand(in, out));
    commandMap.put("/lesson/add", new GameAddCommand(in, out, prompt));
    commandMap.put("/lesson/detail", new GameDetailCommand(in, out, prompt));
    commandMap.put("/lesson/update", new GameUpdateCommand(in, out, prompt));
    commandMap.put("/lesson/delete", new GameDeleteCommand(in, out, prompt));
    commandMap.put("/member/list", new UserListCommand(in, out));
    commandMap.put("/member/add", new UserAddCommand(in, out, prompt));
    commandMap.put("/member/detail", new UserDetailCommand(in, out, prompt));
    commandMap.put("/member/update", new UserUpdateCommand(in, out, prompt));
    commandMap.put("/member/delete", new UserDeleteCommand(in, out, prompt));

    String command;

    while (true) {
      command = prompt.inputString("\n명령>");

      if (command.length() == 0) {
        continue;

      } else if (command.equalsIgnoreCase("quit") || command.equals("/server/stop")) {
        out.writeUTF(command);
        out.flush();
        System.out.println("서버: " + in.readUTF());
        // System.out.println("안녕!");
        break;
      } else if (command.equalsIgnoreCase("history")) {
        printCommandHistory(stack.iterator());
        System.out.println();
        continue;
      } else if (command.equalsIgnoreCase("history2")) {
        printCommandHistory(queue.iterator());
        System.out.println();
        continue;
      }
      stack.push(command);
      queue.offer(command);
      Command commandHandler = commandMap.get(command);

      if (commandHandler != null) {
        try {
          commandHandler.execute();
        } catch (Exception e) {
          System.out.println("명령어 실행 중 오류 발생 : " + e.getMessage());
        }
      } else {
        System.out.println("실행할 수 없는 명령입니다.");
      }
      System.out.println();
    }
    sc.close();

  }

  private void printCommandHistory(Iterator<String> iter) {
    Iterator<String> iterator = iter;
    int count = 0;
    String answer = null;
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
      if (++count % 5 == 0) {
        System.out.print(": ");
        answer = sc.nextLine();
        if (answer.equalsIgnoreCase("q")) {
          break;
        }
      }
    }
    System.out.println();
  }

  public static void main(String[] args) {

    System.out.println("게임 관리 시스템 클라이언트입니다.");
    ClientApp app = new ClientApp();
    app.service();

  }
}
