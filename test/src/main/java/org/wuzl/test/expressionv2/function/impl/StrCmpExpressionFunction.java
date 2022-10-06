package org.wuzl.test.expressionv2.function.impl;

import java.util.List;

import org.wuzl.test.expressionv2.ExpressionNode;
import org.wuzl.test.expressionv2.function.support.ExpressionFunctionAdapter;

/**
 * 比较字符串 s1 和 s2，如果 s1 与 s2 相等返回 0 ，如果 s1>s2 返回 1，如果 s1<s2 返回 -1
 * 
 * 二个参数:
 * 
 * 1. 要计算的字符串
 *
 * 2.比较的字符串
 * 
 * @author ziliang.wu
 *
 */
public class StrCmpExpressionFunction extends ExpressionFunctionAdapter {
    @Override
    public int variableNum() {
        return 2;
    }

    @Override
    public String doConver(List<ExpressionNode> nodes) {
        StringBuilder sb = new StringBuilder();
        sb.append(" STRCMP(");
        sb.append(nodes.get(0).getValue().trim()).append(", ");
        sb.append(nodes.get(1).getValue().trim());
        sb.append(") ");
        return sb.toString();
    }

}
