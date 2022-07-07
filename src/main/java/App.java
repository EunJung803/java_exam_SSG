import java.util.Scanner;

public class App {
    public void run() {
        System.out.println(" == 명언 SSG == ");

        Scanner sc = new Scanner(System.in);

        outer: // 라벨임, 이게 적혀있는 곳에서 break되면 여기로 돌아온다
        while(true) {
            System.out.printf("명령) ");
            String cmd = sc.nextLine().trim(); // trim으로 양옆의 공백 제거

            switch (cmd) {
                case "종료":
                    break outer; // 위에 outer 지점으로 돌아감 break 하면서
            }
        }

//        sc.close();
    }
}
