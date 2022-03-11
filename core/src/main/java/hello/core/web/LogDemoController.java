package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

     private final LogDemoService logDemoService;
     private final MyLogger myLogger;
//     private final ObjectProvider<MyLogger> myLoggerProvider; //MyLogger를 주입받는게 아니라 찾는 DL

     @RequestMapping("log-demo")
     @ResponseBody //문자를 그대로 보낼 수 있다.(String)
     public String logDemo(HttpServletRequest request) { // 고객 요청 정보를 받을 수 있다.
          String requestURL = request.getRequestURL().toString();
//          MyLogger myLogger = myLoggerProvider.getObject(); //스프링 컨테이너에 요청하는 것을 지연하는 것
          myLogger.setRequestURL(requestURL); //생성한 URL을 myLogger에 저장

          myLogger.log("controller test");
          logDemoService.logic("testId");
          return "OK";
     }
}
