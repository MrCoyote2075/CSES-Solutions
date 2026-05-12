
import java.io.InputStream;
import java.util.HashSet;

public class Main1 {
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
    public static HashSet<String> dict;
    public static int MAX = (int) 1e9;
    public static int MIN = (int) -1e9;
    public static long MOD = (long) 1e9 + 7;

    public static long rec(int lev, String s) {

        if (lev == n)
            return 1;

        long ans = 0;

        StringBuilder sb = new StringBuilder();

        for (int j = lev; j < n; j++) {
            sb.append(s.charAt(j));

            if (dict.contains(sb.toString()))
                ans = (ans + rec(j + 1, s)) % MOD;

        }

        return ans;
    }

    public static long memorization(int lev, String s, long[] memo) {

        if (lev == n)
            return 1;

        if (memo[lev] != -1)
            return memo[lev];

        long ans = 0;

        StringBuilder sb = new StringBuilder();

        for (int j = lev; j < n; j++) {
            sb.append(s.charAt(j));

            if (dict.contains(sb.toString()))
                ans = (ans + memorization(j + 1, s, memo)) % MOD;

        }

        return memo[lev] = ans;
    }

    public static int tabulation(String s) {
        long[] memo = new long[n + 1];

        for (int lev = n; 0 <= lev; lev--) {
            if (lev == n) {
                memo[n] = 1;
                continue;
            }

            long ans = 0;

            StringBuilder sb = new StringBuilder();

            for (int j = lev; j < n; j++) {
                sb.append(s.charAt(j));

                if (dict.contains(sb.toString()))
                    ans = (ans + memo[j + 1]) % MOD;
            }

            memo[lev] = ans;
        }

        return (int) memo[0];
    }

    public static int solve(String s) {

        //// 1 :
        // return (int) rec(0, s);

        //// 2 :
        // long[] memo = new long[n];
        // Arrays.fill(memo, -1);

        // return (int) memorization(0, s, memo);

        // 3 :
        return tabulation(s);
    }

    public static void main(String[] args) throws Exception {
        UltraFastReader in = new UltraFastReader();

        String s = in.next();
        n = in.nextInt();

        dict = new HashSet<>();

        for (int i = 0; i < n; i++)
            dict.add(in.next());

        n = s.length();

        System.out.println(solve(s));
    }

}
