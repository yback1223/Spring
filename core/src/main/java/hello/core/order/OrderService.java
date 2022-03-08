package hello.core.order;

public interface OrderService {
//     최종 order 결과를 반환한다. 세 파라미터를 넘기면 최종 가격을 반환해야 하는 것
     Order createOrder(Long memberId, String itemName, int itemPrice);
}
