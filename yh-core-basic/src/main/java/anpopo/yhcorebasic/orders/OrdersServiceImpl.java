package anpopo.yhcorebasic.orders;

import anpopo.yhcorebasic.discount.DiscountPolicy;
import anpopo.yhcorebasic.discount.FixDiscountPolicy;
import anpopo.yhcorebasic.member.Member;
import anpopo.yhcorebasic.member.MemberRepository;
import anpopo.yhcorebasic.member.MemoryMemberRepository;

public class OrdersServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Orders createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);

        int discount = discountPolicy.discount(member, itemPrice);

        return new Orders(memberId, itemName, itemPrice, discount);
    }
}
