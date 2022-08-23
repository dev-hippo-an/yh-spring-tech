package anpopo.yhcorebasic.orders;

import anpopo.yhcorebasic.discount.DiscountPolicy;
import anpopo.yhcorebasic.member.Member;
import anpopo.yhcorebasic.member.MemberRepository;

public class OrdersServiceImpl implements OrderService {

    /**
     * 실제 구현체를 의존하고 있다 - DIP 위반
     * 실 구현체를 바꾼다 - OCP 위반
     */
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrdersServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Orders createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);

        int discount = discountPolicy.discount(member, itemPrice);

        return new Orders(memberId, itemName, itemPrice, discount);
    }

    public MemberRepository getMemberRepository() {
        return this.memberRepository;
    }
}
