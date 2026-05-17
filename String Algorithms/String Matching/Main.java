
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

    // Method - 1 :
    public static int solve1(String text, String pat) {
        int ans = 0;
        int ind = 0;

        while ((ind = text.indexOf(pat, ind)) != -1) {
            ind++;
            ans++;
        }

        return ans;
    }

    // Method - 2 :
    public static int[] buildLPS(int m, String pat) {

        int[] lps = new int[m];

        int len = 0, i = 1;

        while (i < m) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;

            } else if (len == 0) {
                lps[i] = 0;
                i++;

            } else
                len = lps[len - 1];
        }

        return lps;
    }

    public static int solve(String text, String pat) {
        int n = text.length();
        int m = pat.length();

        int[] lps = buildLPS(m, pat);

        // Knuth-Morris-Pratt Algorithm...
        int ans = 0;
        int i = 0, len = 0;

        while (i < n) {
            if (text.charAt(i) == pat.charAt(len)) {
                len++;
                i++;

                if (len == m) {
                    ans++;
                    len = lps[len - 1];
                }

            } else if (len == 0)
                i++;

            else
                len = lps[len - 1];
        }

        return ans;
    }

    public static void main(String[] args) throws Exception {
        UltraFastReader in = new UltraFastReader();

        String str = in.next();
        String pat = in.next();

        System.out.println(solve(str, pat));
    }

}
