package com.ll.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    // 접근을 해야하는 아래의 세 요소는 맨 위에서 정의
    private Scanner sc;
    private int id;
    private List<WiseSaying> wiseList;

    public App() {  // 생성자를 사용하여 깔끔하게 정리
        sc = new Scanner(System.in);
        wiseList = new ArrayList<>();
        id = 0;
    }

    public void run() {
        System.out.println(" == 명언 SSG == ");

        outer: // 라벨임, 이게 적혀있는 곳에서 break되면 여기로 돌아온다
        while(true) {
            System.out.printf("명령) ");
            String cmd = sc.nextLine().trim(); // trim으로 양옆의 공백 제거

            Rq rq = new Rq(cmd);

            switch (rq.getPath()) {

                // 적절한 역할군으로 넘겨준다 == 디스패치
                case "등록":
                    write(rq);  // write 구현 - 리팩토링
                    break;

                case "삭제":
                    remove(rq);  // remove 구현 - 리펙토링
                    break;

                case "목록":
                    list(rq);  // list 구현 - 리펙토링
                    break;

                case "종료":
                    break outer; // 위에 outer 지점으로 돌아감 break 하면서
            }
        }

        sc.close();
    }

    private void write(Rq rq) {
        System.out.printf("명언 : ");
        String quote = sc.nextLine().trim();   // 명언 입력받기

        System.out.printf("작가 : ");
        String author = sc.nextLine().trim();   // 작가 입력받기

        id++;
        System.out.println(id + "번 명언이 등록되었습니다.");

        WiseSaying wiseSaying = new WiseSaying(id, quote, author);  // WiseSaying 생성
        wiseList.add(wiseSaying);   // WiseSaying List에 WiseSaying 추가
    }

    private void remove(Rq rq) {
        int paramID = rq.getIntParam("id", 0); // URL에 입력된 id 얻기

        if(paramID == 0) {  // URL에 입력된 id가 없으면 중지
            System.out.println("id를 입력해주세요.");
            return;
        }

        // 명언 객체를 담을 WiseSaying형 변수
        WiseSaying foundWiseSaying = null;

        // URL에 입력된 id가 해당하는 명언객체 찾아서 변수에 담기 (저장)
        for(WiseSaying wiseSaying___ : wiseList) {
            if(wiseSaying___.id == paramID) {
                foundWiseSaying = wiseSaying___;
            }
        }

        // 찾지 못하면 중지
        if(foundWiseSaying == null) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", paramID);
            return;
        }

        wiseList.remove(foundWiseSaying);  // 입력된 id에 해당하는 명언객체(== 담아뒀던 명언)를 리스트에서 삭제하기
        System.out.printf("%d번 명언이 삭제되었습니다.\n", paramID);
    }

    private void list(Rq rq) {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------");

        for(int i = wiseList.size()-1; i >= 0; i--) {
            WiseSaying wiseList_ = wiseList.get(i);
            System.out.printf("%d / %s / %s\n", wiseList_.id, wiseList_.author, wiseList_.quote);
        }
    }
}
