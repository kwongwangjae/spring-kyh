package hello.core.dicount;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import hello.core.annotaiton.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;

@Component
// @Qualifier("mainDiscountPolicy") 아래에 어노테이션을 생성해서 넘겨줌 오류를 잡기 쉬움
@MainDiscountPolicy
// @Primary // 의존관계에서 우선순위를 가짐
public class RateDiscountPolicy implements  DiscountPolicy {

	private int discountPercent = 10;

	@Override
	public int discount(Member member, int price) { // 자체 테스트 커맨드 쉬프트 t
		if (member.getGrade() == Grade.VIP) {
			return price * discountPercent / 100;
		} else{
			return 0;
		}
	}
}
