package hello.core.beanfind;

import java.util.Objects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;

public class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames(); // 스프링에 등록된 모든 빈 이름 조회

        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                // ROLE_APPLICATION : 일반적으로 사용자가 정의한 빈
                // ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
                
                Object bean = ac.getBean(beanDefinitionName);   // 빈 이름으로 빈 조회
                System.out.println("name = " + beanDefinitionName + " object = " + bean);
            }
        }
    }
}
