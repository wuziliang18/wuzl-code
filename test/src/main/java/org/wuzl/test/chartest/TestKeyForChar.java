package org.wuzl.test.chartest;

import java.io.Console;
import java.io.IOException;

public class TestKeyForChar {
	public static void main(String[] args) throws IOException {
		System.out.println((byte) 0x80);
		System.out.println((byte) 0x40);
		System.out.println((byte) 0x20);
		for (int i = 0; i < 133; i++)
        {
            char a = (char)i;
            System.out.println("Char("+i+")----"+a);
        }
	}
}
