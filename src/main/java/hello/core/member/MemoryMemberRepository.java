package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{

	private static Map<Long, Member> store = new HashMap<>(); //이러면 동시성 이유가 생김 컨퀄해쉬맵을 사용해야함 나중에 공부해보기
	@Override
	public void save(Member member) {
		store.put(member.getId(), member);

	}

	@Override
	public Member findById(Long memberId) {
		return store.get(memberId);
	}
}
