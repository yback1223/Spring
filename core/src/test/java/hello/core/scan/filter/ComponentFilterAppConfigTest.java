package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.context.annotation.ComponentScan.*;

public class ComponentFilterAppConfigTest {

     @Test
     void filterScan() {
          ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
          BeanA beanA = ac.getBean("beanA", BeanA.class);
          assertThat(beanA).isNotNull();

          assertThrows(NoSuchBeanDefinitionException.class,
                  () -> ac.getBean("beanB", BeanB.class));
     }

     @Configuration
     @ComponentScan(
             includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class), //ANNOTATION이 기본값, 없어도 잘 동작한다.
             excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
             //includeFilters: ComponentScan 대상에 포함
             //excludeFilters: ComponentScan 대상에서 제외
     )
     static class ComponentFilterAppConfig {
     }
}
