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
            int sign = 1, c;
            int val = 0;

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

    public static int MAX = (int) 1e9;
    public static int[] dp;

    public static int rec(int lev) {
        if (lev == 0)
            return 0;

        if (dp[lev] != -1)
            return dp[lev];

        String s = String.valueOf(lev);

        int ans = MAX;
        for (int i = 0; i < s.length(); i++) {
            int val = s.charAt(i) - '0';
            if (0 < val)
                ans = Math.min(ans, rec(lev - val) + 1);
        }

        return dp[lev] = ans;
    }

    public static int iter(int n) {
        for (int lev = 1; lev <= n; lev++) {

            String s = String.valueOf(lev);

            int ans = MAX;
            for (int i = 0; i < s.length(); i++) {
                int val = s.charAt(i) - '0';

                if (0 < val)
                    ans = Math.min(ans, dp[lev - val] + 1);
            }

            dp[lev] = ans;
        }

        return dp[n];
    }

    public static int solve(int n) {
        dp = new int[n + 1];
        // Arrays.fill(dp, -1);
        // return rec(n);

        return iter(n);
    }

    public static void main(String[] args) throws Exception {
        UltraFastReader in = new UltraFastReader();

        int n = in.nextInt();

        System.out.println(solve(n));
    }
}