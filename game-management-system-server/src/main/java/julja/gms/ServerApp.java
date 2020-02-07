package julja.gms;

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

        if (request.equals("quit")) {
          out.writeUTF("OK");
          out.flush();
          break;
        }

        if (request.equals("/server/stop")) {
          out.writeUTF("OK");
          out.flush();
          return 9;
        }

        List<Game> gameList = (List<Game>) context.get("gameList");
        List<User> userList = (List<User>) context.get("userList");
        List<Board> boardList = (List<Board>) context.get("boardList");

        if (request.equals("/game/list")) {
          out.writeUTF("OK");
          out.reset();
          out.writeObject(gameList);

        } else if (request.equals("/lesson/list")) {
          out.writeUTF("OK");
          out.reset();
          out.writeObject(userList);

        } else if (request.equals("/member/list")) {
          out.writeUTF("OK");
          out.reset();
          out.writeObject(boardList);

        } else if (request.equals("/board/add")) {
          try {
            Board board = (Board) in.readObject();
            int i = 0;
            for (; i < boardList.size(); i++) {
              if (boardList.get(i).getNo() == board.getNo()) {
                break;
              }
            }
            if (i == boardList.size()) { // 같은 번호의 게시물이 없다면
              boardList.add(board); // 새 게시물 등록
              out.writeUTF("OK");
            } else {
              out.writeUTF("FAIL");
              out.writeUTF("같은 번호의 게시물이 있습니다.");
            }
          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }

        } else if (request.equals("/lesson/add")) {
          try {
            Game lesson = (Game) in.readObject();

            int i = 0;
            for (; i < gameList.size(); i++) {
              if (gameList.get(i).getNo() == lesson.getNo()) {
                break;
              }
            }
            if (i == gameList.size()) {
              gameList.add(lesson);
              out.writeUTF("OK");
            } else {
              out.writeUTF("FAIL");
              out.writeUTF("같은 번호의 수업이 있습니다.");
            }
          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }

        } else if (request.equals("/member/add")) {
          try {
            User member = (User) in.readObject();
            int i = 0;
            for (; i < userList.size(); i++) {
              if (userList.get(i).getNo() == member.getNo()) {
                break;
              }
            }
            if (i == userList.size()) {
              userList.add(member);
              out.writeUTF("OK");
            } else {
              out.writeUTF("FAIL");
              out.writeUTF("같은 번호의 회원이 있습니다.");
            }
          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }

        } else if (request.equals("/board/detail")) {
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

        } else if (request.equals("/lesson/detail")) {
          try {
            int no = in.readInt();
            Game lesson = null;
            for (Game l : gameList) {
              if (l.getNo() == no) {
                lesson = l;
                break;
              }
            }
            if (lesson != null) {
              out.writeUTF("OK");
              out.writeObject(lesson);
            } else {
              out.writeUTF("FAIL");
              out.writeUTF("해당 번호의 수업이 없습니다.");
            }
          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }

        } else if (request.equals("/member/detail")) {
          try {
            int no = in.readInt();
            User member = null;
            for (User m : userList) {
              if (m.getNo() == no) {
                member = m;
                break;
              }
            }
            if (member != null) {
              out.writeUTF("OK");
              out.writeObject(member);
            } else {
              out.writeUTF("FAIL");
              out.writeUTF("해당 번호의 회원이 없습니다.");
            }
          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }

        } else if (request.equals("/board/update")) {
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

        } else if (request.equals("/lesson/update")) {
          try {
            Game lesson = (Game) in.readObject();
            int index = -1;
            for (int i = 0; i < gameList.size(); i++) {
              if (gameList.get(i).getNo() == lesson.getNo()) {
                index = i;
                break;
              }
            }
            if (index != -1) {
              gameList.set(index, lesson);
              out.writeUTF("OK");
            } else {
              out.writeUTF("FAIL");
              out.writeUTF("해당 번호의 수업이 없습니다.");
            }
          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }

        } else if (request.equals("/member/update")) {
          try {
            User member = (User) in.readObject();
            int index = -1;
            for (int i = 0; i < userList.size(); i++) {
              if (userList.get(i).getNo() == member.getNo()) {
                index = i;
                break;
              }
            }
            if (index != -1) {
              userList.set(index, member);
              out.writeUTF("OK");
            } else {
              out.writeUTF("FAIL");
              out.writeUTF("해당 번호의 회원이 없습니다.");
            }
          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }

        } else if (request.equals("/board/delete")) {
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

        } else if (request.equals("/lesson/delete")) {
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

        } else if (request.equals("/member/delete")) {
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

        } else {
          out.writeUTF("FAIL");
          out.writeUTF("요청한 명령을 처리할 수 없습니다.");
        }
        out.flush();
      }
      System.out.println("클라이언트로 메시지를 전송하였음!");
      return 0;
    } catch (Exception e) {
      System.out.println("예외 발생:");
      e.printStackTrace();
      return -1;
    }
  }

  public static void main(String[] args) throws Exception {
    System.out.println("게임 관리 시스템 서버입니다.");
    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new DataLoaderListener());
    app.service();
  }

}
