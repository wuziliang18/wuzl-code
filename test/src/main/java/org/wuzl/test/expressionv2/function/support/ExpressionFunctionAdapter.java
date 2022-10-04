package org.wuzl.test.expressionv2.function.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.wuzl.test.expression.ExpressionException;
import org.wuzl.test.expressionv2.ExpressionNode;
import org.wuzl.test.expressionv2.function.ExpressionFunction;

public abstract class ExpressionFunctionAdapter implements ExpressionFunction {
    private static final Map<Character, Character> LOWER_CACHE = new HashMap<>();
    static {
        for (char i = 0; i < 26; i++) {
            LOWER_CACHE.put((char) ('a' + i), (char) ('a' + i - 32));
        }
    }

    // public ExpressionFunctionAdapter() {
    // ExpressionFunctionFactory.register(this);
    // }

    @Override
    public boolean fixedVariable() {
        return true;
    }

    @Override
    public int variableNum() {
        return 0;
    }

    @Override
    public String functionName() {
        String className = this.getClass().getSimpleName();

        int index = className.lastIndexOf("ExpressionFunction");
        if (index <= 0) {
            index = className.lastIndexOf("Function");
        }
        if (index > 0) {
            className = className.substring(0, index);
        }
        boolean proIsLower = false;
        StringBuilder sb = new StringBuilder();
        for (char c : className.toCharArray()) {
            if (c <= 'z' && c >= 'a') {
                sb.append(LOWER_CACHE.get(c));
                proIsLower = true;
                continue;
            }
            if (c <= 'Z' && c >= 'A') {
                if (proIsLower) {
                    sb.append("_");
                    proIsLower = false;
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }

    @Override
    public String conver(List<ExpressionNode> nodes) {
        if (fixedVariable()) {
            int nodeSize = nodes == null ? 0 : nodes.size();
            if (nodeSize != variableNum()) {
                throw new ExpressionException(this.functionName() + "函数需要参数数量为:" + this.variableNum());
            }
        }
        return this.doConver(nodes);
    }

    protected String removeQuotationMarks(String value) {
        if (value.startsWith("\"") && value.endsWith("\"")) {
            return value.substring(1, value.length() - 1);
        }
        return value;
    }

    protected abstract String doConver(List<ExpressionNode> nodes);

    public static void main(String[] args) {
        System.out.println('a');
        System.out.println('A');
        System.out.println((char) ('a' - 32));
        String className = ExpressionFunctionAdapter.class.getSimpleName();
        System.out.println(className);
        List<String> list = new ArrayList<>();
        list.add("MinExpressionFunction");
        list.add("MinFunction");
        list.add("NotBetweenExpressionFunction");
        list.add("NotBetweenFunction");
        list.add("NotBetween2Test1ExpressionFunction");
        for (String input : list) {
            int index = input.lastIndexOf("ExpressionFunction");
            if (index <= 0) {
                index = input.lastIndexOf("Function");
            }
            if (index > 0) {
                input = input.substring(0, index);
            }
            boolean proIsLower = false;
            StringBuilder sb = new StringBuilder();
            for (char c : input.toCharArray()) {
                if (c <= 'z' && c >= 'a') {
                    sb.append(LOWER_CACHE.get(c));
                    proIsLower = true;
                    continue;
                }
                if (c <= 'Z' && c >= 'A') {
                    if (proIsLower) {
                        sb.append("_");
                        proIsLower = false;
                    }
                }
                sb.append(c);
            }
            System.out.println(sb);
        }
    }
}
