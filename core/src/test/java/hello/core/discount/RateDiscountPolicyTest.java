package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class RateDiscountPolicyTest {
    DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인되어야 한다.")
    void vip_is_ok() {
        // given
        Long memberId = 1L;
        Member member = new Member(memberId, "memberVIP", Grade.VIP);

        // when
        int discount = discountPolicy.discount(member, 20_000);

        // then
        assertThat(discount).isEqualTo(2_000);
    }

    @Test
    @DisplayName("VIP가 아닌 경우 할인되지 않아야 한다.")
    void vip_is_fail() {
        // given
        Long memberId = 2L;
        Member member = new Member(memberId, "memberBASIC", Grade.BASIC);

        // when
        int discount = discountPolicy.discount(member, 20_000);

        // then
        assertThat(discount).isZero();
    }

}