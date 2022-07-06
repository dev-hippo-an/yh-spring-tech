package anpopo.yhcorebasic;

import anpopo.yhcorebasic.config.AppConfig;
import anpopo.yhcorebasic.member.Grade;
import anpopo.yhcorebasic.member.Member;
import anpopo.yhcorebasic.member.MemberService;
import anpopo.yhcorebasic.member.MemberServiceImpl;
import anpopo.yhcorebasic.orders.OrderService;
import anpopo.yhcorebasic.orders.Orders;
import anpopo.yhcorebasic.orders.OrdersServiceImpl;

public class OrderApp {

    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
//        MemberService memberService = new MemberServiceImpl();
//        OrderService orderService = new OrdersServiceImpl();

        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        Long memberId = 1L;
        Member member1 = new Member(memberId, "member1", Grade.VIP);
        memberService.join(member1);

        Orders order = orderService.createOrder(memberId, "item", 10000);
        System.out.println("order = " + order);

    }
}
