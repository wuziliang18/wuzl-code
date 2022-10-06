package org.wuzl.test.expressionv2.function.impl;

import java.util.List;

import org.wuzl.test.expressionv2.ExpressionNode;
import org.wuzl.test.expressionv2.function.support.ExpressionFunctionAdapter;

/**
 * 将字符串 s2 替代字符串 s 中的字符串 s1
 * 
 * 
 * 三个参数:
 * 
 * 1.要计算的字符串
 * 
 * 2.被替代的
 * 
 * 3.替代的
 * 
 * @author ziliang.wu
 *
 */
public class StrReplaceExpressionFunction extends ExpressionFunctionAdapter {
    @Override
    public int variableNum() {
        return 3;
    }

    @Override
    public String doConver(List<ExpressionNode> nodes) {
        StringBuilder sb = new StringBuilder();
        sb.append(" REPLACE(");
        sb.append(nodes.get(0).getValue().trim()).append(", ");
        sb.append(nodes.get(1).getValue().trim()).append(", ");
        sb.append(nodes.get(2).getValue().trim());
        sb.append(") ");
        return sb.toString();
    }

}
