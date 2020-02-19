package julja.gms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import julja.gms.dao.BoardDao;
import julja.gms.dao.GameDao;
import julja.gms.dao.UserDao;
import julja.gms.dao.mariadb.BoardDaoImpl;
import julja.gms.dao.mariadb.GameDaoImpl;
import julja.gms.dao.mariadb.UserDaoImpl;
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
  Connection con;
  HashMap<String, Command> commandMap;
  String host;
  int port;

  public ClientApp() throws Exception {
    commandStack = new ArrayDeque<>();
    commandQueue = new LinkedList<>();

    Class.forName("org.mariadb.jdbc.Driver");
    con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/gmsdb", "study", "1111");

    BoardDao boardDao = new BoardDaoImpl(con);
    UserDao userDao = new UserDaoImpl(con);
    GameDao gameDao = new GameDaoImpl(con);
    commandMap = new HashMap<>();

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
      try (Socket socket = new Socket(host, port);
          ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
          ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
        out.writeUTF("/server/stop");
        out.flush();
        System.out.println("서버: " + in.readUTF());
        System.out.println("안녕!");
      } catch (Exception e) {

      }
    });
  }

  public void service() {
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

    // System.out.println("서버와 연결되었습니다.");
    Command commandHandler = commandMap.get(command);

    if (commandHandler == null) {
      System.out.println("실행할 수 없는 명령입니다.");
      return;
    }

    commandHandler.execute();
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

  public static void main(String[] args) throws Exception {

    System.out.println("게임 관리 시스템 클라이언트입니다.");
    ClientApp app = new ClientApp();
    app.service();

  }
}
