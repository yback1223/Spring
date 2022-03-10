package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core.member", //이 위치에서부터 이 위치의 하위패키지만 찾아 들어간다.(이렇게 되면 member만 ComponentScan의 대상이 된다.)
        basePackageClasses = AutoAppConfig.class, //이렇게 설정하면 AutoAppConfig의 package를 찾아 들어간다.
        //근데 basePackage를 지정하지 않아도 어차피 default 값이 이 ComponentScan이 붙어있는 클래스가 속한 패키지이기 때문에
        //이 패키지만 다 뒤진다.
        excludeFilters= @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        //AppConfig도 @Component가 자동으로 붙어있기 때문에 필터로 제외시켜주는 것
)
public class AutoAppConfig {
}
