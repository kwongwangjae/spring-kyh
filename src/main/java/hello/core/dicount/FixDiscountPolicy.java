package hello.core.dicount;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import hello.core.member.Grade;
import hello.core.member.Member;

@Component
@Qualifier("fixDiscountPolicy")
public class FixDiscountPolicy implements DiscountPolicy{
	private int discountFixAmount = 1000; //1000원 할인
	@Override
	public int discount(Member member, int price) {
		if (member.getGrade() == Grade.VIP) { //enum타입은 == 이다
			return discountFixAmount;
		} else {
			return 0;
		}
	}
}
