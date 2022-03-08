package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
     public static void main(String[] args) {
//          AppConfig appConfig = new AppConfig();
          //원래는 이 main에서 객체 생서을 했지만 이제는 AppConfig를 이용한다.
//          MemberService memberService = appConfig.memberService(); //memberService()를 호출하고 변수 할당하면 그 변수에는 MemberServiceImpl이 들어있다.
//          MemberService memberService = new MemberServiceImpl();

          ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); //@Configuration을 찾아온다.
          MemberService memberService = applicationContext.getBean("memberService", MemberService.class);//빈을 가져온다. 이름(보통 메서드 이름으로), 타입


          Member member = new Member(1L, "memberA", Grade.VIP);
          memberService.join(member);

          Member findMember = memberService.findMember(1L);
          System.out.println("new member = " + member.getName());
          System.out.println("find Member = " + findMember.getName());
     }
}
