package anpopo.yhcorebasic;

import anpopo.yhcorebasic.config.AppConfig;
import anpopo.yhcorebasic.member.Grade;
import anpopo.yhcorebasic.member.Member;
import anpopo.yhcorebasic.member.MemberService;
import anpopo.yhcorebasic.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();

//        MemberService memberService = new MemberServiceImpl();

        MemberService memberService = appConfig.memberService();
        Member member = new Member(1L, "세형", Grade.VIP);

        memberService.join(member);

        Member findMember = memberService.findMember(member.getId());

        System.out.println("findMember = " + findMember);
    }
}
