import java.io.InputStream;
import java.util.Arrays;

public class Main {
    public static class UltraFastReader {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

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

    public static int solve(int n, int x, int[] arr) {
        Arrays.sort(arr);

        int l = 0, r = n - 1;

        int ans = 0;
        while (l < r) {
            if (arr[l] + arr[r] <= x) {
                l++;
                r--;
            } else
                r--;

            ans++;
        }

        if (l == r)
            ans++;

        return ans;
    }

    public static void main(String[] args) throws Exception {
        UltraFastReader in = new UltraFastReader();
        int n = in.nextInt();
        int x = in.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++)
            arr[i] = in.nextInt();

        System.out.println(solve(n, x, arr));
    }
}