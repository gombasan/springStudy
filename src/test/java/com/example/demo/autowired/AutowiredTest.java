package com.example.demo.autowired;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.demo.member.Member;

import jakarta.annotation.Nullable;

public class AutowiredTest {

	@Test
	void AutowiredOption() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
			TestBean.class);

	}

	static class TestBean {

		@Autowired(required = false)  // false 로 하면 함수 호출 자체가 안된다.
		public void setNoBean1(Member noBean1) {
			System.out.println("noBean1 = " + noBean1);
		}

		@Autowired  //@Nullable 로 설정하면 Null로 반환.
		public void setNoBean2(@Nullable Member noBean2) {
			System.out.println("noBean2 = " + noBean2);
		}

		@Autowired  // Optional 을 사용하면 Optional.empty로 반환
		public void setNoBean3(Optional<Member> noBean3) {
			System.out.println("noBean3 = " + noBean3);
		}
	}
}
