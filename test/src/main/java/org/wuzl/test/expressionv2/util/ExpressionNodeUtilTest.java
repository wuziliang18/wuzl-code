package org.wuzl.test.expressionv2.util;

import java.util.ArrayList;
import java.util.List;

public class ExpressionNodeUtilTest {
    public static void main(String[] args) {
        List<Character> list = new ArrayList<>();
        list.add('a');
        list.add('c');
        list.add('z');
        list.add('A');
        list.add('C');
        list.add('Z');

        list.add('0');
        list.add('6');
        list.add('9');

        list.add('.');
        list.add('$');

        list.add('#');
        list.add(' ');
        list.add('+');
        list.add('-');
        list.add('*');
        list.add('/');
        list.add(')');
        list.add('(');
        list.add('!');
        list.add('%');
        for (char c : list) {
            System.out.println(c + " is " + ExpressionNodeUtil.isFunctionOrVariableName(c));
        }
    }
}
