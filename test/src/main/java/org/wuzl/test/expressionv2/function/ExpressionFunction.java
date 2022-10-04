package org.wuzl.test.expressionv2.function;

import java.util.List;

import org.wuzl.test.expressionv2.ExpressionNode;

/**
 * 表单式函数
 * 
 * @author ziliang.wu
 *
 */
public interface ExpressionFunction {
    /**
     *  参数数量
     * 
     * @return
     */
    public int variableNum();

    /**
     * 固定参数长度 用于方法重载
     * 
     * @return
     */
    public boolean fixedVariable();

    /**
     * 函数名称
     */
    public String functionName();

    /**
     * 转换
     * 
     * @param nodes
     * @return
     */
    public String conver(List<ExpressionNode> nodes);
}
