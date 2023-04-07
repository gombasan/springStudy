package com.example.demo.scope;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class SingletonWithPrototypeTest1 {

	@Test
	void prototypeFine() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
			PrototypeBean.class);
		PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
		PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

		prototypeBean1.addCount();
		prototypeBean2.addCount();

		assertThat(prototypeBean1.getCount()).isEqualTo(prototypeBean2.getCount());

	}

	@Test
	void singletonClientUsePrototype() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
			ClientBean.class,PrototypeBean.class);
		ClientBean clientBean1 = ac.getBean(ClientBean.class);
		ClientBean clientBean2 = ac.getBean(ClientBean.class);

		int count1 = clientBean1.logic();
		assertThat(count1).isEqualTo(1);
		int count2 = clientBean2.logic();
		assertThat(count2).isEqualTo(1);

	}

	@Scope("prototype")
	static class PrototypeBean {
		private int count = 0;


		public void addCount() {
			count++;
		}

		public int getCount() {
			return this.count;
		}
		@PostConstruct
		public void init() {
			System.out.println("PrototypeBean.init" + this);
		}
		@PreDestroy
		public void destroy() {
			System.out.println("PrototypeBean.destroy" + this);
		}
	}

	@Scope("singleton")
	static class ClientBean {
		@Autowired
		private ObjectProvider<PrototypeBean> prototypeBeanProvider;


		public int logic() {
			PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
			prototypeBean.addCount();
			return prototypeBean.getCount();
		}

	}
}
