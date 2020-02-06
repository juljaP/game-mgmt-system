package julja.gms;

import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import julja.gms.Handler.Command;
import julja.util.Prompt;

public class ClientApp {

  Scanner sc = new Scanner(System.in);
  Prompt prompt = new Prompt(sc);

  @SuppressWarnings("unchecked")
  public void service() {
    Deque<String> stack = new ArrayDeque<>();
    Queue<String> queue = new LinkedList<>();
    HashMap<String, Command> commandMap = new HashMap<>();
    String command;

    while (true) {
      command = prompt.inputString("명령>");

      if (command.length() == 0) {
        continue;
      } else if (command.equalsIgnoreCase("quit")) {
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

      if (commandHandler != null)
        try {
          commandHandler.execute();
        } catch (Exception e) {
          System.out.println("명령어 실행 중 오류 발생 : " + e.getMessage());
        }
      else {
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
    

    /*
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
        PrintStream out = new PrintStream(socket.getOutputStream());
        Scanner in = new Scanner(socket.getInputStream())) {
      System.out.println("서버와 연결되었습니다.");
      System.out.print("메시지: ");
      String msg = sc.nextLine();
      out.println(msg);
      System.out.println("서버에 메시지를 전송하였습니다.");
      String getMsg = in.nextLine();
      System.out.println("서버에서 메시지를 수신하였습니다.");
      System.out.println("서버: " + getMsg);
      System.out.println("서버와의 연결을 끊었습니다.");
    } catch (Exception e) {
      System.out.print("예외 발생: ");
      e.printStackTrace();
    }
    sc.close();
    */
  }
}
