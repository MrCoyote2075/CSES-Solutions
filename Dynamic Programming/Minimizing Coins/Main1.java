import java.io.InputStream;
import java.io.PrintWriter;

public class Main1 {
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
    public static int[][] oldDp;

    public static int oldRec(int lev, int sum, int[] arr) {

        if (sum == 0)
            return 0;

        int ans = (int) 1e9;

        if (oldDp[lev][sum] != -1)
            return oldDp[lev][sum];

        if (0 <= sum - arr[lev])
            ans = Math.min(ans, oldRec(lev, sum - arr[lev], arr) + 1);

        if (0 <= lev - 1)
            ans = Math.min(ans, oldRec(lev - 1, sum, arr));

        return oldDp[lev][sum] = ans;
    }

    public static int oldIter(int target, int[] arr) {

        for (int lev = 0; lev < n; lev++) {
            for (int sum = 0; sum <= target; sum++) {
                int ans = (int) 1e9;
                if (sum == 0)
                    oldDp[lev][sum] = 0;

                else {
                    if (0 <= sum - arr[lev])
                        ans = Math.min(ans, oldDp[lev][sum - arr[lev]] + 1);

                    if (0 <= lev - 1)
                        ans = Math.min(ans, oldDp[lev - 1][sum]);

                    oldDp[lev][sum] = ans;
                }
            }
        }

        return oldDp[n - 1][target];
    }

    public static int oldSolve(int target, int[] arr) {
        oldDp = new int[n + 1][target + 1];
        int ans = oldIter(target, arr);

        // recursive oldDp..
        // for (int[] row : oldDp)
        // Arrays.fill(row, -1);
        // System.out.println(Arrays.toString(row));

        // int ans = oldRec(n - 1, target, arr);

        return ans == (int) 1e9 ? -1 : ans;
    }

    public static int[] dp;

    public static int rec(int sum, int[] arr) {
        if (sum == 0) // Base Condition ...
            return 0;

        if (dp[sum] != -1)
            return dp[sum];

        int ans = (int) 1e9;

        for (int num : arr)
            if (0 <= sum - num)
                ans = Math.min(ans, rec(sum - num, arr) + 1);

        return dp[sum] = ans;
    }

    public static int iter(int target, int[] arr) {

        for (int sum = 0; sum <= target; sum++) {

            if (sum == 0) // Base Condition ...
                continue;

            int ans = (int) 1e9;

            for (int num : arr) {
                if (0 <= sum - num)
                    ans = Math.min(ans, dp[sum - num] + 1);
            }

            dp[sum] = ans;
        }

        return dp[target] == (int) 1e9 ? -1 : dp[target];
    }

    public static int solve(int target, int[] arr) {
        dp = new int[target + 1];
        return iter(target, arr);

        // Arrays.fill(dp, -1);

        // int ans = rec(target, arr);

        // return ans == (int) 1e9 ? -1 : ans;
    }

    public static void main(String[] args) throws Exception {
        UltraFastReader in = new UltraFastReader();
        try (PrintWriter out = new PrintWriter(System.out)) {
            n = in.nextInt();
            int x = in.nextInt();

            int[] arr = new int[n];
            for (int i = 0; i < n; i++)
                arr[i] = in.nextInt();

            // out.println(oldSolve(x, arr));
            out.println(solve(x, arr));

            out.flush();
        }
    }
}
