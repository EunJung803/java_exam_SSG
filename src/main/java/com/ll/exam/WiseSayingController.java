package com.ll.exam;

import java.util.List;
import java.util.Scanner;

public class WiseSayingController {
    private Scanner sc;
    private WiseSayingRepository wiseSayingRepository;

    WiseSayingController(Scanner sc) {
        this.sc = sc;
        wiseSayingRepository = new WiseSayingRepository();
    }

    public void modify(Rq rq) {
        int paramID = rq.getIntParam("id", 0); // URL에 입력된 id 얻기

        if(paramID == 0) {  // URL에 입력된 id가 없으면 중지
            System.out.println("id를 입력해주세요.");
            return;
        }

        // URL에 입력된 id에 해당하는 명언객체 찾기 - findById 구현
        WiseSaying foundWiseSaying = wiseSayingRepository.findById(paramID);

        // 찾지 못하면 중지
        if(foundWiseSaying == null) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", paramID);
            return;
        }

        System.out.printf("명언(기존) : %s\n", foundWiseSaying.quote);  // 기존의 명언을 보여주고
        System.out.printf("명언 : ");
        String quote = sc.nextLine();  // 수정할 명언을 scanner로 받아 바로 foundWiseSaying.quote 부분에 저장
        System.out.printf("작가(기존) : %s\n", foundWiseSaying.author);  // 기존의 작가를 보여주고
        System.out.printf("작가 : ");
        String author = sc.nextLine();  // 수정할 작가명을 scanner로 받아 바로 foundWiseSaying.author 부분에 저장

        wiseSayingRepository.modify(paramID, quote, author);

        System.out.printf("%d번 명언이 수정되었습니다.\n", paramID);
    }

    public void write(Rq rq) {
        System.out.printf("명언 : ");
        String quote = sc.nextLine().trim();   // 명언 입력받기

        System.out.printf("작가 : ");
        String author = sc.nextLine().trim();   // 작가 입력받기

        WiseSaying wiseSaying = wiseSayingRepository.write(quote, author);

        System.out.println(wiseSaying.id + "번 명언이 등록되었습니다.");
    }

    public void remove(Rq rq) {
        int paramID = rq.getIntParam("id", 0); // URL에 입력된 id 얻기

        if(paramID == 0) {  // URL에 입력된 id가 없으면 중지
            System.out.println("id를 입력해주세요.");
            return;
        }

        // URL에 입력된 id에 해당하는 명언객체 찾기 - findById 구현
        WiseSaying foundWiseSaying = wiseSayingRepository.findById(paramID);

        // 찾지 못하면 중지
        if(foundWiseSaying == null) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", paramID);
            return;
        }

        wiseSayingRepository.remove(paramID);  // 입력된 id에 해당하는 명언객체(== 담아뒀던 명언)를 리스트에서 삭제하기
        System.out.printf("%d번 명언이 삭제되었습니다.\n", paramID);
    }

    public void list(Rq rq) {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------");

        List<WiseSaying> wiseSayings = wiseSayingRepository.findAll();

        for(int i = wiseSayings.size()-1; i >= 0; i--) {
            WiseSaying wiseList_ = wiseSayings.get(i);
            System.out.printf("%d / %s / %s\n", wiseList_.id, wiseList_.author, wiseList_.quote);
        }
    }

}
