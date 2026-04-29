
import java.io.InputStream;

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
    public static int MAX = (int) 1e9;
    public static int MIN = (int) -1e9;
    public static long MOD = (long) 1e9 + 7;

    public static long rec(int lev) {
        if (lev == 0) // Base Condition...
            return 1;

        long ans = 0;

        for (int i = 1; i <= 6; i++)
            if (0 <= lev - i)
                ans = (ans + rec(lev - i)) % MOD;

        return ans;
    }

    public static long memorization(int lev, long[] memo) {
        if (lev == 0) // Base Condition...
            return 1;

        if (memo[lev] != -1)
            return memo[lev];

        long ans = 0;

        for (int i = 1; i <= 6; i++)
            if (0 <= lev - i)
                ans = (ans + memorization(lev - i, memo)) % MOD;

        return memo[lev] = ans;
    }

    public static long tabulation(long[] dp) {

        dp[0] = 1; // Base Condition...

        for (int lev = 0; lev <= n; lev++) { // final as 0 -> if (lev == 0) :=> Initial as n -> rec (n)
            long ans = 0;

            for (int i = 1; i <= 6; i++)
                if (0 <= lev - i)
                    ans = (ans + dp[lev - i]) % MOD;

            dp[lev] += ans;
        }

        return dp[n];
    }

    public static long solve() {
        // return rec(n);

        long[] memo = new long[n + 1];
        // Arrays.fill(memo, -1);

        // return memorization(n, memo);
        return tabulation(memo);
    }

    public static void main(String[] args) throws Exception {
        UltraFastReader in = new UltraFastReader();
        n = in.nextInt();

        System.out.println(solve());
    }

}
