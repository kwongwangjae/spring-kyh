package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class SingletonTest {
	@Test
	@DisplayName("스프링 없는 순수한 DI 컨테이너")
	void pureContainer(){
		AppConfig appConfig = new AppConfig();
		//1. 조회: 호출할 때 마다 객체를 생성
		MemberService memberService1 = appConfig.memberService();

		MemberService memberService2 = appConfig.memberService();
		System.out.println(memberService1);
		System.out.println(memberService2);

		// key = memberRepository1 value = hello.core.member.MemoryMemberRepository@558bdf1f
		// key = memberRepository2 value = hello.core.member.MemoryMemberRepository@8576fa0

		Assertions.assertThat(memberService1).isNotSameAs(memberService2);
	}

	@Test
	@DisplayName("싱글톤 객체를 적용한 객체 사용")
	void singletonTest(){
		// new SingletonService();
		SingletonService singletonService1 = SingletonService.getInstance();
		SingletonService singletonService2 = SingletonService.getInstance();

		System.out.println(singletonService1);
		System.out.println(singletonService2);
		singletonService1.logic();

		// hello.core.singleton.SingletonService@221af3c0
		// hello.core.singleton.SingletonService@221af3c0

		Assertions.assertThat(singletonService1).isSameAs(singletonService2);

	}

	@Test
	@DisplayName("스프링 컨테이너와 싱글톤")
	void springContainer(){
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		//1. 조회: 호출할 때 마다 객체를 생성
		MemberService memberService1 = ac.getBean("memberService", MemberService.class);
		MemberService memberService2 = ac.getBean("memberService", MemberService.class);

		System.out.println(memberService1);
		System.out.println(memberService2);

		Assertions.assertThat(memberService1).isSameAs(memberService2);

	}
}
