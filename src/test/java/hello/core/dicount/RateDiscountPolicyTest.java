package hello.core.dicount;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;

class RateDiscountPolicyTest {
	RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

	@Test
	@DisplayName("VIP는 10% 할인이 적용되어야한다.")
	void vip_o() {
		//given
		Member member = new Member(1L, "MemberVIP", Grade.VIP);
		//when
		int discount = discountPolicy.discount(member, 10000);
		//then`
		assertThat(discount).isEqualTo(1000); //옵션 엔터로 줄임말 가능
	}

	@Test
	@DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다.")
	void vip_x() {
		//given
		Member member = new Member(2L, "MemberBASIC", Grade.BASIC);
		//when
		int discount = discountPolicy.discount(member, 10000);
		//then
		assertThat(discount).isEqualTo(0);
	}
}