package org.wuzl.test.expressionv2.function.impl;

import java.util.List;

import org.wuzl.test.expressionv2.ExpressionNode;
import org.wuzl.test.expressionv2.function.support.ExpressionFunctionAdapter;

/**
 * 将字符串转换为大写
 * 
 * 一个参数:
 * 
 * 要计算的字符串
 * 
 * @author ziliang.wu
 *
 */
public class StrUpperCaseExpressionFunction extends ExpressionFunctionAdapter {
    @Override
    public int variableNum() {
        return 1;
    }

    @Override
    public String doConver(List<ExpressionNode> nodes) {
        StringBuilder sb = new StringBuilder();
        sb.append(" UCASE(");
        sb.append(nodes.get(0).getValue().trim());
        sb.append(") ");
        return sb.toString();
    }

}
