package hello.core.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MemberServiceImplTest {
    MemberService memberService = new MemberServiceImpl();

    @Test
    @DisplayName("member를 저장 및 조회할 수 있다.")
    void joinAndFindMember() {
        // given
        Member member = new Member(1L, "member", Grade.BASIC);

        // when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        // then
        assertThat(findMember).isEqualTo(member);
    }

}