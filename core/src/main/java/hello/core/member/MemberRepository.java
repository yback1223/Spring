package hello.core.member;

public interface MemberRepository { //회원 저장소를 만들었으면 구현체를 만들어야 한다.
     void save(Member member); //회원 저장

     Member findById(Long memberId); //id로 회원 찾기
}
