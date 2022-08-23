package anpopo.yhcorebasic.singleton;

import anpopo.yhcorebasic.config.AppConfig;
import anpopo.yhcorebasic.member.MemberRepository;
import anpopo.yhcorebasic.member.MemberService;
import anpopo.yhcorebasic.member.MemberServiceImpl;
import anpopo.yhcorebasic.orders.OrdersServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("스프링이 없는 순수한 di 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        Assertions.assertThat(memberService1).isNotSameAs(memberService2);

    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        SingletonService instance1 = SingletonService.getInstance();
        SingletonService instance2 = SingletonService.getInstance();
        Assertions.assertThat(instance1).isSameAs(instance2);

    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }


    @Test
    void singletonDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrdersServiceImpl orderService = ac.getBean("orderService", OrdersServiceImpl.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();
        MemberRepository memberRepository3 = ac.getBean(MemberRepository.class);

        System.out.println(memberRepository1);
        System.out.println(memberRepository2);
        System.out.println(memberRepository3);


        Assertions.assertThat(memberRepository1).isSameAs(memberRepository3);
        Assertions.assertThat(memberRepository2).isSameAs(memberRepository3);
    }

    /**
     * 바이트 코드 조작 라이브러리인 CGLIB 을 사용해서 실제 AppConfig 클래스를 상속받는 임의의 객체 생성
     * 해당 객체에서 appConfig 의 @Bean 등록 메서드를 재정의
     * 스프링 컨테이너에서 인스턴스가 있는 경우 해당 인스턴스를 반환
     * 그렇지 않은 경우 super 의 메소드 실행 정도로 코드 예상
     *
     */
    @Test
    void appConfigDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // npopo.yhcorebasic.config.AppConfig$$EnhancerBySpringCGLIB$$3a8d1718@73173f63
        System.out.println(ac.getBean(AppConfig.class));


    }
}
