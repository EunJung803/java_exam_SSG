package com.ll.exam;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    @Test
    void 파일에_있는_JSON을_객체로_변환() {
        Util.mkdir("test_data");
        WiseSaying wiseSaying = new WiseSaying(1, "내 사전에 불가능은 없다.", "나폴레옹");
        Util.saveToFile("test_data/1.json", wiseSaying.toJson());  // toJson 메서드 실행하기

        String rs = Util.readFromFile("test_data/1.json");
        Map<String, Object> map = Util.jsonToMap(rs);   // map으로 변환하기
        WiseSaying loadedWiseSaying = new WiseSaying(map);      // WiseSaying 객체로 변환하기

        assertEquals(1, map.get("id"));
        assertEquals("내 사전에 불가능은 없다.", map.get("quote"));
        assertEquals("나폴레옹", map.get("author"));
    }

    @Test
    void 파일에_있는_JSON을_맵으로_변환() {
        Util.mkdir("test_data");
        WiseSaying wiseSaying = new WiseSaying(1, "내 사전에 불가능은 없다.", "나폴레옹");
        Util.saveToFile("test_data/1.json", wiseSaying.toJson());  // toJson 메서드 실행하기

        String rs = Util.readFromFile("test_data/1.json");
        Map<String, Object> map = Util.jsonToMap(rs);

        assertEquals(1, map.get("id"));
        assertEquals("내 사전에 불가능은 없다.", map.get("quote"));
        assertEquals("나폴레옹", map.get("author"));
    }

    @Test
    void 파일에_객체를_저장() {
        Util.mkdir("test_data");
        WiseSaying wiseSaying = new WiseSaying(1, "내 사전에 불가능은 없다.", "나폴레옹");
        Util.saveToFile("test_data/1.json", wiseSaying.toJson());  // toJson 메서드 실행하기

        String rs = Util.readFromFile("test_data/1.json");
        assertEquals(wiseSaying.toJson(), rs);
    }

    @Test
    void 파일에_내용쓰기() {
        Util.mkdir("test_data");  // 폴더 생성
        Util.saveToFile("test_data/1.json", "내용\n내용");  // json 파일 생성 / body == 내용

        String rs = Util.readFromFile("test_data/1.json");

        assertEquals("내용\n내용", rs);
    }

    @Test
    public void Rq__getPath() {
        Rq rq = new Rq("삭제?id=1");

        String path = rq.getPath();

        assertEquals("삭제", path);
    }

    @Test
    public void Rq__getIntParam() {
        Rq rq = new Rq("삭제?id=1");

        int id = rq.getIntParam("id", 0);

        assertEquals(1, id);
    }

    @Test
    public void Rq__getIntParam__2() {
        Rq rq = new Rq("검색?id=10&no=1");

        int id = rq.getIntParam("id", 0);
        int no = rq.getIntParam("no", 0);

        assertEquals(10, id);
        assertEquals(1, no);
    }

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
