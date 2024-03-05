package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class SingletonTest {
	@Test
	void singletonBeanFind() {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SingletonBean.class);
		SingletonBean singletonBean1 = applicationContext.getBean(SingletonBean.class);
		SingletonBean singletonBean2 = applicationContext.getBean(SingletonBean.class);
		System.out.println(singletonBean1);
		System.out.println(singletonBean2);

		Assertions.assertThat(singletonBean1).isSameAs(singletonBean2);

		applicationContext.close();
	}

	@Scope("singleton")
	static class SingletonBean{
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
