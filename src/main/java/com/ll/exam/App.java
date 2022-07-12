package com.ll.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private Scanner sc;

    public App() {  // 생성자를 사용하여 깔끔하게 정리
        sc = new Scanner(System.in);
    }

    public void run() {
        System.out.println(" == 명언 SSG == ");

        // 프로그램이 시작되면 WiseSayingController를 생성
        WiseSayingController wiseSayingController = new WiseSayingController(sc);

        outer: // 라벨임, 이게 적혀있는 곳에서 break되면 여기로 돌아온다
        while(true) {
            System.out.printf("명령) ");
            String cmd = sc.nextLine().trim(); // trim으로 양옆의 공백 제거

            Rq rq = new Rq(cmd);

            switch (rq.getPath()) {

                // 적절한 역할군으로 넘겨준다 == 디스패치
                case "등록":
                    wiseSayingController.write(rq);
                    break;

                case "삭제":
                    wiseSayingController.remove(rq);
                    break;

                case "수정":
                    wiseSayingController.modify(rq);
                    break;

                case "목록":
                    wiseSayingController.list(rq);
                    break;

                case "종료":
                    break outer; // 위에 outer 지점으로 돌아감 break 하면서
            }
        }

        sc.close();
    }


}
