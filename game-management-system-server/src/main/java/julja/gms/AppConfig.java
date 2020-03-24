package julja.gms;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(value = "julja.gms")

public class AppConfig {

  static Logger logger = LogManager.getLogger(AppConfig.class);

  public AppConfig() {
    logger.info("AppConfig 객체 생성!");
  }

}
