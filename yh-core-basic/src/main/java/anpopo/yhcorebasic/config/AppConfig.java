package anpopo.yhcorebasic.config;

import anpopo.yhcorebasic.discount.DiscountPolicy;
import anpopo.yhcorebasic.discount.FixDiscountPolicy;
import anpopo.yhcorebasic.member.MemberRepository;
import anpopo.yhcorebasic.member.MemberService;
import anpopo.yhcorebasic.member.MemberServiceImpl;
import anpopo.yhcorebasic.member.MemoryMemberRepository;
import anpopo.yhcorebasic.orders.OrderService;
import anpopo.yhcorebasic.orders.OrdersServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrdersServiceImpl(memberRepository(), discountPolicy());
    }

    private DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}
