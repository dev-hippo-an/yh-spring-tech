package anpopo.yhcorebasic.orders;

import org.springframework.core.annotation.Order;

public interface OrderService {

    Orders createOrder(Long memberId, String itemName, int itemPrice);
}
