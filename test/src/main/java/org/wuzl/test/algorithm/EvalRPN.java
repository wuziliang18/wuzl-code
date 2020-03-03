package org.wuzl.test.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.function.Function;

/**
 * 根据逆波兰表示法，求表达式的值。
 * 
 * 有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 * 
 * 说明：
 * 
 * 整数除法只保留整数部分。 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/evaluate-reverse-polish-notation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author ziliang.wu
 *
 */
public class EvalRPN {
    public static void main(String[] args) {
        EvalRPN obj = new EvalRPN();
        System.out.println(obj.evalRPN(new String[] { "2", "1", "+", "3", "*" }));
        System.out.println(obj.evalRPN(new String[] { "4", "13", "5", "/", "+" }));
        System.out.println(
                obj.evalRPN(new String[] { "10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+" }));
    }

    private static Map<String, Function<Stack<Integer>, Integer>> funcitonMap = new HashMap<>();
    static {
        funcitonMap.put("+", new Function<Stack<Integer>, Integer>() {

            @Override
            public Integer apply(Stack<Integer> stack) {
                int a = stack.pop();
                int b = stack.pop();
                int result = a + b;
                stack.push(result);
                return result;
            }

        });

        funcitonMap.put("-", new Function<Stack<Integer>, Integer>() {

            @Override
            public Integer apply(Stack<Integer> stack) {
                int a = stack.pop();
                int b = stack.pop();
                int result = b - a;
                stack.push(result);
                return result;
            }

        });

        funcitonMap.put("*", new Function<Stack<Integer>, Integer>() {

            @Override
            public Integer apply(Stack<Integer> stack) {
                int a = stack.pop();
                int b = stack.pop();
                int result = a * b;
                stack.push(result);
                return result;
            }
        });
        funcitonMap.put("/", new Function<Stack<Integer>, Integer>() {

            @Override
            public Integer apply(Stack<Integer> stack) {
                int a = stack.pop();
                int b = stack.pop();
                int result = b / a;
                stack.push(result);
                return result;
            }

        });
    }

    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            Function<Stack<Integer>, Integer> functon = funcitonMap.get(token);
            if (functon != null) {
                functon.apply(stack);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        int result = stack.pop();
        return result;
    }

}
