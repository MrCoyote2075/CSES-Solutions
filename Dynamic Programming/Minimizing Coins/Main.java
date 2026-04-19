
// import java.util.Arrays;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static Integer[][] dp;

    public static int rec(int lev, int x, int[] arr) {
        if (x == 0)
            return 0;

        if (lev == arr.length || x < 0)
            return (int) 1e9;

        if (dp[lev][x] != null)
            return dp[lev][x];

        int ans = (int) 1e9;

        // ans = Math.min(ans, rec(lev + 1, x % arr[lev], arr) + (x / arr[lev]));
        ans = Math.min(ans, rec(lev, x - arr[lev], arr) + 1);
        ans = Math.min(ans, rec(lev + 1, x, arr));

        return dp[lev][x] = ans;
    }

    public static int iter(int x, int[] arr) {


        for(int i = arr.length - 1; i >= 0 ; )

        // if (x == 0)
        // return 0;

        // if (lev == arr.length || x < 0)
        // return (int) 1e9;

        // if (dp[lev][x] != null)
        // return dp[lev][x];

        // int ans = (int) 1e9;

        // // ans = Math.min(ans, rec(lev + 1, x % arr[lev], arr) + (x / arr[lev]));
        // ans = Math.min(ans, rec(lev, x - arr[lev], arr) + 1);
        // ans = Math.min(ans, rec(lev + 1, x, arr));

        return 0;
    }

    public static int solve(int[] arr, int x) {
        dp = new Integer[arr.length][x + 1];
        Arrays.sort(arr);
        int l = -1, r = arr.length;
        while (++l < --r) {
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
        }

        int ans = rec(0, x, arr);

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
