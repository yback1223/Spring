package hello.core.singleton;

//객체를 미리 만들어두는 가장 단순하고 안전한 방법
public class SingletonService {
     //JVM이 자바가 시작하면 일단 이 객체를 생성하고 instace에 할당시킨다.
     private static final SingletonService instance = new SingletonService();

     //SingletonService객체를 사용하기 위해서는 getInstance() 메소드로 접근해야 한다.
     public static SingletonService getInstance() {
          return instance;
     }

     private SingletonService() {
          //다른 곳에서 SingletonService로 생성자를 생성하지 못하게 막는다.
     }

     public void logic() {
          System.out.println("싱글톤 로직 호출");
     }
}
