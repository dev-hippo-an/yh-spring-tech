package anpopo.yhcorebasic.config;

import anpopo.yhcorebasic.discount.DiscountPolicy;
import anpopo.yhcorebasic.discount.FixDiscountPolicy;
import anpopo.yhcorebasic.discount.RateDiscountPolicy;
import anpopo.yhcorebasic.member.MemberRepository;
import anpopo.yhcorebasic.member.MemberService;
import anpopo.yhcorebasic.member.MemberServiceImpl;
import anpopo.yhcorebasic.member.MemoryMemberRepository;
import anpopo.yhcorebasic.orders.OrderService;
import anpopo.yhcorebasic.orders.OrdersServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 사용 영역의 코드가 아닌 구성 영역의 코드의 변경으로 기능의 동작을 변경할 수 있다.
 */

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService() {
        return new OrdersServiceImpl(memberRepository(), discountPolicy());
    }
    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
