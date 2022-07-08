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
        return "com.ll.exam.WiseSaying{" +
                "id=" + id +
                ", quote='" + quote + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

}
