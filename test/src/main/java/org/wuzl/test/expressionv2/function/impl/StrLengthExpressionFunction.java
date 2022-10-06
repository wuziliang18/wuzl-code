package org.wuzl.test.expressionv2.function.impl;

import java.util.List;

import org.wuzl.test.expressionv2.ExpressionNode;
import org.wuzl.test.expressionv2.function.support.ExpressionFunctionAdapter;

/**
 * 返回字符串长度
 * 
 * 一个参数:
 * 
 * 要计算的字符串
 * 
 * @author ziliang.wu
 *
 */
public class StrLengthExpressionFunction extends ExpressionFunctionAdapter {
    @Override
    public int variableNum() {
        return 1;
    }

    @Override
    public String doConver(List<ExpressionNode> nodes) {
        StringBuilder sb = new StringBuilder();
        sb.append(" CHAR_LENGTH(");
        sb.append(nodes.get(0).getValue().trim());
        sb.append(") ");
        return sb.toString();
    }

}
