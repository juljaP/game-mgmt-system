package julja.gms;

import javax.sql.DataSource;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:julja/gms/conf/jdbc.properties")
public class MybatisConfig {

  static Logger logger = LogManager.getLogger(MybatisConfig.class);

  public MybatisConfig() {
    MybatisConfig.logger.info("MybatisConfig 객체 생성!");
  }

  @Bean
  public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ApplicationContext appCtx)
      throws Exception {
    // log4j 활성화
    LogFactory.useLog4JLogging();
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource);
    sqlSessionFactoryBean.setTypeAliasesPackage("julja.gms.domain");
    sqlSessionFactoryBean
        .setMapperLocations(appCtx.getResources("classpath:julja/gms/mapper/*Mapper.xml"));

    return sqlSessionFactoryBean.getObject();
  }
}
