
import java.io.InputStream;
import java.io.PrintWriter;

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

        public String next() throws Exception {
            StringBuilder sb = new StringBuilder();
            int c;

            do
                c = read();
            while (c <= ' ');

            while (c > ' ') {
                sb.append((char) c);
                c = read();
            }

            return sb.toString();
        }

        public int nextInt() throws Exception {
            int c, sign = 1, val = 0;

            do
                c = read();
            while (c <= ' ');

            if (c == '-') {
                sign = -1;
                c = read();
            }

            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }

            return val * sign;
        }
    }

    public static void main(String[] args) throws Exception {
        UltraFastReader in = new UltraFastReader();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int q = in.nextInt();

        int[][] pref = new int[n + 2][n + 2];

        for (int i = 0; i < n; i++) {
            String s = in.next();
            for (int j = 0; j < n; j++) {
                int val = (s.charAt(j) == '*') ? 1 : 0;
                pref[i + 1][j + 1] = (pref[i + 1][j] + pref[i][j + 1] + val) - pref[i][j];
            }
        }

        StringBuilder sb = new StringBuilder();

        while (q-- > 0) {
            int row1 = in.nextInt();
            int col1 = in.nextInt();
            int row2 = in.nextInt();
            int col2 = in.nextInt();

            int ans = (pref[row2][col2] - (pref[row1 - 1][col2] + pref[row2][col1 - 1]))
                    + pref[row1 - 1][col1 - 1];

            sb.append(ans).append('\n');
        }

        out.println(sb);
        out.flush();
    }
}