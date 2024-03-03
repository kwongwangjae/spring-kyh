package hello.core.beanfind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;

public class ApplicationContextInfoTest {

	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

	@Test
	@DisplayName("모든 빈")
	void findAllBean(){
		String[] beanDefinitionNames = ac.getBeanDefinitionNames();;
		for(String beanName : beanDefinitionNames){
			Object bean = ac.getBean(beanName);
			System.out.println(beanDefinitionNames + "=" + bean);
		}
	}

//커맨드 d 복붙
	@Test
	@DisplayName("애플리케이션 빈")
	void findApplicationBean() {
		String[] beanDefinitionNames = ac.getBeanDefinitionNames();
		for (String beanName : beanDefinitionNames) {
			BeanDefinition beanDefinition = ac.getBeanDefinition(beanName);

			//Role ROLE_APPLICATION 직접 등록한 애플리케이션 빈
			//Role ROLE_INFRASTRUCTURE 스프링 내부에서 사용하는 빈

			if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
				Object bean = ac.getBean(beanName);
				System.out.println(beanDefinitionNames + "=" + bean);

			}
		}
	}
}
