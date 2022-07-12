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

                case "수정":
                    modify(rq);  // modify 구현 - 수정을 위한 메서드
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

    private void modify(Rq rq) {
        int paramID = rq.getIntParam("id", 0); // URL에 입력된 id 얻기

        if(paramID == 0) {  // URL에 입력된 id가 없으면 중지
            System.out.println("id를 입력해주세요.");
            return;
        }

        // URL에 입력된 id에 해당하는 명언객체 찾기 - findById 구현
        WiseSaying foundWiseSaying = findById(paramID);

        // 찾지 못하면 중지
        if(foundWiseSaying == null) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", paramID);
            return;
        }

        System.out.printf("명언(기존) : %s\n", foundWiseSaying.quote);  // 기존의 명언을 보여주고
        System.out.printf("명언 : ");
        foundWiseSaying.quote = sc.nextLine();  // 수정할 명언을 scanner로 받아 바로 foundWiseSaying.quote 부분에 저장
        System.out.printf("작가(기존) : %s\n", foundWiseSaying.author);  // 기존의 작가를 보여주고
        System.out.printf("작가 : ");
        foundWiseSaying.author = sc.nextLine();  // 수정할 작가명을 scanner로 받아 바로 foundWiseSaying.author 부분에 저장

        System.out.printf("%d번 명언이 수정되었습니다.\n", paramID);
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

        // URL에 입력된 id에 해당하는 명언객체 찾기 - findById 구현
        WiseSaying foundWiseSaying = findById(paramID);

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

    private WiseSaying findById(int paramID) {
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
