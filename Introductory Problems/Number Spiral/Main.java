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

        public long nextLong() throws Exception {
            int sign = 1, c;
            long val = 0;

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

    public static void main(String[] args) throws Exception {
        UltraFastReader in = new UltraFastReader();
        long t = in.nextLong();

        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            long row = in.nextLong();
            long col = in.nextLong();
            long ans;

            if (col <= row)
                ans = ((row & 1) == 0) ? ((row * row) + 1) - col
                        : ((row - 1) * (row - 1)) + col;
            else
                ans = ((col & 1) == 1) ? ((col * col) + 1) - row
                        : ((col - 1) * (col - 1)) + row;

            sb.append(ans).append('\n');
        }

        System.out.println(sb);
    }
}