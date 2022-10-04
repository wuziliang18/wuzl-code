package org.wuzl.test.expressionv2;

import java.util.Arrays;
import java.util.List;

public class ExpressionStatementTest {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("true and $a==1", "$a == null and $b.c==null", "1+2+#IF_NULL($a.d,112)", "\"hello\"+\"world\"",
                "3    * 5", "(1 + 2) - 4 * 3 + #MOD(2,3) + 4%3", "$a+$b", "$a.c+$b.d",
                "#IF($a==1 or  $b==2) ($a.d1*$b+$c)+($a.c*112) #ELSE 1+$a+$b+121.1+\"111ss.xx\" #ENDIF",
                "#IF($a==1 ||  $b==2) ($a.d1*$b+$c)+($a.c*112) #ELSE 1+$a+$b+121.1+\"111ss.xx\" #ENDIF",
                "#IF($a==1 and $b.a==null) $a.b1*3+#test($a.n+#test2(),$b) #ELSE 1+$a+$b+121.1+\"111ss.xx\" #ENDIF"
                        + "");
        // List<String> list = Arrays.asList("1==2 or $b==3");
        for (String input : list) {
            ExpressionStatement parse = new ExpressionStatement(input);
            System.out.println("开始: " + input);
            ExpressionNode node = parse.readNode();
            while (node != null) {
                System.out.println(node);
                node = parse.readNode();
            }
            System.out.println("end: " + input);
        }
    }
}
