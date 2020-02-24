package julja.gms;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import julja.gms.context.ApplicationContextListener;
import julja.gms.dao.BoardDao;
import julja.gms.dao.GameDao;
import julja.gms.dao.UserDao;
import julja.gms.servlet.BoardAddServlet;
import julja.gms.servlet.BoardDeleteServlet;
import julja.gms.servlet.BoardDetailServlet;
import julja.gms.servlet.BoardListServlet;
import julja.gms.servlet.BoardUpdateServlet;
import julja.gms.servlet.GameAddServlet;
import julja.gms.servlet.GameDeleteServlet;
import julja.gms.servlet.GameDetailServlet;
import julja.gms.servlet.GameListServlet;
import julja.gms.servlet.GameUpdateServlet;
import julja.gms.servlet.Servlet;
import julja.gms.servlet.UserAddServlet;
import julja.gms.servlet.UserDeleteServlet;
import julja.gms.servlet.UserDetailServlet;
import julja.gms.servlet.UserListServlet;
import julja.gms.servlet.UserSearchServlet;
import julja.gms.servlet.UserUpdateServlet;

public class ServerApp {

  Set<ApplicationContextListener> listeners = new HashSet<>();
  Map<String, Servlet> servletMap = new HashMap<>();
  ExecutorService executorService = Executors.newCachedThreadPool();
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

  public void service() {

    notifyApplicationInitialized();

    GameDao gameDao = (GameDao) context.get("gameDao");
    UserDao userDao = (UserDao) context.get("userDao");
    BoardDao boardDao = (BoardDao) context.get("boardDao");

    servletMap.put("/board/add", new BoardAddServlet(boardDao));
    servletMap.put("/board/delete", new BoardDeleteServlet(boardDao));
    servletMap.put("/board/detail", new BoardDetailServlet(boardDao));
    servletMap.put("/board/list", new BoardListServlet(boardDao));
    servletMap.put("/board/update", new BoardUpdateServlet(boardDao));

    servletMap.put("/game/add", new GameAddServlet(gameDao));
    servletMap.put("/game/delete", new GameDeleteServlet(gameDao));
    servletMap.put("/game/detail", new GameDetailServlet(gameDao));
    servletMap.put("/game/list", new GameListServlet(gameDao));
    servletMap.put("/game/update", new GameUpdateServlet(gameDao));

    servletMap.put("/user/add", new UserAddServlet(userDao));
    servletMap.put("/user/delete", new UserDeleteServlet(userDao));
    servletMap.put("/user/detail", new UserDetailServlet(userDao));
    servletMap.put("/user/list", new UserListServlet(userDao));
    servletMap.put("/user/update", new UserUpdateServlet(userDao));
    servletMap.put("/user/search", new UserSearchServlet(userDao));

    try (ServerSocket serverSocket = new ServerSocket(9999)) {
      System.out.println("클라이언트 연결 대기중...");
      while (true) {
        Socket socket = serverSocket.accept();
        System.out.println("클라이언트와 연결되었습니다.");

        executorService.submit(() -> {
          processRequest(socket);
          System.out.println("-----------------------------------");
        });
      }
    } catch (Exception e) {
      System.out.println("서버 준비 중 오류 발생");
    }
    notifyApplicationDestroyed();
    executorService.shutdown();
  }

  int processRequest(Socket clientSocket) {
    try (Socket socket = clientSocket;
        Scanner in = new Scanner(socket.getInputStream());
        PrintStream out = new PrintStream(socket.getOutputStream())) {

      String request = in.nextLine();
      System.out.printf("=> %s\n", request);

      /*
       * if (request.equalsIgnoreCase("/server/stop")) { quit(out); return 9; }
       */
      Servlet servlet = servletMap.get(request);
      if (servlet != null) {
        try {
          servlet.service(in, out);
        } catch (Exception e) {
          out.println("요청 처리 중 오류 발생!");
          out.println(e.getMessage());
          System.out.print("클라이언트 요청 처리 중 오류발생: ");
          e.printStackTrace();
        }
      } else {
        notFound(out);
      }
      out.println("!end!");
      out.flush();
      return 0;
    } catch (Exception e) {
      System.out.println("예외 발생:");
      e.printStackTrace();
      return -1;
    }
  }

  private void notFound(PrintStream out) throws IOException {
    out.println("FAIL");
    out.println("요청한 명령을 처리할 수 없습니다.");
  }

  private void quit(ObjectOutputStream out) throws IOException {
    out.writeUTF("OK");
    out.flush();
  }

  public static void main(String[] args) throws Exception {
    System.out.println("게임 관리 시스템 서버입니다.");
    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new DataLoaderListener());
    app.service();
  }

}
