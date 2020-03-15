package julja.gms;

import java.io.IOException;
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
import org.apache.ibatis.session.SqlSessionFactory;
import julja.gms.context.ApplicationContextListener;
import julja.gms.service.BoardService;
import julja.gms.service.GameService;
import julja.gms.service.PhotoBoardService;
import julja.gms.service.UserService;
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
import julja.gms.servlet.LoginServlet;
import julja.gms.servlet.PhotoBoardAddServlet;
import julja.gms.servlet.PhotoBoardDeleteServlet;
import julja.gms.servlet.PhotoBoardDetailServlet;
import julja.gms.servlet.PhotoBoardListServlet;
import julja.gms.servlet.PhotoBoardUpdateServlet;
import julja.gms.servlet.Servlet;
import julja.gms.servlet.UserAddServlet;
import julja.gms.servlet.UserDeleteServlet;
import julja.gms.servlet.UserDetailServlet;
import julja.gms.servlet.UserListServlet;
import julja.gms.servlet.UserSearchServlet;
import julja.gms.servlet.UserUpdateServlet;
import julja.sql.SqlSessionFactoryProxy;

public class ServerApp {

  Set<ApplicationContextListener> listeners = new HashSet<>();
  Map<String, Servlet> servletMap = new HashMap<>();
  ExecutorService executorService = Executors.newCachedThreadPool();
  Map<String, Object> context = new HashMap<>();
  boolean serverStop = false;

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

    GameService gameService = (GameService) context.get("gameService");
    UserService userService = (UserService) context.get("userService");
    BoardService boardService = (BoardService) context.get("boardService");
    PhotoBoardService photoBoardService = (PhotoBoardService) context.get("photoBoardService");
    SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) context.get("sqlSessionFactory");

    servletMap.put("/board/add", new BoardAddServlet(boardService));
    servletMap.put("/board/delete", new BoardDeleteServlet(boardService));
    servletMap.put("/board/detail", new BoardDetailServlet(boardService));
    servletMap.put("/board/list", new BoardListServlet(boardService));
    servletMap.put("/board/update", new BoardUpdateServlet(boardService));

    servletMap.put("/game/add", new GameAddServlet(gameService));
    servletMap.put("/game/delete", new GameDeleteServlet(gameService));
    servletMap.put("/game/detail", new GameDetailServlet(gameService));
    servletMap.put("/game/list", new GameListServlet(gameService));
    servletMap.put("/game/update", new GameUpdateServlet(gameService));
    servletMap.put("/game/search", new GameSearchServlet(gameService));

    servletMap.put("/auth/login", new LoginServlet(userService));

    servletMap.put("/user/add", new UserAddServlet(userService));
    servletMap.put("/user/delete", new UserDeleteServlet(userService));
    servletMap.put("/user/detail", new UserDetailServlet(userService));
    servletMap.put("/user/list", new UserListServlet(userService));
    servletMap.put("/user/update", new UserUpdateServlet(userService));
    servletMap.put("/user/search", new UserSearchServlet(userService));
    servletMap.put("/photoboard/list", new PhotoBoardListServlet(photoBoardService, gameService));
    servletMap.put("/photoboard/detail", new PhotoBoardDetailServlet(photoBoardService));
    servletMap.put("/photoboard/add", new PhotoBoardAddServlet(photoBoardService, gameService));
    servletMap.put("/photoboard/delete", new PhotoBoardDeleteServlet(photoBoardService));
    servletMap.put("/photoboard/update", new PhotoBoardUpdateServlet(photoBoardService));

    try (ServerSocket serverSocket = new ServerSocket(9999)) {
      System.out.println("클라이언트 연결 대기중...");
      while (true) {
        Socket socket = serverSocket.accept();
        System.out.println("클라이언트와 연결되었습니다.");

        executorService.submit(() -> {
          processRequest(socket);
          ((SqlSessionFactoryProxy) sqlSessionFactory).closeSession();
          System.out.println("-----------------------------------");
        });

        if (serverStop) {
          break;
        }

      }
    } catch (Exception e) {
      System.out.println("서버 준비 중 오류 발생");
    }

    executorService.shutdown();

    while (true) {
      if (executorService.isTerminated()) {
        break;
      }
      try {
        Thread.sleep(500); // 0.5초마다 스레드 종료 여부 검사
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    notifyApplicationDestroyed();

  }

  void processRequest(Socket clientSocket) {
    try (Socket socket = clientSocket;
        Scanner in = new Scanner(socket.getInputStream());
        PrintStream out = new PrintStream(socket.getOutputStream())) {

      String request = in.nextLine();
      System.out.printf("=> %s\n", request);


      if (request.equalsIgnoreCase("/server/stop")) {
        quit(out);
        return;
      }

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
    } catch (Exception e) {
      System.out.println("예외 발생:");
      e.printStackTrace();
    }
  }

  private void notFound(PrintStream out) throws IOException {
    out.println("FAIL");
    out.println("요청한 명령을 처리할 수 없습니다.");
  }

  private void quit(PrintStream out) throws IOException {
    serverStop = true;
    out.println("OK");
    out.println("!end!");
    out.flush();
  }

  public static void main(String[] args) throws Exception {
    System.out.println("게임 관리 시스템 서버입니다.");
    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new DataLoaderListener());
    app.service();
  }

}
