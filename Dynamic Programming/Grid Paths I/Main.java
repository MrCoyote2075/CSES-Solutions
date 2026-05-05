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
    public static int MAX = (int) 1e9;
    public static int MIN = (int) -1e9;
    public static int MOD = (int) 1e9 + 7;

    public static int rec(int i, int j, char[][] grid) {

        if (i == 0 && j == 0)
            return 1;

        int ans = 0;

        if (0 <= i - 1 && grid[i - 1][j] != '*')
            ans += rec(i - 1, j, grid);

        if (0 <= j - 1 && grid[i][j - 1] != '*')
            ans += rec(i, j - 1, grid);

        return ans;
    }

    public static int memorization(int i, int j, char[][] grid, int[][] memo) {

        if (i == 0 && j == 0)
            return 1;

        if (memo[i][j] != -1)
            return memo[i][j];

        int ans = 0;

        if (0 <= i - 1 && grid[i - 1][j] != '*') {
            ans += memorization(i - 1, j, grid, memo);

            if (ans >= MOD)
                ans = ans - MOD;
        }

        if (0 <= j - 1 && grid[i][j - 1] != '*') {
            ans += memorization(i, j - 1, grid, memo);

            if (ans >= MOD)
                ans = ans - MOD;
        }

        return memo[i][j] = ans;
    }

    public static int tabulation(char[][] grid, int[][] memo) {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0) {
                    memo[0][0] = 1;
                    continue;
                }

                int ans = 0;
                if (0 <= i - 1 && grid[i - 1][j] != '*') {
                    ans += memo[i - 1][j];

                    if (ans >= MOD)
                        ans = ans - MOD;
                }

                if (0 <= j - 1 && grid[i][j - 1] != '*') {
                    ans += memo[i][j - 1];

                    if (ans >= MOD)
                        ans = ans - MOD;
                }

                memo[i][j] = ans;
            }
        }

        return memo[n - 1][n - 1];
    }

    public static int solve(char[][] grid) {
        if (grid[0][0] == '*' || grid[n - 1][n - 1] == '*')
            return 0;

        // return rec(n - 1, n - 1, grid);

        int[][] memo = new int[n][n];
        // for (int[] arr : memo)
        // Arrays.fill(arr, -1);

        // return (int) memorization(n - 1, n - 1, grid, memo);

        return tabulation(grid, memo);
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
