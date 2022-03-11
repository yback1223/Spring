package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {

     @Test
     void prototypeBeanFind() {
          AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
          System.out.println("find prototypeBean1");
          PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
          System.out.println("find prototypeBean2");
          PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
          System.out.println("prototypeBean1 = " + prototypeBean1);
          System.out.println("prototypeBean2 = " + prototypeBean2);

          assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

          //만약 클라이언트측(PrototypeTest)에서 수작업으로 닫으려면 다음과 같이 하면 된다.
//          prototypeBean1.destroy();
//          prototypeBean2.destroy();

          ac.close(); //close에 대한 출력물이 나오지 않는다. -> prototype이라서 이미 만들고 버렸기 때문

     }

     @Scope("prototype")
     static class PrototypeBean {
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
