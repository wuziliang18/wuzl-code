package org.wuzl.test.expressionv2;

import org.wuzl.test.expression.ExpressionException;
import org.wuzl.test.expressionv2.util.ExpressionNodeUtil;

public class ExpressionStatement {

    private final String expression;

    // 当前读取的位置
    private int position;

    public String getExpression() {
        return expression;
    }

    public int getPosition() {
        return position;
    }

    public ExpressionStatement(String expression) {
        this.expression = expression;
        this.position = 0;
    }

    /**
     * 读取下一个表达式节点,如果读取失败则返回null
     * 
     * @return
     */
    public ExpressionNode readNode() {
        // 空格的位置
        int whileSpacePos = -1;
        // "标识
        boolean stringFlag = false;
        boolean funcitonFlag = false;
        boolean variableFlag = false;
        StringBuffer buffer = new StringBuffer(10);
        while (this.position < this.expression.length()) {
            char c = this.expression.charAt(this.position);
            if (c == '"') {
                stringFlag = !stringFlag;
                if (!stringFlag) {
                    // ""匹配完成
                    this.position++;
                    buffer.append(c);
                    break;
                }
                // 之前的匹配完成了
                if (buffer.length() != 0) {
                    break;
                }
            }

            if (stringFlag) {
                // 双引号内
                this.position++;
                buffer.append(c);
                continue;
            }
            if (buffer.length() == 0) {
                // 处理函数
                if (c == '#') {
                    funcitonFlag = true;
                    this.position++;
                    buffer.append(c);
                    continue;
                }
                // 处理变量
                if (c == '$') {
                    variableFlag = true;
                    this.position++;
                    buffer.append(c);
                    continue;
                }
            }
            if (funcitonFlag || variableFlag) {
                if (ExpressionNodeUtil.isFunctionOrVariableName(c)) {
                    this.position++;
                    buffer.append(c);
                    continue;
                }
                break;
            }

            if (ExpressionNodeUtil.isWhileSpace(c)) {
                // 空白符之间有非空白符
                if ((whileSpacePos >= 0) && ((this.position - whileSpacePos) > 1)) {
                    throw new ExpressionException(
                            String.format("表达式\"%s\"在位置(%s)上的字符非法!", this.getExpression(), this.getPosition()));
                }
                if (buffer.length() == 0) {
                    whileSpacePos = -1;
                } else {
                    whileSpacePos = this.position;
                }
                this.position++;
                if (buffer.length() > 0) {
                    break;
                }
                continue;
            }
            if ((buffer.length() == 0) || ExpressionNodeUtil.isCongener(c, buffer.charAt(buffer.length() - 1))) {
                this.position++;
                buffer.append(c);
            } else {
                break;
            }
            if (!ExpressionNodeUtil.needMoreOperator(c)) {
                break;
            }
        }
        if (buffer.length() == 0) {
            return null;
        }
        ExpressionNode node = new ExpressionNode(buffer.toString());
        if (node.getType() == ExpressionNodeType.UNKNOWN) {
            throw new ExpressionException(String.format("表达式\"%s\"在位置%s上的字符\"%s\"非法!", this.getExpression(),
                    this.getPosition() - node.getValue().length(), node.getValue()));
        }
        return node;
    }
}
