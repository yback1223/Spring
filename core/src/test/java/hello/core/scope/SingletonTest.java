package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

     @Test
     void singletonBeanFind() {
          AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);//SingletonBean을 여기 넣어주면 자동으로 componentscan이 돼서 스프링빈 등록이 된다.
          SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
          SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);

          System.out.println("singletonBean1 = " + singletonBean1);
          System.out.println("singletonBean2 = " + singletonBean2);

          assertThat(singletonBean1).isSameAs(singletonBean2);

          ac.close();
     }

     @Scope("singleton")
     static class SingletonBean {

          @PostConstruct
          public void init() {
               System.out.println("SingletonBean.init");
          }

          @PreDestroy
          public void destroy() {
               System.out.println("SingletonBean.destroy");
          }
     }
}
