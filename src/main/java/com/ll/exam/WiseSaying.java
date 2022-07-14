package com.ll.exam;

import java.util.Map;

public class WiseSaying {
    int id;
    String quote;
    String author;

    public WiseSaying(int id, String quote, String author) {
        this.id = id;
        this.quote = quote;
        this.author = author;
    }

    public WiseSaying(Map<String, Object> map) {
        // 오브젝트 타입이기 때문에 형변환을 각자 해주어야 한다.
        this.id = (int) map.get("id");
        this.quote = (String) map.get("quote");
        this.author = (String) map.get("author");
    }

    @Override
    public String toString() {
        return "WiseSaying{" +
                "id=" + id +
                ", quote='" + quote + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    public String toJson() {  // Json 형태로 변환해주기
        return """
                {
                    "id": %d,
                    "content": "%s",
                    "author": "%s"
                }
                """
                .stripIndent()      // 문자열 안의 모든 라인에 strip를 적용하여 앞 뒤 공백을 제거
                .formatted(id, quote, author)       // 문자열의 형식 지정
                .trim();        // 문자열의 앞&뒤 공백 제거
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o instanceof WiseSaying == false) return false;

        WiseSaying other = (WiseSaying) o;

        if(this.id != other.id) return false;
        if(this.quote.equals(other.quote) == false) return false;
        if(this.author.equals(other.author) == false) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (quote != null ? quote.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);

        return result;
    }
}
