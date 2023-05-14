package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // 조회 성능 향상
/*
 * 영속성 컨텍스트를 플러쉬 하지 않아서 더티 검사하지 않는다
 * DB 드라이버에 따라 읽기 전용이므로 성능을 많이 할당하지 않을 수 있다.
 * */
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    /*
     *   회원 가입
     */
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);

        return member.getId();
    }
    private void validateDuplicateMember(Member member) {
        // 중복 회원 검증
        List<Member> findMembers = memberRepository.findByName(member.getName());
        // 동시 요청시 같이 검증에 통과하여 저장될 수 있으므로 유일 제약 조건 추가하면 좋다.

        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }


    // 회원 전체 조회

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }


    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
