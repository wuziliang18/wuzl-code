package org.wuzl.test.expressionv2.function.impl;

import java.util.List;

import org.wuzl.test.expressionv2.ExpressionNode;
import org.wuzl.test.expressionv2.function.support.ExpressionFunctionAdapter;

/**
 * 将字符串 s 的所有字母变成小写字母
 * 
 * 一个参数:
 * 
 * 要计算的字符串
 * 
 * @author ziliang.wu
 *
 */
public class StrLowerCaseExpressionFunction extends ExpressionFunctionAdapter {
    @Override
    public int variableNum() {
        return 1;
    }

    @Override
    public String doConver(List<ExpressionNode> nodes) {
        StringBuilder sb = new StringBuilder();
        sb.append(" LCASE(");
        sb.append(nodes.get(0).getValue().trim());
        sb.append(") ");
        return sb.toString();
    }

}
