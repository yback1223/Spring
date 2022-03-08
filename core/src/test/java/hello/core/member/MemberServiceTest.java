package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

     MemberService memberService;

     @BeforeEach
     public void beforeEach() {
          AppConfig appConfig = new AppConfig();
          memberService = appConfig.memberService();
     }

//     MemberService memberService = new MemberServiceImpl();

     //test code는 필수
     @Test
     void join() {

          //given
          Member member = new Member(1L, "memberA", Grade.VIP);

          //when
          memberService.join(member);
          Member findMember = memberService.findMember(1L);

          //then, 검증은 Assertions
          Assertions.assertThat(member).isEqualTo(findMember);

     }
}
