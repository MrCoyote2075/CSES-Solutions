
import java.io.InputStream;

public class Main {
    public static class UltraFastReader {
        public final InputStream in = System.in;
        public final byte[] buffer = new byte[1 << 16];
        public int ptr = 0, len = 0;

        public byte read() throws Exception {
            if (ptr >= len) {
                ptr = 0;
                len = in.read(buffer);
                if (len <= 0)
                    return -1;
            }

            return buffer[ptr++];
        }

        public int nextInt() throws Exception {
            byte c;
            int sign = 1, val = 0;

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

        public long nextLong() throws Exception {
            byte c;
            long sign = 1, val = 0;

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

        public String next() throws Exception {
            byte c;
            StringBuilder sb = new StringBuilder();

            do
                c = read();
            while (c <= ' ');

            while (c > ' ') {
                sb.append((char) c);
                c = read();
            }

            return sb.toString();
        }

        public String nextLine() throws Exception {
            byte c;
            StringBuilder sb = new StringBuilder();

            do
                c = read();
            while (c <= ' ');

            while (c >= ' ') {
                sb.append((char) c);
                c = read();
            }

            return sb.toString();
        }

        public char nextChar() throws Exception {
            byte c;
            do
                c = read();
            while (c <= ' ');

            return (char) c;
        }
    }

    public static int n;
    public static int MAX = (int) 1e9;
    public static int MIN = (int) -1e9;
    public static long MOD = (long) 1e9 + 7;

    public static int rec(int lev, int sum, int[] prices, int[] pages, int[][] memo) {
        if (lev == -1 || sum == 0)
            return 0;

        if (memo[lev][sum] != -1)
            return memo[lev][sum];

        int ans = 0;

        if (0 <= sum - prices[lev])
            ans = Math.max(ans, rec(lev - 1, sum - prices[lev], prices, pages, memo) + pages[lev]);

        ans = Math.max(ans, rec(lev - 1, sum, prices, pages, memo));

        return memo[lev][sum] = ans;
    }

    public static int iter(int x, int[] prices, int[] pages) {

        int[][] memo = new int[n + 1][x + 1];

        for (int lev = 1; lev <= n; lev++) {
            for (int sum = 0; sum <= x; sum++) {
                int ans = 0;

                if (0 <= sum - prices[lev - 1])
                    ans = Math.max(ans, memo[lev - 1][sum - prices[lev - 1]] + pages[lev - 1]);

                ans = Math.max(ans, memo[lev - 1][sum]);

                memo[lev][sum] = ans;
            }
        }

        return memo[n][x];
    }

    // 1D memo in iteration casus problem.
    // Reason : iterating forward causes the same item to be reused multiple times
    // in the same iteration, turning the problem into an Unbounded Knapsack instead
    // of 0/1 Knapsack.

    public static int wrongOptimisedIter(int x, int[] prices, int[] pages) {

        int[] memo = new int[x + 1];

        for (int lev = 1; lev <= n; lev++) {
            for (int sum = 0; sum <= x; sum++) {
                int ans = 0;

                if (0 <= sum - prices[lev - 1])
                    ans = Math.max(ans, memo[sum - prices[lev - 1]] + pages[lev - 1]);

                ans = Math.max(ans, memo[sum]);

                memo[sum] = ans;
            }
        }

        return memo[x];
    }

    // Solution : For 0/1 Knapsack, you must iterate backward. so, backward
    // traversal prevents current item from seeing its own updated values.

    public static int optimisedIter(int x, int[] prices, int[] pages) {

        int[] memo = new int[x + 1];

        for (int lev = 1; lev <= n; lev++) {
            for (int sum = x; sum >= 0; sum--) {
                int ans = 0;

                if (0 <= sum - prices[lev - 1])
                    ans = Math.max(ans, memo[sum - prices[lev - 1]] + pages[lev - 1]);

                ans = Math.max(ans, memo[sum]);

                memo[sum] = ans;
            }
        }

        return memo[x];
    }

    public static int solve(int x, int[] prices, int[] pages) {
        // int[][] memo = new int[n][x + 1];

        // for (int[] arr : memo)
        // Arrays.fill(arr, -1);

        // return rec(n - 1, x, prices, pages, memo);

        // return iter(x, prices, pages);

        // return wrongIter(x, prices, pages);

        return optimisedIter(x, prices, pages);
    }

    public static void main(String[] args) throws Exception {
        UltraFastReader in = new UltraFastReader();

        n = in.nextInt();
        int x = in.nextInt();

        int price[] = new int[n];
        int pages[] = new int[n];

        for (int i = 0; i < n; i++)
            price[i] = in.nextInt();

        for (int i = 0; i < n; i++)
            pages[i] = in.nextInt();

        System.out.println(solve(x, price, pages));

        // for (int[] arr : memo)
        // System.out.println(Arrays.toString(arr));
    }

}