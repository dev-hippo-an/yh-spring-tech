package anpopo.yhcorebasic.beanfind;

import anpopo.yhcorebasic.config.AppConfig;
import anpopo.yhcorebasic.discount.DiscountPolicy;
import anpopo.yhcorebasic.discount.FixDiscountPolicy;
import anpopo.yhcorebasic.discount.RateDiscountPolicy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @DisplayName("부모 타입으로 조회시 자식이 둘 이상있으면 중복 오류")
    @Test
    void findBeanByParentTypeDuplicate() {
        Assertions.assertThatThrownBy(() -> ac.getBean(DiscountPolicy.class))
                .isInstanceOf(NoUniqueBeanDefinitionException.class);
    }

    @DisplayName("부모 타입으로 조회시 자식이 둘 이상있으면 빈 이름을 지정하면 된다.")
    @Test
    void findBeanByParentTypeBeanName() {
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        Assertions.assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @DisplayName("부모 타입으로 조회시 자식이 둘 이상있으면 특정 하위 타입으로 조회하면 된다.")
    @Test
    void findBeanBySubType() {
        DiscountPolicy rateDiscountPolicy = ac.getBean(RateDiscountPolicy.class);
        Assertions.assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @DisplayName("부모 타입으로 모두 조회")
    @Test
    void findAllBeanByParentType() {
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);

        for (Map.Entry<String, DiscountPolicy> stringDiscountPolicyEntry : beansOfType.entrySet()) {
            System.out.println("key : " + stringDiscountPolicyEntry.getKey() + " / value : " + stringDiscountPolicyEntry.getValue());
        }

        Assertions.assertThat(beansOfType.size()).isEqualTo(2);
    }

    @DisplayName("부모 타입으로 모두 조회 (Object)")
    @Test
    void findAllBeanByObjectType() {
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);

        for (Map.Entry<String, Object> entry : beansOfType.entrySet()) {
            System.out.println("key : " + entry.getKey() + " / value : " + entry.getValue());
        }

    }



    static class TestConfig {
        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }
}
