package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
//이렇게 하면 MyLogger의 가짜 프록시 클래스를 만들어두고 HTTP request와 상관없이 가짜 프록시 클래스를 다른 빈에 미리 주입해 둘 수 있다.
public class MyLogger {

     private String uuid;
     private String requestURL;

     public void setRequestURL(String requestURL) {
          this.requestURL = requestURL;
     }

     public void log(String message) {
          System.out.println("[" + uuid + "][" + requestURL + "] " + message);
     }

     @PostConstruct
     public void init() {
          uuid = UUID.randomUUID().toString(); //unique한 ID 생성
          System.out.println("[" + uuid + "] request scope bean create: " + this);
     }

     @PreDestroy
     public void close() {
          System.out.println("[" + uuid + "] request scope bean close: " + this);

     }
}
