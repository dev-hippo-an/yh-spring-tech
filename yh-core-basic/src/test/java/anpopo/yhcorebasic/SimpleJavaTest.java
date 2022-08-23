package anpopo.yhcorebasic;

import org.junit.jupiter.api.Test;

public class SimpleJavaTest {


    @Test
    void test() {
        String clause = "(순번)자유 양식 기안-(SEQ)톡톡웨이브,신사업 전략기획 연구소-안세형-20220816(순번)";
        String newClause = clause.replaceAll("\\(순번\\)|\\(SEQ\\)", String.valueOf(1));

        System.out.println(newClause);
    }

    @Test
    void test2() {
        String clause = "(순.번)-자유 양식 기안-(SEQ)톡톡...웨이브,신사업 전략기획 연구소-안세형-20220816(순번)";
        String newClause = clause.replaceAll( "[-.]", String.valueOf(1));

        System.out.println(newClause);
    }


}
