package org.wuzl.test.expressionv2.function.impl;

import java.util.List;

import org.wuzl.test.expressionv2.ExpressionNode;
import org.wuzl.test.expressionv2.function.support.ExpressionFunctionAdapter;

/**
 * 从日期或日期时间表达式中提取日期值
 * 
 * 一个参数:
 * 
 * 日期或日期时间表达式
 * 
 * @author ziliang.wu
 *
 */
public class DateExpressionFunction extends ExpressionFunctionAdapter {
    @Override
    public int variableNum() {
        return 1;
    }

    @Override
    public String doConver(List<ExpressionNode> nodes) {
        StringBuilder sb = new StringBuilder();
        sb.append(" DATE(");
        sb.append(nodes.get(0).getValue().trim());
        sb.append(") ");
        return sb.toString();
    }

}
