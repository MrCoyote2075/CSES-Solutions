
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

    public static long[] bit;

    public static void update(int ind, int val, int n) {
        while (ind <= n) {
            bit[ind] += val;
            ind += ind & -ind;
        }
    }

    public static long query(int ind) {
        long val = 0;

        while (ind > 0) {
            val += bit[ind];
            ind -= ind & -ind;
        }

        return val;
    }

    public static void main(String[] args) throws Exception {
        UltraFastReader in = new UltraFastReader();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int q = in.nextInt();

        int[] arr = new int[n];
        bit = new long[n + 2];

        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
            update(i + 1, arr[i], n);
        }

        StringBuilder sb = new StringBuilder();
        while (q-- > 0) {
            int l = in.nextInt();
            int r = in.nextInt();

            sb.append(query(r) - query(l - 1)).append('\n');
        }

        out.println(sb);
        out.flush();
    }
}