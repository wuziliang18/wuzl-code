package org.wuzl.test.expression;

public class Test {
    public static void main(String[] args) {
        // 加法运算
        String plusStr = "1 + 2";
        System.out.println(ExpressionEvaluator.eval(plusStr));

        // 减法运算
        String subtractStr = "20 - 6";
        System.out.println(ExpressionEvaluator.eval(subtractStr));

        // 乘法运算
        String multiPlyStr = "3    * 5";
        System.out.println(ExpressionEvaluator.eval(multiPlyStr));

        // 除法运算
        String divideStr = "20 / 4";
        System.out.println(ExpressionEvaluator.eval(divideStr));

        // 次幂运算
        String powerStr = "2  ^ 3";
        System.out.println(ExpressionEvaluator.eval(powerStr));

        // 取余运算
        String modStr = "4 % 3";
        System.out.println(ExpressionEvaluator.eval(modStr));

        // 混合运算
        String resultStr = "(1 + 2) - 4 * 3 + 2^2 + 4%3";
        System.out.println(ExpressionEvaluator.eval(resultStr));

        String s1 = "(1 + 2) == 3";
        System.out.println(ExpressionEvaluator.eval(s1));

        String s2 = "(20 - 6) < 3";
        System.out.println(ExpressionEvaluator.eval(s2));

        String s3 = "(3 + 1) == 4 && 5 > (2 + 3)";
        System.out.println(ExpressionEvaluator.eval(s3));

        String s4 = "\"hello\" == \"hello\" && 3 != 4";
        System.out.println(ExpressionEvaluator.eval(s4));
        System.out.println(ExpressionEvaluator.parseExpression(s4));
        String s5 = "\"helloworld\" @ \"hello\" &&  \"helloworld\" !@ \"word\" ";
        System.out.println(ExpressionEvaluator.eval(s5));
        String s6 = "1==2 || 2==3 并且 2==2 ";
        System.out.println(ExpressionEvaluator.parseExpression(s6));
    }
}
