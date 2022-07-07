import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class App {
    public void run() {
        System.out.println(" == 명언 SSG == ");

        Scanner sc = new Scanner(System.in);

        int cnt = 0;

        outer: // 라벨임, 이게 적혀있는 곳에서 break되면 여기로 돌아온다
        while(true) {
            System.out.printf("명령) ");
            String cmd = sc.nextLine().trim(); // trim으로 양옆의 공백 제거

            switch (cmd) {
                case "등록":
                    System.out.printf("명언 : ");
                    String lifeQuotes = sc.nextLine().trim();
                    System.out.printf("작가 : ");
                    String author = sc.nextLine().trim();
                    cnt++;
                    System.out.println(cnt + "번 명언이 등록되었습니다.");
                    break;
                case "종료":
                    break outer; // 위에 outer 지점으로 돌아감 break 하면서
            }
        }

        sc.close();
    }
}
