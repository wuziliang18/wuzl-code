package org.wuzl.test.expressionv2;

import java.util.HashMap;
import java.util.Map;

/**
 * 节点分类枚举
 * 
 * @author ziliang.wu
 *
 */
public enum ExpressionNodeCategory {
    FUNCTION("FUNCTION", "函数类型"), // 函数
    VARIABLE("VARIABLE", "文本"), // 变量
    OPERATOR("OPERATOR", "操作符"), // 操作符
    VALUE("VALUE", "值"), // 值
    UNKNOWN("UNKNOWN", "未知类型"), // 未知类型
    ;

    private static final Map<String, ExpressionNodeCategory> cache = new HashMap<>();
    static {
        for (ExpressionNodeCategory typeEnum : ExpressionNodeCategory.values()) {
            cache.put(typeEnum.getType(), typeEnum);
        }
    }

    private ExpressionNodeCategory(String type, String text) {
        this.type = type;
        this.text = text;
    }

    private final String type;
    private final String text;

    public String getText() {
        return text;
    }

    public String getType() {
        return type;
    }

    public static ExpressionNodeCategory getByType(String type) {
        ExpressionNodeCategory nodeType = cache.get(type);
        if (nodeType != null) {
            return nodeType;
        }
        return null;
    }
}
