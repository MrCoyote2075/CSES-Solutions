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

        public char nextChar() throws Exception {
            int c;
            do
                c = read();
            while (c <= ' ');

            return (char) c;
        }
    }

    public static int n;
    public static int MOD = (int) 1e9 + 7;

    // Raw Recursion without Optimization...
    // Actual Solution Flow...
    public static int rec(int i, int j, char[][] grid) {

        if (i == 0 && j == 0) // Base Condition...
            return 1;

        int ans = 0;

        if (0 <= i - 1 && grid[i - 1][j] != '*') { // Travel Down...
            ans += rec(i - 1, j, grid);

            if (ans >= MOD)
                ans = ans - MOD;
        }

        if (0 <= j - 1 && grid[i][j - 1] != '*') { // Travel Right...
            ans += rec(i, j - 1, grid);

            if (ans >= MOD)
                ans = ans - MOD;
        }

        return ans;
    }

    // Recursion Based, 2D - DP (Memorization)...
    public static int memorization(int i, int j, char[][] grid, int[][] memo) {

        if (i == 0 && j == 0) // Base Condition...
            return 1;

        if (memo[i][j] != -1)
            return memo[i][j];

        int ans = 0;

        if (0 <= i - 1 && grid[i - 1][j] != '*') { // Travel Down...
            ans += memorization(i - 1, j, grid, memo);

            if (ans >= MOD)
                ans = ans - MOD;
        }

        if (0 <= j - 1 && grid[i][j - 1] != '*') { // Travel Right...
            ans += memorization(i, j - 1, grid, memo);

            if (ans >= MOD)
                ans = ans - MOD;
        }

        return memo[i][j] = ans; // Memorization...
    }

    // Iteration Based, Faster than Recursion...
    public static int tabulation(char[][] grid) {

        int[][] memo = new int[n][n]; // 2D - DP...

        for (int i = 0; i < n; i++) { // start with base Value (Base Condition = 0) -> end with Actual (problem = n).
            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0) { // Base Condition...
                    memo[0][0] = 1;
                    continue;
                }

                int ans = 0;
                if (0 <= i - 1 && grid[i - 1][j] != '*') { // Travel Down...
                    ans += memo[i - 1][j];

                    if (ans >= MOD)
                        ans = ans - MOD;
                }

                if (0 <= j - 1 && grid[i][j - 1] != '*') { // Travel Right...
                    ans += memo[i][j - 1];

                    if (ans >= MOD)
                        ans = ans - MOD;
                }

                memo[i][j] = ans; // Memorization...
            }
        }

        return memo[n - 1][n - 1];
    }

    // Space Optimized, Iteration...
    // Reusing the same Array Everytime...
    public static int optimizedTabulation(char[][] grid) {

        int[] memo = new int[n]; // 1D - DP...

        for (int i = 0; i < n; i++) { // start with base Value (Base Condition = 0) -> end with Actual (problem = n).
            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0) { // Base Condition...
                    memo[0] = 1;
                    continue;
                }

                int ans = 0;
                if (0 <= i - 1 && grid[i - 1][j] != '*') { // Travel Down...
                    ans += memo[j];

                    if (ans >= MOD)
                        ans = ans - MOD;
                }

                if (0 <= j - 1 && grid[i][j - 1] != '*') { // Travel Right...
                    ans += memo[j - 1];

                    if (ans >= MOD)
                        ans = ans - MOD;
                }

                memo[j] = ans; // Memorization...
            }
        }

        return memo[n - 1];
    }

    public static int solve(char[][] grid) {
        if (grid[0][0] == '*' || grid[n - 1][n - 1] == '*')
            return 0;

        // // 1 :
        // return rec(n - 1, n - 1, grid);

        // // 2 :
        // int[][] memo = new int[n][n];
        // for (int[] arr : memo)
        // Arrays.fill(arr, -1);

        // return (int) memorization(n - 1, n - 1, grid, memo);

        // // 3 :
        // return tabulation(grid);

        // 4 :
        return optimizedTabulation(grid);
    }

    public static void main(String[] args) throws Exception {
        UltraFastReader in = new UltraFastReader();
        n = in.nextInt();
        char grid[][] = new char[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = in.nextChar();

        System.out.println(solve(grid));
    }
}
