
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        in.close();

        if (n <= 3) {
            System.out.println(1 == n ? 1 : "NO SOLUTION");
            return;
        }

        StringBuilder sb = new StringBuilder();
        int n1 = n / 2;
        for (int i = 1; i <= n1; i++) {
            sb.append(i + n1).append(" ");
            sb.append(i).append(" ");
        }

        if ((n & 1) == 1)
            sb.append(n);

        System.out.println(sb);
    }
}