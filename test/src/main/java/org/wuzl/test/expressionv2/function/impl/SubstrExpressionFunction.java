package org.wuzl.test.expressionv2.function.impl;

import java.util.List;

import org.wuzl.test.expressionv2.ExpressionNode;
import org.wuzl.test.expressionv2.function.support.ExpressionFunctionAdapter;

/**
 * 截取字符串
 * 
 * 三个参数:
 * 
 * 1.要截取的字符串
 * 
 * 2.截取的起始位置从1开始
 * 
 * 3.截取长度
 * 
 * @author ziliang.wu
 *
 */
public class SubstrExpressionFunction extends ExpressionFunctionAdapter {
    @Override
    public int variableNum() {
        return 3;
    }

    @Override
    public String doConver(List<ExpressionNode> nodes) {
        StringBuilder sb = new StringBuilder();
        sb.append(" SUBSTR(");
        sb.append(nodes.get(0).getValue()).append(", ");
        String start = nodes.get(1).getValue();
        if (start.trim().equals("0")) {
            start = "1";
        }
        sb.append(start).append(", ");
        sb.append(nodes.get(2).getValue());
        sb.append(") ");
        return sb.toString();
    }

}
