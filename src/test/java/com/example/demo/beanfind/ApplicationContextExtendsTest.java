package com.example.demo.beanfind;

import com.example.demo.AppConfig;
import com.example.demo.discount.DiscountPolicy;
import com.example.demo.discount.FixDiscountPolicy;
import com.example.demo.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextExtendsTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회 시 , 자식이 둘 이상 있으면, 중복 오류가 발생한다.")
    void findBeanByParentTypeDuplicate() {
        assertThrows(NoUniqueBeanDefinitionException.class, () -> {
            ac.getBean(DiscountPolicy.class);
        });

    }
    @Test
    @DisplayName("부모 타입으로 조회 시 , 자식이 둘 이상 있으면, 빈 이름을 지정하면 된다 ")
    void findBeanByParentTypeBeanName() {
        DiscountPolicy discountPolicy = ac.getBean("fixDiscountPolicy", DiscountPolicy.class);
        assertThat(discountPolicy).isInstanceOf(FixDiscountPolicy.class);

    }
    @Test
    @DisplayName("특정 하위 타입으로 조회")
    void findBeanBySubType() {
        DiscountPolicy discountPolicy = ac.getBean(FixDiscountPolicy.class);
        assertThat(discountPolicy).isInstanceOf(FixDiscountPolicy.class);
    }
    @Test
    @DisplayName("부모 타임으로 모두 조회하기")
    void findAllBeanByParentType() {
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        assertThat(beansOfType.size()).isEqualTo(2);
    }
    @Test
    @DisplayName("부모 타임으로 모두 조회하기 -> Object")
    void findAllBeanByObjectType() {
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for (String s : beansOfType.keySet()) {
            System.out.println("name = " + s);
        }
    }

    @Configuration
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
