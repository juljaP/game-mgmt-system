package julja.gms;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import julja.gms.context.ApplicationContextListener;
import julja.gms.servlet.Servlet;

public class ServerApp {

  static Logger logger = LogManager.getLogger(ServerApp.class);

  Set<ApplicationContextListener> listeners = new HashSet<>();
  ExecutorService executorService = Executors.newCachedThreadPool();
  Map<String, Object> context = new HashMap<>();
  boolean serverStop = false;
  ApplicationContext iocContainer;

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
    iocContainer = (ApplicationContext) context.get("iocContainer");


    try (ServerSocket serverSocket = new ServerSocket(9999)) {
      ServerApp.logger.info("클라이언트 연결 대기중...");
      while (true) {
        Socket socket = serverSocket.accept();
        ServerApp.logger.info("클라이언트와 연결되었습니다.");

        executorService.submit(() -> {
          processRequest(socket);
          ServerApp.logger.info("-----------------------------------");
        });

        if (serverStop) {
          break;
        }

      }
    } catch (Exception e) {
      ServerApp.logger.error(String.format("서버 준비 중 오류 발생: %s", e.getMessage()));
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
      ServerApp.logger.info(String.format("=> %s\n", request));


      if (request.equalsIgnoreCase("/server/stop")) {
        quit(out);
        return;
      }

      Servlet servlet = (Servlet) iocContainer.getBean(request);
      if (servlet != null) {
        try {
          servlet.service(in, out);
        } catch (Exception e) {
          out.println("요청 처리 중 오류 발생!");
          out.println(e.getMessage());
          
          logger.info("클라이언트 요청 처리 중 오류발생: ");
          logger.info(e.getMessage());
          StringWriter strWriter = new StringWriter();
          e.printStackTrace(new PrintWriter(strWriter));
          logger.debug(strWriter.toString());
        }
      } else {
        notFound(out);
      }
      out.println("!end!");
      out.flush();
    } catch (Exception e) {
      logger.error(String.format("예외 발생: %s", e.getMessage()));
      StringWriter strWriter = new StringWriter();
      e.printStackTrace(new PrintWriter(strWriter));
      logger.debug(strWriter.toString());
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
    ServerApp.logger.info("게임 관리 시스템 서버입니다.");
    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new ContextLoaderListener());
    app.service();
  }

}
