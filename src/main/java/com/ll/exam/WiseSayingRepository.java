package com.ll.exam;

import java.util.ArrayList;
import java.util.List;

public class WiseSayingRepository {

    public int id;
    public List<WiseSaying> wiseList;

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
    }
}
