package com.ll.exam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class App {
    public void run() {
        System.out.println(" == 명언 SSG == ");

        Scanner sc = new Scanner(System.in);

        int id = 0;
        List<WiseSaying> wiseList = new ArrayList<>();

        outer: // 라벨임, 이게 적혀있는 곳에서 break되면 여기로 돌아온다
        while(true) {
            System.out.printf("명령) ");
            String cmd = sc.nextLine().trim(); // trim으로 양옆의 공백 제거

            switch (cmd) {
                case "등록":
                    System.out.printf("명언 : ");
                    String quote = sc.nextLine().trim();   // 명언 입력받기
//                    quoteList.add(lifeQuotes);  // 리스트에 명언 추가

                    System.out.printf("작가 : ");
                    String author = sc.nextLine().trim();   // 작가 입력받기
//                    authorList.add(author);     // 리스트에 작가 추가

                    id++;
                    System.out.println(id + "번 명언이 등록되었습니다.");

                    WiseSaying wiseSaying = new WiseSaying(id, quote, author);  // WiseSaying 생성
                    wiseList.add(wiseSaying);   // WiseSaying List에 WiseSaying 추가

//                    System.out.println(wiseSaying);  // 객체 확인용 출력
                    break;

                case "목록":
                    System.out.println("번호 / 작가 / 명언");
                    System.out.println("----------------");

                    for(int i = wiseList.size()-1; i >= 0; i--) {
                        WiseSaying wiseList_ = wiseList.get(i);
                        System.out.printf("%d / %s / %s\n", wiseList_.id, wiseList_.author, wiseList_.quote);
                    }
                    break;
                case "종료":
                    break outer; // 위에 outer 지점으로 돌아감 break 하면서
            }
        }

        sc.close();
    }
}
