package org.wuzl.test.algorithm;

public class RemoveChar {
    public static void main(String[] args) {
        RemoveChar obj = new RemoveChar();
        System.out.println(obj.removeChar("asdadsfas", 'a'));
    }

    public String removeChar(String input, char c) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != c) {
                sb.append(input.charAt(i));
            }
        }
        return sb.toString();
    }
}
