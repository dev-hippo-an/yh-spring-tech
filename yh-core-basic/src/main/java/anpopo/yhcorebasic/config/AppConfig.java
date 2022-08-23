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

/**
 * @Configuration 이 없는 경우
 * CGLIB 을 통한 바이트 코드 조작이 없이 빈을 생성하기 때문에 싱글톤 보장이 되지 않음 -> 실제적으로 호출되는 횟수만큼 메소드 실행
 * 또한 스프링 컨테이너에서 관리되는 빈을 주입해주는 것이 아니기 때문에 빈이 스프링 컨테이너의 관리를 받을 수 없음.
 *
 * 고로 @Configuration 을 사용해서 @Bean 을 등록하자
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
