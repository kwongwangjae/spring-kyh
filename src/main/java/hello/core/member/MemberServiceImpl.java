package hello.core.member;

public class MemberServiceImpl implements MemberService{

	private final MemberRepository memberRepository; //;추가는 커맨드 쉬프트 엔터

	public MemberServiceImpl(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	@Override
	public void join(Member member) {
		memberRepository.save(member);
	}

	@Override
	public Member findMember(Long memberId) {
		return memberRepository.findById(memberId);
	}
}
