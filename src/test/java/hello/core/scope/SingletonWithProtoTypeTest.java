package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;

public class SingletonWithProtoTypeTest {
	@Test
	void prototypeFind() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
		PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
		prototypeBean1.addCount();
		Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);
		PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
		prototypeBean2.addCount();
		Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1);
	}


	@Test
	void singletonClientUsePrototype() {
		AnnotationConfigApplicationContext ac = new
			AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

		ClientBean clientBean1 = ac.getBean(ClientBean.class);
		int count1 = clientBean1.logic();
		Assertions.assertThat(count1).isEqualTo(1);

		ClientBean clientBean2 = ac.getBean(ClientBean.class);
		int count2 = clientBean2.logic();
		Assertions.assertThat(count2).isEqualTo(1);
	}

	@Scope("singleton")
	static class ClientBean {
		// private final PrototypeBean prototypeBean; //생성 시점에서 주입

		private Provider<PrototypeBean> prototypeBeanProvider;

		// public ClientBean(PrototypeBean prototypeBean) {
		// 	this.prototypeBean = prototypeBean;
		// }

		// ApplicationContext applicationContext;

		public int logic() {
			// PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class);
			// prototypeBean.addCount();
			PrototypeBean prototypeBean = prototypeBeanProvider.get();
			prototypeBean.addCount();
			int count = prototypeBean.getCount();
			return count;
		}
	}
//커맨드 옵션 n -> 합치는 기능
	@Scope("prototype")
	static class PrototypeBean {
		private int count = 0;
		public void addCount() {
			count++;
		}
		public int getCount() {
			return count;
		}
		@PostConstruct
		public void init() {
			System.out.println("PrototypeBean.init " + this);
		}
		@PreDestroy
		public void destroy() {
			System.out.println("PrototypeBean.destroy");
		}
	}
}