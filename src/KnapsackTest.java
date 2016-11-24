import java.io.IOException;
import java.util.Scanner;

/**
 * Created by jo930_000 on 2016-11-21.
 */
public class KnapsackTest {
    public static void main(String[] args) throws IOException {
        Knapsack test = new Knapsack();
        test.make_item();

        Scanner scan = new Scanner(System.in);
        System.out.print("배낭의 사이즈를 입력하세요(0-50) : ");
        int input = scan.nextInt();

        if(input < 0 || input > 50) {
            System.out.println("범위 오류입니다. 프로그램을 종료합니다.");
        }
        else {
            test.print_table(input);
            test.item_backtracking();
        }
    }
}
