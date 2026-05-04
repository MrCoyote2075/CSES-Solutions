
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

    public static String solve() {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            long val = i * i;
            long ans = ((val * (val - 1)) / 2) - (4 * (i - 1) * (i - 2));
            sb.append(ans).append('\n');
        }

        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        UltraFastReader in = new UltraFastReader();
        n = in.nextInt();

        System.out.println(solve());
    }

}

// n! / ((n - r)! * r!)