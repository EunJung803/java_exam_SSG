package com.ll.exam;

import java.util.HashMap;
import java.util.Map;

public class Rq {
    String url;
    String path;
//    String queryStr;
    Map<String, String> queryParams;  // Map 선언

    // getIntParam과 getPath 할때마다 ?을 기준으로 쪼개야 하기 때문에 두 부분에 다 존재
    // 따라서 생성자을 통해 한번 받았을 때 쪼개주어 리팩토링 진행
    public Rq(String url) {  // Rq 생성자
        this.url = url;
        String[] urlBits = url.split("\\?", 2);  // 미리 쪼개놓는다 ~

        this.path = urlBits[0];

        queryParams = new HashMap<>();  // HashMap 생성

        if(urlBits.length == 2) {  // ?를 써서 정보를 물어보지 않는 명령이 존재하기 때문에 조건문으로 걸러준다.
            String queryStr = urlBits[1];

            String[] paramBits = queryStr.split("&");

            for (String paramBit : paramBits) {
                String[] paramNameAndValue = paramBit.split("=", 2);

                if (paramNameAndValue.length == 1) {
                    continue;
                }

                String paramName = paramNameAndValue[0].trim();
                String paramValue = paramNameAndValue[1].trim();

                queryParams.put(paramName, paramValue);
            }
        }
    }

    public int getIntParam(String paramName, int defaultValue) {
        if (queryParams.containsKey(paramName) == false) {  // 값이 들어가있지 않으면
            return defaultValue;
        }

        String paramValue = queryParams.get(paramName);

        if(paramValue.length() == 0) {
            return defaultValue;
        }

        return Integer.parseInt(paramValue);
    }

    public String getPath() {
        return path;
    }
}
