
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

    public static long solve(int k, long x, long a, long b, long c) {
        long[] arr = new long[n + 1];

        arr[1] = x;
        for (int i = 2; i <= n; i++) {
            arr[i] = ((a * arr[i - 1]) + b);

            if (c <= arr[i])
                arr[i] %= c;

        }

        long sum = 0;
        long xor = 0;

        for (int l = 1, r = 1; r <= n; r++) {
            sum += arr[r];

            if (k == r - l + 1) {
                xor ^= sum;
                sum -= arr[l++];
            }
        }

        return xor;
    }

    public static void main(String[] args) throws Exception {
        UltraFastReader in = new UltraFastReader();

        n = in.nextInt();
        int k = in.nextInt();
        int x = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();

        System.out.println(solve(k, x, a, b, c));
    }

}
