package org.wuzl.test.expressionv2.function.impl;

import java.util.List;

import org.wuzl.test.expressionv2.ExpressionNode;
import org.wuzl.test.expressionv2.function.support.ExpressionFunctionAdapter;

/**
 * 可以将数字 进行格式化 留到小数点后 n 位，最后一位四舍五入
 * 
 * 二个参数:
 * 
 * 1.要格式化的数字
 * 
 * 2.保留小数位
 * 
 * @author ziliang.wu
 *
 */
public class NumerFormatExpressionFunction extends ExpressionFunctionAdapter {
    @Override
    public int variableNum() {
        return 2;
    }

    @Override
    public String doConver(List<ExpressionNode> nodes) {
        StringBuilder sb = new StringBuilder();
        sb.append(" FORMAT(");
        sb.append(nodes.get(0).getValue().trim()).append(", ");
        sb.append(nodes.get(1).getValue().trim());
        sb.append(") ");
        return sb.toString();
    }

}
