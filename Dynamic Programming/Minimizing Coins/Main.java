
// import java.util.Arrays;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static Integer[] dp;

    public static int rec(int x, int[] arr) {
        if (x == 0)
            return 0;

        int ans = (int) 1e9;

        if (dp[x] != null)
            return dp[x];

        for (int coin : arr) {
            if (x - coin >= 0)
                ans = Math.min(ans, rec(x - coin, arr) + 1);
        }

        return dp[x] = ans;
    }

    public static int iter(int target, int[] arr) {
        int[] memo = new int[target + 2];

        for (int x = 1; x <= target; x++) {
            int ans = (int) 1e9;

            for (int coin : arr) {
                if (x - coin >= 0)
                    ans = Math.min(ans, memo[x - coin] + 1);
            }

            memo[x] = ans;
        }

        return memo[target];
    }

    public static int solve(int[] arr, int x) {
        dp = new Integer[x + 2];
        Arrays.sort(arr);

        int l = -1, r = arr.length;
        while (++l < --r) {
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
        }

        // int ans = rec(x, arr);
        int ans = iter(x, arr);

        return ans == (int) 1e9 ? -1 : ans;
    }

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int n = in.nextInt();
            int x = in.nextInt();
            int arr[] = new int[n];

            for (int i = 0; i < n; i++)
                arr[i] = in.nextInt();

            System.out.println(solve(arr, x));
        }
    }
}
