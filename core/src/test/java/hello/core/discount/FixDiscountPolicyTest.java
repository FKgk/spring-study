package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FixDiscountPolicyTest {
    DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Test
    @DisplayName("VIP는 1,000원 할인받아야 한다.")
    void vip_is_ok() {
        // given
        Member member = new Member(1L, "memberVIP", Grade.VIP);

        // when
        int discount = discountPolicy.discount(member, 10_000);

        // then
        assertThat(discount).isEqualTo(1_000);
    }

    @Test
    @DisplayName("VIP가 아니면 할인 받을 수 없다.")
    void vip_is_fail() {
        // given
        Member member = new Member(1L, "memberBASIC", Grade.BASIC);

        // when
        int discount = discountPolicy.discount(member, 10_000);

        // then
        assertThat(discount).isEqualTo(0);

    }

}