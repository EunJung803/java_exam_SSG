package com.ll.exam;

public class Rq {
    String url;
    String path;
    String queryStr;

    // getIntParam과 getPath 할때마다 ?을 기준으로 쪼개야 하기 때문에 두 부분에 다 존재
    // 따라서 생성자을 통해 한번 받았을 때 쪼개주어 리팩토링 진행
    public Rq(String url) {  // Rq 생성자
        this.url = url;
        String[] urlBits = url.split("\\?", 2);  // 미리 쪼개놓는다 ~

        this.path = urlBits[0];

        if(urlBits.length == 2) {  // ?를 써서 정보를 물어보지 않는 명령이 존재하기 때문에 조건문으로 걸러준다.
            this.queryStr = urlBits[1];
        }
    }

    public int getIntParam(String paramName, int defaultValue) {
        if (queryStr == null) {  // 값이 들어가있지 않으면 null이 들어가있음
            return defaultValue;
        }

        String[] bits = queryStr.split("&");

        for (String urlBit : bits) {
            String[] paramNameAndValue = urlBit.split("=", 2);
            String paramName_ = paramNameAndValue[0];
            String paramValue = paramNameAndValue[1];

            if (paramName.equals(paramName_)) {
                return Integer.parseInt(paramValue);
            }
        }

        return defaultValue;
    }

    public String getPath() {
        return path;
    }
}
