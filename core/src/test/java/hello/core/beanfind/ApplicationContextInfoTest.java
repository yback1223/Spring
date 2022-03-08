package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//JUnit5부터는 제어자 필요없다.
class ApplicationContextInfoTest {
     AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

     @Test
     @DisplayName("모든 빈 출력하기")
     void findAllBean() {
          String[] beanDefinitionNames = ac.getBeanDefinitionNames();
          for (String beanDefinitionName : beanDefinitionNames) {
               Object bean = ac.getBean(beanDefinitionName);
               System.out.println("beanDefinitionName = " + beanDefinitionName + ", object = " + bean);
          }
     }

     @Test
     @DisplayName("모든 Application빈 출력하기")
     void findApplicationBean() {
          String[] beanDefinitionNames = ac.getBeanDefinitionNames();
          for (String beanDefinitionName : beanDefinitionNames) {
               //getBeanDefinition 빈에 대한 메타 정보를 가져온다.
               BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

               //getRoled이 ROLE_APPLICATION이면 우리가 직접 등록한 빈만 해당된다.
               //ROLE_APPLICATION : 직접 등록한 애플리케이션 빈
               //ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
               if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                    Object bean = ac.getBean(beanDefinitionName);
                    System.out.println("beanDefinitionName = " + beanDefinitionName + ", object = " + bean);
               }
          }
     }
}
