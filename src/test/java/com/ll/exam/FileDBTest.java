package com.ll.exam;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileDBTest {

    @BeforeEach     // 각 테스트 전에 미리 수행해주는 역할 ( 매 테스트를 시작하면서 deleteDir, mkdir을 해줄 필요가 없어졌다 이제 )
    void beforeEach() {
        Util.deleteDir("test_data");
        Util.mkdir("test_data");
    }

    @Test
    void 특정_폴더에_존재하는_모든_파일의_이름들을_가져온다() {
        Util.saveNumberToFile("test_data/1.txt", 1);
        Util.saveNumberToFile("test_data/2.txt", 1);
        Util.saveNumberToFile("test_data/3.txt", 1);

        List<String> fileNames = Util.getFileNamesFromDir("test_data");

        assertEquals(((List<?>) fileNames).get(0), "1.txt");
        assertEquals(fileNames.get(1), "2.txt");
        assertEquals(fileNames.get(2), "3.txt");
    }

    @Test
    void 파일에_숫자_저장() {
        Util.saveNumberToFile("test_data/last_id.txt", 100);
        int rs = Util.readNumberFromFile("test_data/last_id.txt", 0);
        assertEquals(100, rs);
    }

    @Test
    void 파일에_있는_JSON을_객체로_변환() {
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
        WiseSaying wiseSaying = new WiseSaying(1, "내 사전에 불가능은 없다.", "나폴레옹");
        Util.saveToFile("test_data/1.json", wiseSaying.toJson());  // toJson 메서드 실행하기

        String rs = Util.readFromFile("test_data/1.json");
        assertEquals(wiseSaying.toJson(), rs);
    }

    @Test
    void 파일에_내용쓰기() {
        Util.saveToFile("test_data/1.json", "내용\n내용");  // json 파일 생성 / body == 내용

        String rs = Util.readFromFile("test_data/1.json");

        assertEquals("내용\n내용", rs);
    }
}
