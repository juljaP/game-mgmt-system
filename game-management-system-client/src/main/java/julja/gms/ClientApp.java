package julja.gms;

import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import julja.util.Prompt;

public class ClientApp {

  Scanner sc = new Scanner(System.in);
  Prompt prompt = new Prompt(sc);

  Deque<String> commandStack;
  Queue<String> commandQueue;

  public ClientApp() throws Exception {
    commandStack = new ArrayDeque<>();
    commandQueue = new LinkedList<>();
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

      if (command.endsWith("/server/stop")) {
        processCommand(command);
      }
    }
    sc.close();
  }

  private void processCommand(String command) {
    String host = null;
    int port = 9999;
    String servletPath = null;

    // ex) https://localhost:9999/photoboard
    try {
      if (!command.startsWith("https://")) {
        throw new Exception("명령어 형식이 옳지 않습니다.");
      }

      String url = command.substring(8);

      int index = url.indexOf('/');
      String[] str = url.substring(0, index).split(":");
      host = str[0];

      if (str.length == 2) {
        port = Integer.parseInt(str[1]);
      }
      servletPath = url.substring(index);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return;
    }

    // Connect to Server
    try (Socket socket = new Socket(host, port);
        PrintStream out = new PrintStream(socket.getOutputStream());
        Scanner in = new Scanner(socket.getInputStream())) {

      out.println(servletPath);
      out.flush();

      while (true) {
        String response = in.nextLine();
        if (response.equals("!end!")) {
          break;
        } else if (response.contentEquals("!{}!")) {
          String input = prompt.inputString("");
          out.println(input);
        } else {
          System.out.println(response);
        }
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
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
