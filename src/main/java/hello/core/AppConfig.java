package hello.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.core.dicount.DiscountPolicy;
import hello.core.dicount.FixDiscountPolicy;
import hello.core.dicount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

@Configuration
public class AppConfig {

	@Bean
	public MemberService memberService() {
		//1번
		System.out.println("call AppConfig.memberService");
		return new MemberServiceImpl(memberRepository());
	}
	@Bean
	public OrderService orderService() {
		//1번
		System.out.println("call AppConfig.orderService");
		return new OrderServiceImpl(
			memberRepository(),
			discountPolicy());
	}
	@Bean
	public MemberRepository memberRepository() {
		//2번? 3번?
		System.out.println("call AppConfig.memberRepository");
		return new MemoryMemberRepository();
	}
	@Bean
	public DiscountPolicy discountPolicy() {
		return new RateDiscountPolicy();
	}
	//커맨드 옵션 m -> 리팩토링 기능
    //커맨드 e -> 과거 히스토리를 다 볼 수 있음

}

