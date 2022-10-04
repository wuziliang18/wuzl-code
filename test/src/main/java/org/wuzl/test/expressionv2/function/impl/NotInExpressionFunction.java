package org.wuzl.test.expressionv2.function.impl;

import java.util.List;

import org.wuzl.test.expression.ExpressionException;
import org.wuzl.test.expressionv2.ExpressionNode;
import org.wuzl.test.expressionv2.function.support.ExpressionFunctionAdapter;

public class NotInExpressionFunction extends ExpressionFunctionAdapter {
    @Override
    public boolean fixedVariable() {
        return false;
    }

    @Override
    public String doConver(List<ExpressionNode> nodes) {
        if (nodes == null || nodes.size() == 0) {
            throw new ExpressionException("notin函数至少需要一个参数数量");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(" not in (");
        for (ExpressionNode node : nodes) {
            sb.append(node.getValue()).append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append(") ");
        return sb.toString();
    }

}
