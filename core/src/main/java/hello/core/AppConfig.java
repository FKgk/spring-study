package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.*;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig  {
    // interface 에만 의존된 구현체에 필요한 구현체 클래스를 할당해준다.
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository);
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository, discountPolicy);
    }

}