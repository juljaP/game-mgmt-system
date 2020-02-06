package julja.context;

import java.util.Map;

public interface ApplicationContextListener {

  void contextInitailized(Map<String, Object> map);

  void contextDestroyed(Map<String, Object> map);

}
