import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class App {
    public void run() {
        System.out.println(" == 명언 SSG == ");

        Scanner sc = new Scanner(System.in);

        int cnt = 0;
        ArrayList<String> quoteList = new ArrayList<String>();
        ArrayList<String> authorList = new ArrayList<String>();

        outer: // 라벨임, 이게 적혀있는 곳에서 break되면 여기로 돌아온다
        while(true) {
            System.out.printf("명령) ");
            String cmd = sc.nextLine().trim(); // trim으로 양옆의 공백 제거

            switch (cmd) {
                case "등록":
                    System.out.printf("명언 : ");
                    String lifeQuotes = sc.nextLine().trim();   // 명언 입력받기
                    quoteList.add(lifeQuotes);  // 리스트에 명언 추가

                    System.out.printf("작가 : ");
                    String author = sc.nextLine().trim();   // 작가 입력받기
                    authorList.add(author);     // 리스트에 작가 추가

                    cnt++;
                    System.out.println(cnt + "번 명언이 등록되었습니다.");
                    break;

                case "목록":
                    System.out.println("번호 / 작가 / 명언");
                    System.out.println("----------------");
//                    ArrayList 요소 확인용 출력
//                    for(String authorStr : authorList) {
//                        System.out.println(authorStr);
//                    }
//                    for(String quoteStr : quoteList) {
//                        System.out.println(quoteStr);
//                    }
                    for(int i=cnt-1; i>=0; i--) {
                        System.out.printf("%d / %s / %s\n", i+1, authorList.get(i), quoteList.get(i));
                    }
                    break;

                case "종료":
                    break outer; // 위에 outer 지점으로 돌아감 break 하면서
            }
        }

        sc.close();
    }
}
