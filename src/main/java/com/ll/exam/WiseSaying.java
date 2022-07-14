package com.ll.exam;

public class WiseSaying {
    int id;
    String quote;
    String author;

    public WiseSaying(int id, String quote, String author) {
        this.id = id;
        this.quote = quote;
        this.author = author;
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
}
