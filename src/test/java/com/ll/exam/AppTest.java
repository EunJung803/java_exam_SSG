package com.ll.exam;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    @Test
    public void 문자열을_스캐너의_입력으로_설정() {
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

    @Test
    public void 표준출력을_리다이렉션하여_결과를_문자열로_받기() throws IOException {
        /*
        일종의 블랙박스 역할

        표준출력을 리다이렉션
        -> 아무리 출력해도 output에 차곡차곡 쌓인다 (출력되지 X)

        표준출력을 원상복구
        -> 쌓이지 않고 다시 원상복구 시킴 출력을
         */

        //표준출력을 리다이렉션
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        System.out.println("안녕");

        String rs = output.toString().trim();

        //표준출력을 원상복구
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        output.close();

        assertEquals("안녕", rs);
    }
}
