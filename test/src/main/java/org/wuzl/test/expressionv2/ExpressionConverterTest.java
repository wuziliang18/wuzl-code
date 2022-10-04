package org.wuzl.test.expressionv2;

import java.util.Arrays;
import java.util.List;

public class ExpressionConverterTest {
    public static void main(String[] args) {
        ExpressionConverter converter = new ExpressionConverter();
        List<String> list = Arrays.asList("$a #LIKE(\"a\") and $b=1", "$a #IN(1,2)", "true and $a==1",
                "$a == null and $b.c!=null", "1+2+#IF_NULL($a.d,112)", "\"hello\"+\"world\"", "3    * 5",
                "(1 + 2) - 4 * 3 + #POW(2,3) + 4%3", "$a+$b", "$a.c+$b.d",
                "#IF($a==1 or  $b==2) ($a.d1*$b+$c)+($a.c*112) #ELSE_IF($d==2) 1+$a #ELSE 1+$a+$b+121.1+\"111ss.xx\" #END_IF",
                "#IF($a==1 ||  $b==2) ($a.d1*$b+$c)+($a.c*112) #ELSE 1+$a+$b+121.1+\"111ss.xx\" #END_IF",
                "#IF($a==1 and $b.a==null) $a.b1*3+#IF_NULL($a.n+#CURRENT_DATE(),$b) #ELSE 1+$a+$b+121.1+\"111ss.xx\" #END_IF"
                        + "");
        for (String input : list) {
            // input = "#IF($a==1 or $b==2) ($a.d1*$b+$c)+($a.c*112) #ELSE_IF($d==2) 1+$a #ELSE
            // 1+$a+$b+121.1+\"111ss.xx\" #END_IF";
            // input = "#IF($a==1 and $b.a==null) $a.b1*3+#IF_NULL($a.n+#CURRENT_DATE(),$b) #ELSE
            // 1+$a+$b+121.1+\"111ss.xx\" #END_IF";
            System.out.println("输入: " + input);
            String result = converter.convert(input);
            System.out.println("输出: " + result);
        }

    }
}
