package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class OrderServiceTest {
    MemberService memberService;
    OrderService orderService;
    @BeforeEach
    void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        // given
        Long memberId = 1L;
        Member member = new Member(memberId, "customer", Grade.VIP);
        memberService.join(member);

        // when
        Member findMember = memberService.findMember(memberId);

        //then
        assertThat(findMember).isEqualTo(member);

        // when
        Order order = orderService.createOrder(memberId, "item", 10_000);

        // then
        assertThat(order.getDiscountPrice()).isEqualTo(1_000);
    }
}