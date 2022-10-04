package org.wuzl.test.expressionv2;

import org.wuzl.test.expressionv2.util.ExpressionNodeUtil;

public class ExpressionNode {

    private String value;

    private final ExpressionNodeType type;
    // 一元操作符
    private ExpressionNode unitaryNode;

    public ExpressionNode(String value) {
        this.value = value;
        this.type = ExpressionNodeUtil.parseNodeType(value);
    }

    public ExpressionNode(String value, ExpressionNodeType type) {
        this.value = value;
        this.type = type;
    }

    /**
     * 设置与当前节点相关联的一元操作符节点
     * 
     * @param unitaryNode
     */
    public void setUnitaryNode(ExpressionNode unitaryNode) {
        this.unitaryNode = unitaryNode;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ExpressionNodeType getType() {
        return type;
    }

    public ExpressionNode getUnitaryNode() {
        return unitaryNode;
    }

    @Override
    public String toString() {
        return "ExpressionNode {value=" + value + ", type=" + type + "}";
    }

}
