package julja.gms;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientApp {
  public static void main(String[] args) {

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
  }
}
