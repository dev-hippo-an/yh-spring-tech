package anpopo.yhcorebasic.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        statefulService1.order("user1", 10000);
        statefulService2.order("user2", 20000);

        System.out.println(statefulService1.getPrice());
        System.out.println(statefulService2.getPrice());
    }

    static class TestConfig{


        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}