package com.ll.exam;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    @Test
    public void scannerTest() {
        String input = """
                등록
                명언1
                작가1
                """.stripIndent();
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner sc = new Scanner(in);

        String cmd1 = sc.nextLine().trim();
        String quote1 = sc.nextLine().trim();
        String author1 = sc.nextLine().trim();

        assertEquals("등록", cmd1);
        assertEquals("명언1", quote1);
        assertEquals("작가1", author1);
    }
}
