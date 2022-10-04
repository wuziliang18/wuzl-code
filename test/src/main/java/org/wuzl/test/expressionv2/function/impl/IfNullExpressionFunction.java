package org.wuzl.test.expressionv2.function.impl;

import java.util.List;

import org.wuzl.test.expressionv2.ExpressionNode;
import org.wuzl.test.expressionv2.function.support.ExpressionFunctionAdapter;

public class IfNullExpressionFunction extends ExpressionFunctionAdapter {
    @Override
    public int variableNum() {
        return 2;
    }

    @Override
    public String doConver(List<ExpressionNode> nodes) {
        StringBuilder sb = new StringBuilder();
        sb.append(" IFNULL(").append(nodes.get(0).getValue()).append(" , ").append(nodes.get(1).getValue()).append(")");
        return sb.toString();
    }
}
