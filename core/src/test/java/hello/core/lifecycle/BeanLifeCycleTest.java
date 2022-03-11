package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

     @Test
     public void lifeCylceTest() {
          ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
          NetworkClient bean = ac.getBean(NetworkClient.class);
          ac.close(); //ApplicationContext의 하위 클래스인 ConfigurableApplicationContext의 메서드
     }

     @Configuration
     static class LifeCycleConfig {

//          @Bean(initMethod = "init", destroyMethod = "close")
          @Bean
          public NetworkClient networkClient() {
               NetworkClient networkClient = new NetworkClient();
               networkClient.setUrl("http://hello-spring.dev");
               return networkClient;
          }
     }

}
