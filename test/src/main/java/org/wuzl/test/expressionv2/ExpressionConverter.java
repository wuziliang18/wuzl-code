package org.wuzl.test.expressionv2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.wuzl.test.expression.ExpressionException;
import org.wuzl.test.expressionv2.function.ExpressionFunction;
import org.wuzl.test.expressionv2.function.ExpressionFunctionFactory;

public class ExpressionConverter {
    public String convert(String input) {
        if (input == null || input.equals("")) {
            return input;
        }

        ExpressionStatement parse = new ExpressionStatement(input);
        StringBuilder sb = new StringBuilder();
        Stack<ExpressionNode> stack = new Stack<ExpressionNode>();
        ExpressionNode node = parse.readNode();
        Set<String> variables = new HashSet<>();
        while (node != null) {
            ExpressionNode nextNode = parse.readNode();
            switch (node.getType()) {
            case L_PARENTHESES:

            case IF:
            case ELSE:
            case ELSE_IF:

            case FUNCTION:

            case DELIMITER:
                stack.push(node);
                break;
            case R_PARENTHESES:
                // 出栈 直到越到左括号 该对括号丢弃
                // 判断前一节点是否函数
                // 是 根据函数生成sql内容 需要判断函数和参数
                // 不是 直接当字符串
                // 判断栈是否为空
                // 空直接放到结果中
                // 不空生成文本节点放入栈
                stack.push(node);
                String text = this.processRParentheses(stack);
                this.processTextValue(stack, sb, text);
                break;
            case END_IF:
                // 开始出栈 直到if 生成sql内容
                // 判断栈是否为空
                // 空直接放到结果中
                // 不空生成文本节点放入栈
                stack.push(node);
                text = this.processEndIf(stack);
                this.processTextValue(stack, sb, text);
                break;
            case VARIABLE:
                // 校验是否合法
                // 判断栈是否为空
                // 空直接放到结果中
                // 不空生成文本节点放入栈
                variables.add(node.getValue());
                this.processTextValue(stack, sb, node.getValue());
                break;
            default:
                ExpressionNodeCategory category = node.getType().getExpressionNodeCategory();
                switch (category) {
                case OPERATOR:
                    // 部分需要转义
                    // 判断栈是否为空
                    // 空直接放到结果中
                    // 不空生成文本节点放入栈
                    text = this.processOperator(node, nextNode);
                    this.processTextValue(stack, sb, text);
                    break;
                case VALUE:
                case UNKNOWN:
                    // 数字 字符串 其他
                    // 判断栈是否为空
                    // 空直接放到结果中
                    // 不空生成文本节点放入栈
                    text = node.getValue();
                    this.processTextValue(stack, sb, text);
                    break;
                default:
                    throw new ExpressionException("未处理的节点:" + node);
                }

            }
            node = nextNode;
        }
        if (!stack.isEmpty()) {
            throw new ExpressionException("格式不正确,多余节点" + stack.pop().getValue());
        }
        // 校验变量合法
        // TODO
        return sb.toString();
    }

    private String processOperator(ExpressionNode node, ExpressionNode nextNode) {
        ExpressionNodeType nodeType = node.getType();
        if (nodeType != ExpressionNodeType.EQ && nodeType != ExpressionNodeType.NE) {
            return nodeType.getType();

        }
        if (nextNode == null) {
            throw new ExpressionException("==符号后没有值");
        }
        if (nextNode.getType() == ExpressionNodeType.NULL) {
            // ==null和!=null单独处理 seek下一节点
            return nodeType == ExpressionNodeType.EQ ? "is" : "is not";
        }
        return nodeType == ExpressionNodeType.EQ ? "=" : nodeType.getType();
    }

    private String processEndIf(Stack<ExpressionNode> stack) {
        LinkedList<ExpressionNode> popNodes = pop(stack, ExpressionNodeType.IF);
        StringBuilder sb = new StringBuilder();
        int index = 0;
        while (index < popNodes.size()) {
            ExpressionNode node = popNodes.get(index);
            ExpressionNodeType nodeType = node.getType();
            switch (nodeType) {
            case IF:
                sb.append(" CASE WHEN ");
                // 取判断节点
                index++;
                if (index >= popNodes.size()) {
                    throw new ExpressionException("if后没有条件");
                }
                sb.append(popNodes.get(index).getValue());
                sb.append(" THEN ");
                break;
            case ELSE:
                sb.append(" ELSE ");
                break;
            case ELSE_IF:
                sb.append(" WHEN ");
                // 取判断节点
                index++;
                if (index >= popNodes.size()) {
                    throw new ExpressionException("ELSE_IF后没有条件");
                }
                sb.append(popNodes.get(index).getValue());
                sb.append(" THEN ");
                break;
            case END_IF:
                sb.append(" END ");
                break;
            default:
                sb.append(node.getValue());
                break;
            }
            index++;
        }
        return sb.toString();
    }

    private String processRParentheses(Stack<ExpressionNode> stack) {
        LinkedList<ExpressionNode> popNodes = pop(stack, ExpressionNodeType.L_PARENTHESES);
        if (!stack.isEmpty()) {
            ExpressionNode proNode = stack.pop();
            if (proNode.getType() == ExpressionNodeType.FUNCTION) {
                popNodes.addFirst(proNode);
                return processFunction(popNodes);
            }
            stack.push(proNode);
        }
        // popNodes.removeFirst();
        // popNodes.removeLast();
        return this.splitString(popNodes);
    }

    private String processFunction(LinkedList<ExpressionNode> nodes) {
        ExpressionNode functionNode = nodes.removeFirst();
        nodes.removeFirst();
        nodes.removeLast();
        String functionName = functionNode.getValue().substring(1, functionNode.getValue().length());
        ExpressionFunction function = ExpressionFunctionFactory.getByFunctionName(functionName);
        if (function == null) {
            throw new ExpressionException("不支持的函数:" + functionName);
        }
        Iterator<ExpressionNode> ite = nodes.iterator();
        List<ExpressionNode> variableNodes = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        while (ite.hasNext()) {
            ExpressionNode node = ite.next();
            if (node.getType() == ExpressionNodeType.DELIMITER) {
                // 累加变量
                variableNodes.add(new ExpressionNode(sb.toString(), ExpressionNodeType.TEXT));
                sb = new StringBuilder();
                continue;
            }
            sb.append(node.getValue()).append(" ");
        }
        if (sb.length() > 0) {
            variableNodes.add(new ExpressionNode(sb.toString(), ExpressionNodeType.TEXT));
        }
        if (function.fixedVariable() && variableNodes.size() != function.variableNum()) {
            throw new ExpressionException(functionName + "函数需要参数数量为:" + function.variableNum());
        }
        return function.conver(variableNodes);
    }

    private String splitString(LinkedList<ExpressionNode> nodes) {
        StringBuilder sb = new StringBuilder();
        for (ExpressionNode node : nodes) {
            sb.append(node.getValue()).append(" ");
        }
        return sb.toString();
    }

    private LinkedList<ExpressionNode> pop(Stack<ExpressionNode> stack, ExpressionNodeType endNodeType) {
        LinkedList<ExpressionNode> list = new LinkedList<>();
        while (!stack.isEmpty()) {
            ExpressionNode node = stack.pop();
            list.addFirst(node);
            if (node.getType() == endNodeType) {
                return list;
            }
        }
        throw new ExpressionException("缺少节点类型:" + endNodeType);
    }

    private void processTextValue(Stack<ExpressionNode> stack, StringBuilder sb, String text) {
        if (stack.isEmpty()) {
            sb.append(text).append(" ");
            return;
        }
        stack.push(new ExpressionNode(text, ExpressionNodeType.TEXT));
    }
}
