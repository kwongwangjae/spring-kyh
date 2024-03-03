package hello.core.dicount;

import org.springframework.stereotype.Component;

import hello.core.member.Grade;
import hello.core.member.Member;

@Component
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
