package org.wuzl.test.expressionv2.util;

import org.wuzl.test.expressionv2.ExpressionNodeType;

public class ExpressionNodeUtil {
    /**
     * 解析节点类型
     * 
     * @param value
     * @return
     */
    public static ExpressionNodeType parseNodeType(String value) {
        if (value == null || value.equals("")) {
            return ExpressionNodeType.UNKNOWN;
        }
        switch (value.toLowerCase()) {
        case "+":
            return ExpressionNodeType.PLUS;
        case "-":
            return ExpressionNodeType.SUBTRACT;
        case "*":
            return ExpressionNodeType.MULTIPLY;
        case "/":
            return ExpressionNodeType.DIVIDE;
        case "%":
            return ExpressionNodeType.MOD;
        case "(":
            return ExpressionNodeType.L_PARENTHESES;
        case ")":
            return ExpressionNodeType.R_PARENTHESES;
        case "&":
            return ExpressionNodeType.BITWISE_AND;
        case "|":
            return ExpressionNodeType.BITWISE_OR;
        case "&&":
        case "and":
            return ExpressionNodeType.AND;
        case "||":
        case "or":
            return ExpressionNodeType.OR;
        case "!":
            return ExpressionNodeType.NOT;
        case "==":
        case "=":
            return ExpressionNodeType.EQ;
        case "!=":
        case "<>":
        case "≠":
            return ExpressionNodeType.NE;
        case ">":
            return ExpressionNodeType.GT;
        case "<":
            return ExpressionNodeType.LT;
        case ">=":
        case "≥":
            return ExpressionNodeType.GE;
        case "<=":
        case "≤":
            return ExpressionNodeType.LE;
        case "<<":
            return ExpressionNodeType.L_SHIFT;
        case ">>":
            return ExpressionNodeType.R_SHIFT;
        case "true":
            return ExpressionNodeType.TRUE;
        case "false":
            return ExpressionNodeType.FALSE;
        case "null":
            return ExpressionNodeType.NULL;
        // case "like":
        // return ExpressionNodeType.LIKE;
        // case "not_like":
        // return ExpressionNodeType.NOT_LIKE;
        // case "l_like":
        // return ExpressionNodeType.L_LIKE;
        // case "r_like":
        // return ExpressionNodeType.R_LIKE;
        // case "in":
        // return ExpressionNodeType.IN;
        // case "not_in":
        // return ExpressionNodeType.NOT_IN;
        // case "between":
        // return ExpressionNodeType.BETWEEN;
        // case "not_between":
        // return ExpressionNodeType.NOT_BETWEEN;

        }
        if (value.equalsIgnoreCase("#If")) {
            return ExpressionNodeType.IF;
        }
        if (value.equalsIgnoreCase("#ELSE")) {
            return ExpressionNodeType.ELSE;
        }
        if (value.equalsIgnoreCase("#ELSE_IF")) {
            return ExpressionNodeType.ELSE_IF;
        }
        if (value.equalsIgnoreCase("#END_IF")) {
            return ExpressionNodeType.END_IF;
        }

        if (value.startsWith("#")) {
            return ExpressionNodeType.FUNCTION;
        }
        if (value.startsWith("$")) {
            return ExpressionNodeType.VARIABLE;
        }
        if (value.equals(",")) {
            return ExpressionNodeType.DELIMITER;
        }
        if (isNumerics(value)) {
            return ExpressionNodeType.NUMERIC;
        }
        if (value.contains("\"")) {
            return ExpressionNodeType.STRING;
        }

        return ExpressionNodeType.UNKNOWN;
    }

    /**
     * 判断是否为数值
     * 
     * @param op
     * @return
     */
    public static boolean isNumerics(String op) {
        return op.matches("^[\\+\\-]?(0|[1-9]\\d*|[1-9]\\d*\\.\\d+|0\\.\\d+)");
    }

    /**
     * 判断某个字符后是否需要更多的操作符
     * 
     * @param c
     * @return
     */
    public static boolean needMoreOperator(char c) {
        switch (c) {
        case '&':
        case '|':
        case '=':
        case '!':
        case '>':
        case '<':
        case '.': // 小数点
            return true;
        }
        // //数字和字母则需要更多
        return Character.isDigit(c) || (c <= 'z' && c >= 'a') || (c <= 'Z' && c >= 'A');
    }

    /**
     * 判断两个字符是否是同一类
     * 
     * @param c1
     * @param c2
     * @return
     */
    public static boolean isCongener(char c1, char c2) {
        if ((c1 == '(') || (c2 == '(')) {
            return false;
        }
        if ((c1 == ')') || (c2 == ')')) {
            return false;
        }
        if ((c1 == '"') || (c2 == '"')) {
            return false;
        }
        if (c2 == '=') {
            return c1 == '=';
        }
        if (Character.isDigit(c1) || (c1 == '.')) {
            // c1为数字,则c2也为数字
            return (Character.isDigit(c2) || (c2 == '.'));
        }
        return (!Character.isDigit(c2) && (c2 != '.'));
    }

    /**
     * 是方法和参数允许的字符
     * 
     * @param c
     * @return
     */
    public static boolean isFunctionOrVariableName(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || c == '.' || c == '$'
                || c == '_';
    }

    /**
     * 判断某个字符是否是空白字符
     * 
     * @param c
     * @return
     */
    public static boolean isWhileSpace(char c) {
        return c == ' ' || c == '\t';
    }

    /**
     * 判断是否是一元操作符节点
     * 
     * @param nodeType
     * @return
     */
    public static boolean isUnitaryNode(ExpressionNodeType nodeType) {
        return (nodeType == ExpressionNodeType.PLUS || nodeType == ExpressionNodeType.SUBTRACT);
    }

}
