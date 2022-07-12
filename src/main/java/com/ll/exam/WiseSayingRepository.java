package com.ll.exam;

import java.util.ArrayList;
import java.util.List;

public class WiseSayingRepository {

    private int id;
    private List<WiseSaying> wiseList;

    WiseSayingRepository() {
        wiseList = new ArrayList<>();
        id = 0;
    }

    public WiseSaying findById(int paramID) {
        // URL에 입력된 id가 해당하는 명언객체 찾아서 변수에 담기 (저장)
        for(WiseSaying wiseSaying___ : wiseList) {
            if(wiseSaying___.id == paramID) {
                return wiseSaying___;
            }
        }

        // 다 돌았는데 없으면 null
        return null;

        // 하나 찾기
    }

    public List<WiseSaying> findAll() {
        return wiseList;

        // 다 찾기
    }

    public WiseSaying write(String content, String author) {
        int wiseid = ++id;
        WiseSaying wiseSaying = new WiseSaying(wiseid, content, author);
        wiseList.add(wiseSaying);

        // 파일저장

        return wiseSaying;
    }

    public void remove(int paramId) {
        WiseSaying foundWiseSaying = findById(paramId);
        wiseList.remove(foundWiseSaying);

        // 파일 삭제
    }

    public void modify(int paramId, String content, String author) {
        WiseSaying foundWiseSaying = findById(paramId);
        foundWiseSaying.quote = content;
        foundWiseSaying.author = author;

        // 파일 수정
    }

}
