package anpopo.yhcorebasic.discount;

import anpopo.yhcorebasic.member.Grade;
import anpopo.yhcorebasic.member.Member;

public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercentage = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercentage / 100;
        }
        return 0;
    }
}
