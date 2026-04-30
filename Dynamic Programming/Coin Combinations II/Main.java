
import java.io.InputStream;

public class Main {
    public static class UltraFastReader {
        public final InputStream in = System.in;
        public final byte[] buffer = new byte[1 << 16];
        public int ptr = 0, len = 0;

        public int read() throws Exception {
            if (ptr >= len) {
                ptr = 0;
                len = in.read(buffer);
                if (len <= 0)
                    return -1;
            }

            return buffer[ptr++];
        }

        public int nextInt() throws Exception {
            int sign = 1, val = 0, c;

            do
                c = read();
            while (c <= ' ');

            if (c == '-') {
                sign = -1;
                c = read();
            }

            while (c > ' ') {
                val = (val * 10) + (c - '0');
                c = read();
            }

            return val * sign;
        }
    }

    public static int n;
    public static int MAX = (int) 1e9;
    public static int MIN = (int) -1e9;
    public static int MOD = (int) 1e9 + 7;

    public static long rec(int lev, int sum, int[] arr) {
        if (sum == 0)
            return 1;

        long ans = 0;

        for (int i = lev; 0 <= i; i--) {
            if (0 <= sum - arr[i])
                ans = (ans + rec(i, sum - arr[i], arr)) % MOD;
        }

        return ans;
    }

    public static long[][] memo;

    public static long oldMemorization(int lev, int sum, int[] arr) {
        if (sum == 0)
            return 1;

        if (memo[lev][sum] != -1)
            return memo[lev][sum];

        long ans = 0;

        for (int i = lev; 0 <= i; i--) {
            if (0 <= sum - arr[i])
                ans = (ans + oldMemorization(i, sum - arr[i], arr)) % MOD;
        }

        return memo[lev][sum] = ans;
    }

    public static long memorization(int lev, int sum, int[] arr) {
        if (sum == 0)
            return 1;

        if (memo[lev][sum] != -1)
            return memo[lev][sum];

        long ans = 0;

        if (0 <= sum - arr[lev])
            ans += memorization(lev, sum - arr[lev], arr);

        if (0 <= lev - 1)
            ans += memorization(lev - 1, sum, arr);

        return memo[lev][sum] = (ans % MOD);
    }

    public static long oldTabulation(int target, int[] arr) {

        for (int lev = 0; lev < n; lev++) {
            memo[lev][0] = 1;

            for (int sum = 1; sum <= target; sum++) {
                long ans = 0;

                if (0 <= sum - arr[lev])
                    ans += memo[lev][sum - arr[lev]];

                if (0 <= lev - 1)
                    ans += memo[lev - 1][sum];

                memo[lev][sum] = (ans % MOD);
            }
        }

        return memo[n - 1][target];
    }

    // TLE : in CSES -> cuz of MODULO Operation
    public static long tabulation1(int target, int[] arr) {
        long[] dp = new long[target + 1];

        for (int lev = 0; lev < n; lev++) {
            dp[0] = 1;

            for (int sum = 1; sum <= target; sum++) {
                long ans = 0;

                if (0 <= sum - arr[lev])
                    ans += dp[sum - arr[lev]];

                ans += dp[sum];

                dp[sum] = ans % MOD;
            }
        }

        return dp[target];
    }

    public static int tabulation(int target, int[] arr) {
        int[] dp = new int[target + 1];

        for (int lev = 0; lev < n; lev++) {
            dp[0] = 1;
            for (int sum = arr[lev]; sum <= target; sum++) {
                int ans = dp[sum] + dp[sum - arr[lev]];
                if (ans >= MOD)
                    ans -= MOD;

                dp[sum] = ans;
            }
        }

        return dp[target];
    }

    public static int solve(int target, int[] nums) {
        // memo = new long[n][target + 1];

        // for (long[] arr : memo)
        // Arrays.fill(arr, -1);

        // return rec(n - 1, target, arr);
        // return memorization(n - 1, target, nums);
        // return oldTabulation(target, nums);
        return tabulation(target, nums);
    }

    public static void main(String[] args) throws Exception {
        UltraFastReader in = new UltraFastReader();
        n = in.nextInt();
        int x = in.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++)
            arr[i] = in.nextInt();

        System.out.println(solve(x, arr));
    }

}