package anpopo.yhcorebasic.orders;

import anpopo.yhcorebasic.member.Grade;
import anpopo.yhcorebasic.member.Member;
import anpopo.yhcorebasic.member.MemberService;
import anpopo.yhcorebasic.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {


    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrdersServiceImpl();

    @Test
    void createOrder() {
        Long memberId = 1L;

        Member member = new Member(memberId, "member", Grade.VIP);

        memberService.join(member);

        Orders order = orderService.createOrder(member.getId(), "item", 10000);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}