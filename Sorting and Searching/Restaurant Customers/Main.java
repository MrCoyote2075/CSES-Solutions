
import java.io.InputStream;
import java.util.ArrayList;

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

    public static int solve(int[] inTime, int[] outTime) {
        ArrayList<int[]> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            arr.add(new int[] { inTime[i], 1 });
            arr.add(new int[] { outTime[i], -1 });
        }

        arr.sort((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        int ans = 0, cur = 0;
        for (int i = 0; i < arr.size(); i++) {
            cur += arr.get(i)[1];
            ans = Math.max(cur, ans);
        }

        return ans;
    }

    public static void main(String[] args) throws Exception {
        UltraFastReader in = new UltraFastReader();

        n = in.nextInt();

        int[] inTime = new int[n];
        int[] outTime = new int[n];

        for (int i = 0; i < n; i++) {
            inTime[i] = in.nextInt();
            outTime[i] = in.nextInt();
        }

        System.out.println(solve(inTime, outTime));
    }

}
