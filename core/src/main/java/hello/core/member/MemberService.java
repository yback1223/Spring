package hello.core.member;

public interface MemberService {
     //MemberService에는 가입과 조회 기능이 있어야 한다.

     void join(Member member); //가입

     Member findMember(Long memberId); //조회
}
