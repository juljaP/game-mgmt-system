package julja.gms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import julja.context.ApplicationContextListener;
import julja.gms.domain.Board;
import julja.gms.domain.Game;
import julja.gms.domain.User;

public class ServerApp {

  Set<ApplicationContextListener> listeners = new HashSet<>();
  Map<String, Object> context = new HashMap<>();

  public void addApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }

  public void removeApplicationContextListener(ApplicationContextListener listener) {
    listeners.remove(listener);
  }

  private void notifyApplicationInitialized() {
    for (ApplicationContextListener listener : listeners) {
      listener.contextInitailized(context);
    }
  }

  private void notifyApplicationDestroyed() {
    for (ApplicationContextListener listener : listeners) {
      listener.contextDestroyed(context);
    }
  }
  
  @SuppressWarnings("unchecked")
  public void service() {
    
    notifyApplicationInitialized();
    List<Game> gameList = (List<Game>) context.get("gameList");
    List<User> userList = (List<User>) context.get("userList");
    List<Board> boardList = (List<Board>) context.get("boardList");
    
    notifyApplicationDestroyed();
    
  }

  public static void main(String[] args) throws Exception {
    System.out.println("게임 관리 시스템 서버입니다.");
    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new DataLoaderListener());
    app.service();
    

    /*
    try (ServerSocket serverSocket = new ServerSocket(9999)) {// 9999포트 클라이언트 연결 대기
      System.out.println("클라이언트 연결 대기중...");
      while (true) {
        Socket socket = serverSocket.accept();
        System.out.println("클라이언트와 연결되었습니다.");
        processRequest(socket);
        System.out.println("----------------------------");
      }
    } catch (Exception e) {
      System.out.println("서버 준비 중 오류 발생");
      return;
    }
  }

  private static void processRequest(Socket clientSocket) {
    try (Socket socket = clientSocket;
        Scanner in = new Scanner(socket.getInputStream());
        PrintStream out = new PrintStream(socket.getOutputStream())) { // 통신 입출력 스트림 준비
      String msg = in.nextLine();
      System.out.println("클라이언트: " + msg);
      out.println("done!");
    } catch (Exception e) {
      System.out.print("예외 발생: ");
      e.printStackTrace();
    }
    */
  }
 
}
