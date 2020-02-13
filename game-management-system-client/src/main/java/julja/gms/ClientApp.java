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
import julja.gms.dao.proxy.BoardDaoProxy;
import julja.gms.dao.proxy.GameDaoProxy;
import julja.gms.dao.proxy.UserDaoProxy;
import julja.gms.handler.BoardAddCommand;
import julja.gms.handler.BoardDeleteCommand;
import julja.gms.handler.BoardDetailCommand;
import julja.gms.handler.BoardListCommand;
import julja.gms.handler.BoardUpdateCommand;
import julja.gms.handler.Command;
import julja.gms.handler.GameAddCommand;
import julja.gms.handler.GameDeleteCommand;
import julja.gms.handler.GameDetailCommand;
import julja.gms.handler.GameListCommand;
import julja.gms.handler.GameUpdateCommand;
import julja.gms.handler.UserAddCommand;
import julja.gms.handler.UserDeleteCommand;
import julja.gms.handler.UserDetailCommand;
import julja.gms.handler.UserListCommand;
import julja.gms.handler.UserUpdateCommand;
import julja.util.Prompt;

public class ClientApp {

  Scanner sc = new Scanner(System.in);
  Prompt prompt = new Prompt(sc);

  Deque<String> commandStack;
  Queue<String> commandQueue;

  // String host = null;
  // int port = 0;

  public ClientApp() {
    commandStack = new ArrayDeque<>();
    commandQueue = new LinkedList<>();
  }

  public void service() {

    Scanner sc = new Scanner(System.in);

    try {
      // host = prompt.inputString("서버? ");
      // port = prompt.inputInt("포트? ");
    } catch (Exception e) {
      System.out.println("서버 주소 혹은 포트 번호가 유효하지 않습니다.");
      sc.close();
      return;
    }

    String command;

    while (true) {
      command = prompt.inputString("\n명령> ");

      if (command.length() == 0)
        continue;

      if (command.equalsIgnoreCase("quit")) {
        break;
      } else if (command.equalsIgnoreCase("history")) {
        printCommandHistory(commandStack.iterator());
        continue;
      } else if (command.equalsIgnoreCase("history2")) {
        printCommandHistory(commandQueue.iterator());
        continue;
      }
      commandStack.push(command);
      commandQueue.offer(command);

      processCommand(command);
    }
    sc.close();
  }

  private void processCommand(String command) {

    try (/* Socket socket = new Socket(serverAddr, port); */
        Socket socket = new Socket("localhost", 9999);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
      // System.out.println("서버와 연결되었습니다.");

      BoardDaoProxy boardDao = new BoardDaoProxy(in, out);
      UserDaoProxy userDao = new UserDaoProxy(in, out);
      GameDaoProxy gameDao = new GameDaoProxy(in, out);

      HashMap<String, Command> commandMap = new HashMap<>();

      commandMap.put("/board/list", new BoardListCommand(boardDao));
      commandMap.put("/board/add", new BoardAddCommand(boardDao, prompt));
      commandMap.put("/board/detail", new BoardDetailCommand(boardDao, prompt));
      commandMap.put("/board/update", new BoardUpdateCommand(boardDao, prompt));
      commandMap.put("/board/delete", new BoardDeleteCommand(boardDao, prompt));

      commandMap.put("/game/list", new GameListCommand(gameDao));
      commandMap.put("/game/add", new GameAddCommand(gameDao, prompt));
      commandMap.put("/game/detail", new GameDetailCommand(gameDao, prompt));
      commandMap.put("/game/update", new GameUpdateCommand(gameDao, prompt));
      commandMap.put("/game/delete", new GameDeleteCommand(gameDao, prompt));

      commandMap.put("/user/list", new UserListCommand(userDao));
      commandMap.put("/user/add", new UserAddCommand(userDao, prompt));
      commandMap.put("/user/detail", new UserDetailCommand(userDao, prompt));
      commandMap.put("/user/update", new UserUpdateCommand(userDao, prompt));
      commandMap.put("/user/delete", new UserDeleteCommand(userDao, prompt));

      commandMap.put("/server/stop", () -> {
        try {
          out.writeUTF(command);
          out.flush();
          System.out.println("서버: " + in.readUTF());
          // System.out.println("안녕!");
        } catch (Exception e) {

        }
      });

      Command commandHandler = commandMap.get(command);

      if (commandHandler == null) {
        System.out.println("실행할 수 없는 명령입니다.");
        return;
      }

      commandHandler.execute();
    } catch (Exception e) {
      System.out.println("명령어 실행 중 오류 발생 : " + e.getMessage());
      e.printStackTrace();

    }
    // System.out.println("서버와 연결을 끊었음!");
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
  }

  public static void main(String[] args) {

    System.out.println("게임 관리 시스템 클라이언트입니다.");
    ClientApp app = new ClientApp();
    app.service();

  }
}
