package anpopo.yhcorebasic.beanfind;

import anpopo.yhcorebasic.config.AppConfig;
import anpopo.yhcorebasic.discount.DiscountPolicy;
import anpopo.yhcorebasic.member.MemberRepository;
import anpopo.yhcorebasic.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);


    @Test
    @DisplayName("타입으로 조회시 같은 타입이 있으면 중복 오류가 발생한다.")
    void findBeanByTypeDuplicate() {

        Assertions.assertThatThrownBy(() -> ac.getBean(MemberRepository.class))
                .isInstanceOf(NoUniqueBeanDefinitionException.class)
                .hasMessageContaining("No");
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 있으면 빈이름을 지정하면 된다.")
    void findBeanByType() {
        MemberRepository memberRepository1 = ac.getBean("memberRepository1", MemberRepository.class);

        Assertions.assertThat(memberRepository1).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입 모두 조회")
    void findAllByType() {
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);

        for (Map.Entry<String, MemberRepository> stringMemberRepositoryEntry : beansOfType.entrySet()) {
            System.out.println(String.format("key: %s, value: %s", stringMemberRepositoryEntry.getKey(), stringMemberRepositoryEntry.getValue()));
        }
        System.out.println("beansOfType = " + beansOfType);
        Assertions.assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Configuration
    static class SameBeanConfig {
        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }
}
