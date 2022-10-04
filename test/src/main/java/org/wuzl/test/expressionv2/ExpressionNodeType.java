package org.wuzl.test.expressionv2;

import java.util.HashMap;
import java.util.Map;

/**
 * 节点类型枚举
 * 
 * @author ziliang.wu
 *
 */
public enum ExpressionNodeType {
    UNKNOWN("UNKNOWN", "未知类型", ExpressionNodeCategory.UNKNOWN), // 未知类型
    TEXT("TEXT", "文本", ExpressionNodeCategory.VALUE), // 文本 直接输出

    DELIMITER("DELIMITER", "函数参数分隔符", ExpressionNodeCategory.FUNCTION), // 函数参数分隔符

    NUMERIC("NUMERIC", "数字", ExpressionNodeCategory.VALUE), /// 数值,
    STRING("STRING", "字符串", ExpressionNodeCategory.VALUE), //
    TRUE("TRUE", "TRUE", ExpressionNodeCategory.VALUE), // TRUE
    FALSE("FALSE", "FALSE", ExpressionNodeCategory.VALUE), // FALSE
    NULL("null", "null", ExpressionNodeCategory.VALUE), // null

    VARIABLE("VARIABLE", "变量", ExpressionNodeCategory.VARIABLE), //

    AND("AND", "and", ExpressionNodeCategory.OPERATOR), // && (逻辑与)
    OR("OR", "or", ExpressionNodeCategory.OPERATOR), /// || (逻辑或)
    X_OR("XOR", "逻辑异或", ExpressionNodeCategory.OPERATOR), /// || (逻辑异或)

    EQ("==", "等于", ExpressionNodeCategory.OPERATOR), /// == (相等)
    NE("!=", "不等于", ExpressionNodeCategory.OPERATOR), /// != 或 <> (不等于)
    GT(">", "大于", ExpressionNodeCategory.OPERATOR), /// > (大于)
    LT("<", "小于", ExpressionNodeCategory.OPERATOR), /// < (小于)
    GE(">=", "大于等于", ExpressionNodeCategory.OPERATOR), /// >= (大于等于)
    LE("<=", "小于等于", ExpressionNodeCategory.OPERATOR), /// <= (小于等于)
    // LIKE("LIKE", "like", 2), // 包含
    // L_LIKE("LEFT_LIKE", "left like", 2), // 包含
    // R_LIKE("RIGHT_LIKE", "right like", 2), // 包含
    // NOT_LIKE("NOT_LIKE", "not like", 2), // 不包含
    // IN("IN", "in", 2), // 在
    // NOT_IN("NOT_IN", "not in", 2), // 不在
    // BETWEEN("BETWEEN", "区间", 2), // 区间
    // NOT_BETWEEN("NOT_BETWEEN", "不在区间", 2), // 不在区间

    BITWISE_AND("&", "& (按位与)", ExpressionNodeCategory.OPERATOR), // & (按位与)
    BITWISE_OR("|", "| (按位或)", ExpressionNodeCategory.OPERATOR), // | (按位或)
    BITWISE_X_OR("^", "按位异或", ExpressionNodeCategory.OPERATOR), // | (按位异或)
    L_SHIFT("<<", " << (左移位)", ExpressionNodeCategory.OPERATOR), // << (左移位)
    R_SHIFT(">>", " >> (右移位)", ExpressionNodeCategory.OPERATOR), // >> (右移位)

    PLUS("+", "加", ExpressionNodeCategory.OPERATOR), // +
    SUBTRACT("-", "减", ExpressionNodeCategory.OPERATOR), /// -

    MULTIPLY("*", "乘", ExpressionNodeCategory.OPERATOR), // *
    DIVIDE("/", "除", ExpressionNodeCategory.OPERATOR), // //

    MOD("%", "取模", ExpressionNodeCategory.OPERATOR), // % (求模,取余)

    NOT("NOT", "非", ExpressionNodeCategory.OPERATOR), /// ! (逻辑非)

    IF("IF", "if", ExpressionNodeCategory.FUNCTION), //
    ELSE("ELSE", "else", ExpressionNodeCategory.FUNCTION), //
    ELSE_IF("ELSE_IF", "else if", ExpressionNodeCategory.FUNCTION), //
    END_IF("END_IF", "end if", ExpressionNodeCategory.FUNCTION), //

    L_PARENTHESES("(", "左括号", ExpressionNodeCategory.FUNCTION), // (
    R_PARENTHESES(")", "右括号", ExpressionNodeCategory.FUNCTION), /// )
    FUNCTION("FUNCTION", "函数方法", ExpressionNodeCategory.FUNCTION), //
    ;

    private static final Map<String, ExpressionNodeType> cache = new HashMap<>();
    static {
        for (ExpressionNodeType typeEnum : ExpressionNodeType.values()) {
            cache.put(typeEnum.getType(), typeEnum);
        }
    }

    private ExpressionNodeType(String type, String text, ExpressionNodeCategory expressionNodeCategory) {
        this.type = type;
        this.text = text;
        this.expressionNodeCategory = expressionNodeCategory;
    }

    private final String type;
    private final String text;
    private final ExpressionNodeCategory expressionNodeCategory;

    public String getText() {
        return text;
    }

    public String getType() {
        return type;
    }

    public ExpressionNodeCategory getExpressionNodeCategory() {
        return expressionNodeCategory;
    }

    public static ExpressionNodeType getByType(String type) {
        ExpressionNodeType nodeType = cache.get(type);
        if (nodeType != null) {
            return nodeType;
        }
        return UNKNOWN;
    }
}
