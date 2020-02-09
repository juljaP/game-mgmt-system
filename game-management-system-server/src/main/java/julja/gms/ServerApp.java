package julja.gms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
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

  List<Game> gameList;
  List<User> userList;
  List<Board> boardList;

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

    try (ServerSocket serverSocket = new ServerSocket(9999)) {// 9999포트 클라이언트 연결 대기
      System.out.println("클라이언트 연결 대기중...");
      while (true) {
        Socket socket = serverSocket.accept();
        System.out.println("클라이언트와 연결되었습니다.");
        if (processRequest(socket) == 9) {
          break;
        }
      }
    } catch (Exception e) {
      System.out.println("서버 준비 중 오류 발생");
    }

    notifyApplicationDestroyed();

  }

  @SuppressWarnings("unchecked")
  int processRequest(Socket clientSocket) {
    try (Socket socket = clientSocket;
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {
      System.out.println("통신 입출력 스트림 준비완료..");

      while (true) {
        String request = in.readUTF();
        System.out.println("클라이언트가 보낸 메시지를 수신하였습니다.");

        gameList = (List<Game>) context.get("gameList");
        userList = (List<User>) context.get("userList");
        boardList = (List<Board>) context.get("boardList");

        switch (request) {
          case "quit":
            out.writeUTF("OK");
            out.flush();
            break;
          case "/server/stop":
            out.writeUTF("OK");
            out.flush();
            return 9;
          case "/game/list":
            listGame(out);
            break;
          case "/user/list":
            listUser(out);
            break;
          case "/board/list":
            listBoard(out);
            break;
          case "/game/add":
            addGame(in, out);
            break;
          case "/user/add":
            addUser(in, out);
            break;
          case "/board/add":
            addBoard(in, out);
            break;
          case "/game/detail":
            detailGame(in, out);
            break;
          case "/user/detail":
            detailUser(in, out);
            break;
          case "/board/detail":
            detailBoard(in, out);
            break;
          case "/game/update":
            updateGame(in, out);
            break;
          case "/user/update":
            updateUser(in, out);
            break;
          case "/board/update":
            updateBoard(in, out);
            break;
          case "/game/delete":
            deleteGame(in, out);
            break;
          case "/user/delete":
            deleteUser(in, out);
            break;
          case "/board/delete":
            deleteBoard(in, out);
            break;
          default:
            notFound(out);
            break;
        }

        out.flush();
        System.out.println("클라이언트로 메시지를 전송하였음!");
      }
    } catch (Exception e) {
      System.out.println("예외 발생:");
      e.printStackTrace();
      return -1;
    }
  }


  private void notFound(ObjectOutputStream out) throws IOException {
    out.writeUTF("FAIL");
    out.writeUTF("요청한 명령을 처리할 수 없습니다.");
  }

  private void deleteBoard(ObjectInputStream in, ObjectOutputStream out) throws IOException {
    try {
      int no = in.readInt();
      int index = -1;
      for (int i = 0; i < boardList.size(); i++) {
        if (boardList.get(i).getNo() == no) {
          index = i;
          break;
        }
      }
      if (index != -1) {
        boardList.remove(index);
        out.writeUTF("OK");
      } else {
        out.writeUTF("FAIL");
        out.writeUTF("해당 번호의 게시물이 없습니다.");
      }
    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF(e.getMessage());
    }
  }

  private void deleteUser(ObjectInputStream in, ObjectOutputStream out) throws IOException {
    try {
      int no = in.readInt();
      int index = -1;
      for (int i = 0; i < userList.size(); i++) {
        if (userList.get(i).getNo() == no) {
          index = i;
          break;
        }
      }
      if (index != -1) {
        userList.remove(index);
        out.writeUTF("OK");
      } else {
        out.writeUTF("FAIL");
        out.writeUTF("해당 번호의 회원이 없습니다.");
      }
    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF(e.getMessage());
    }
  }

  private void deleteGame(ObjectInputStream in, ObjectOutputStream out) throws IOException {
    try {
      int no = in.readInt();

      int index = -1;
      for (int i = 0; i < gameList.size(); i++) {
        if (gameList.get(i).getNo() == no) {
          index = i;
          break;
        }
      }
      if (index != -1) {
        gameList.remove(index);
        out.writeUTF("OK");
      } else {
        out.writeUTF("FAIL");
        out.writeUTF("해당 번호의 게시물이 없습니다.");
      }
    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF(e.getMessage());
    }
  }

  private void updateBoard(ObjectInputStream in, ObjectOutputStream out) throws IOException {
    try {
      Board board = (Board) in.readObject();
      int index = -1;
      for (int i = 0; i < boardList.size(); i++) {
        if (boardList.get(i).getNo() == board.getNo()) {
          index = i;
          break;
        }
      }

      if (index != -1) {
        boardList.set(index, board);
        out.writeUTF("OK");
      } else {
        out.writeUTF("FAIL");
        out.writeUTF("해당 번호의 게시물이 없습니다.");
      }

    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF(e.getMessage());
    }
  }

  private void updateUser(ObjectInputStream in, ObjectOutputStream out) throws IOException {
    try {
      User user = (User) in.readObject();
      int index = -1;
      for (int i = 0; i < userList.size(); i++) {
        if (userList.get(i).getNo() == user.getNo()) {
          index = i;
          break;
        }
      }
      if (index != -1) {
        userList.set(index, user);
        out.writeUTF("OK");
      } else {
        out.writeUTF("FAIL");
        out.writeUTF("해당 번호의 회원이 없습니다.");
      }
    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF(e.getMessage());
    }
  }

  private void updateGame(ObjectInputStream in, ObjectOutputStream out) throws IOException {
    try {
      Game game = (Game) in.readObject();
      int index = -1;
      for (int i = 0; i < gameList.size(); i++) {
        if (gameList.get(i).getNo() == game.getNo()) {
          index = i;
          break;
        }
      }
      if (index != -1) {
        gameList.set(index, game);
        out.writeUTF("OK");
      } else {
        out.writeUTF("FAIL");
        out.writeUTF("해당 번호의 수업이 없습니다.");
      }
    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF(e.getMessage());
    }
  }

  private void detailBoard(ObjectInputStream in, ObjectOutputStream out) throws IOException {
    try {
      int no = in.readInt();
      Board board = null;
      for (Board b : boardList) {
        if (b.getNo() == no) {
          board = b;
          break;
        }
      }

      if (board != null) {
        out.writeUTF("OK");
        out.writeObject(board);
      } else {
        out.writeUTF("FAIL");
        out.writeUTF("해당 번호의 게시물이 없습니다.");
      }
    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF(e.getMessage());
    }
  }

  private void detailUser(ObjectInputStream in, ObjectOutputStream out) throws IOException {
    try {
      int no = in.readInt();
      User user = null;
      for (User m : userList) {
        if (m.getNo() == no) {
          user = m;
          break;
        }
      }
      if (user != null) {
        out.writeUTF("OK");
        out.writeObject(user);
      } else {
        out.writeUTF("FAIL");
        out.writeUTF("해당 번호의 회원이 없습니다.");
      }
    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF(e.getMessage());
    }
  }

  private void detailGame(ObjectInputStream in, ObjectOutputStream out) throws IOException {
    try {
      int no = in.readInt();
      Game game = null;
      for (Game l : gameList) {
        if (l.getNo() == no) {
          game = l;
          break;
        }
      }
      if (game != null) {
        out.writeUTF("OK");
        out.writeObject(game);
      } else {
        out.writeUTF("FAIL");
        out.writeUTF("해당 번호의 수업이 없습니다.");
      }
    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF(e.getMessage());
    }
  }

  private void addBoard(ObjectInputStream in, ObjectOutputStream out) throws IOException {
    try {
      Board board = (Board) in.readObject();
      int i = 0;
      for (; i < boardList.size(); i++) {
        if (boardList.get(i).getNo() == board.getNo()) {
          break;
        }
      }
      if (i == boardList.size()) {
        boardList.add(board);
        out.writeUTF("OK");
      } else {
        out.writeUTF("FAIL");
        out.writeUTF("같은 번호의 게시물이 있습니다.");
      }
    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF(e.getMessage());
    }
  }

  private void addUser(ObjectInputStream in, ObjectOutputStream out) throws IOException {
    try {
      User user = (User) in.readObject();
      int i = 0;
      for (; i < userList.size(); i++) {
        if (userList.get(i).getNo() == user.getNo()) {
          break;
        }
      }
      if (i == userList.size()) {
        userList.add(user);
        out.writeUTF("OK");
      } else {
        out.writeUTF("FAIL");
        out.writeUTF("같은 번호의 회원이 있습니다.");
      }
    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF(e.getMessage());
    }
  }

  private void addGame(ObjectInputStream in, ObjectOutputStream out) throws IOException {
    try {
      Game game = (Game) in.readObject();

      int i = 0;
      for (; i < gameList.size(); i++) {
        if (gameList.get(i).getNo() == game.getNo()) {
          break;
        }
      }
      if (i == gameList.size()) {
        gameList.add(game);
        out.writeUTF("OK");
      } else {
        out.writeUTF("FAIL");
        out.writeUTF("같은 번호의 수업이 있습니다.");
      }
    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF(e.getMessage());
    }
  }

  private void listBoard(ObjectOutputStream out) throws IOException {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(boardList);
  }

  private void listUser(ObjectOutputStream out) throws IOException {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(userList);
  }

  private void listGame(ObjectOutputStream out) throws IOException {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(gameList);
  }

  public static void main(String[] args) throws Exception {
    System.out.println("게임 관리 시스템 서버입니다.");
    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new DataLoaderListener());
    app.service();
  }

}
