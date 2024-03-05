package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class ProtoTypeTest {
	@Test
	void prototypeBeanFind() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(prototypeBean.class);
		System.out.println("find prototype1");
		prototypeBean prototypeBean1 = ac.getBean(prototypeBean.class);
		System.out.println("find prototype2");
		prototypeBean prototypeBean2 = ac.getBean(prototypeBean.class);
		Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

		// prototypeBean1.destroy();
		// prototypeBean2.destroy();
		ac.close();
	}

	@Scope("prototype")
	static class prototypeBean{
		@PostConstruct
		public void init() {
			System.out.println("init");
		}

		@PreDestroy
		public void destroy() {
			System.out.println("destroy");
		}
	}
}
