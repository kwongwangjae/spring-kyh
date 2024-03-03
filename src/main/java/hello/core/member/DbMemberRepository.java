package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class DbMemberRepository implements MemberRepository{

	private static Map<Long, Member> la = new HashMap<>();
	@Override
	public void save(Member member) {
		la.put(member.getId(), member);
	}

	@Override
	public Member findById(Long memberId) {
		la.get(memberId);
		return null;
	}
}
