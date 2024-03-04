package hello.core.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import hello.core.annotaiton.MainDiscountPolicy;
import hello.core.dicount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;

// @RequiredArgsConstructor 어노테이션을 사용하면 롬복이 해당 클래스의 필드를 가지고 생성자를 자동으로 생성해줍니다.
// 	그런데 final 키워드가 붙은 필드만 생성자에 포함됩니다. 그렇기 때문에 final 키워드가 없는 필드는 생성자에 포함되지 않습니다.
@Component
// @RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

	// public void setDiscountPolicy(DiscountPolicy discountPolicy) {
	// 	this.discountPolicy = discountPolicy;
	// }

	private final MemberRepository memberRepository; //생성자 주입이 아니면 final키워드가 불가능하다?
	private final   DiscountPolicy discountPolicy; //이렇게 바꾸는 대신 누군가 클라이언트인 OrderServiceImpl에 DiscountPolicy의 구현 객체를 대신 생성하고 주입해줘야 한다.

	// @Autowired
	// private DiscountPolicy rateDiscountPolicy;

	// @Autowired
	// public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy rateDiscountPolicy) {
	// 	this.memberRepository = memberRepository;
	// 	this.discountPolicy = rateDiscountPolicy;
	 //}이게 생성자 주입임 //@RequiredArgsConstructor이걸로 대신 생성함
	//rateDiscountPolicy FixdiscountPolicy와 의존관계 구별을 하기 위해서 @Autowired의 명칭을 바꾼 상황이다. (스프링 컨테이너가 이렇게 인식을 가능하게 함)

	// @Autowired
    // public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
	// 	this.memberRepository = memberRepository;
	// 	this.discountPolicy = discountPolicy;
	// }

	@Autowired
	public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
		this.memberRepository = memberRepository;
		this.discountPolicy = discountPolicy;
	}

	// `@Primary` 는 기본값 처럼 동작하는 것이고, `@Qualifier` 는 매우 상세하게 동작한다. 이런 경우 어떤 것이 우선권을
	// 가져갈까? 스프링은 자동보다는 수동이, 넒은 범위의 선택권 보다는 좁은 범위의 선택권이 우선 순위가 높다. 따라서 여 기서도 `@Qualifier` 가 우선권이 높다.
	// private final MemberRepository memberRepository = new MemoryMemberRepository();
	// private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
	// private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		Member member = memberRepository.findById(memberId);
		int discountPrice = discountPolicy.discount(member, itemPrice);

		return new Order(memberId, itemName, itemPrice, discountPrice);
	}

	//테스트 용도
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}
}
