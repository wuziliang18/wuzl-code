package org.wuzl.test.expressionv2.function.impl;

import java.util.List;

import org.wuzl.test.expressionv2.ExpressionNode;
import org.wuzl.test.expressionv2.function.support.ExpressionFunctionAdapter;

public class CurrentTimeExpressionFunction extends ExpressionFunctionAdapter {
    @Override
    public int variableNum() {
        return 0;
    }

    @Override
    public String doConver(List<ExpressionNode> nodes) {
        return "CURRENT_TIME()";
    }
}
