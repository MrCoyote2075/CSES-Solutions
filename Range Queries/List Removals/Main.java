
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;

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
            int c, sign = 1, val = 0;

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

    public static int[] bits;

    public static void update(int ind, int val, int n) {
        while (ind <= n) {
            bits[ind] += val;
            ind += (ind & -ind);
        }
    }

    public static int query(int ind) {
        int val = 0;
        while (0 < ind) {
            val += bits[ind];
            ind -= (ind & -ind);
        }

        return val;
    }

    public static void main(String[] args) throws Exception {
        UltraFastReader in = new UltraFastReader();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int[] arr = new int[n + 1];
        bits = new int[n + 2];

        for (int i = 1; i <= n; i++) {
            arr[i] = in.nextInt();
            update(i, 1, n);
        }

        // System.out.println(Arrays.toString(arr));

        StringBuilder sb = new StringBuilder();

        int q = n;
        while (q-- > 0) {
            int pos = in.nextInt();

            int left = 1, right = n, ind = n;

            // System.out.println("\nbinary search : ");
            int mid = -1;
            while (left <= right) {
                mid = (left + right) / 2;

                // System.out.print(mid + " ");
                int key = query(mid);

                if (pos <= key) {
                    ind = mid;
                    right = mid - 1;
                } else
                    left = mid + 1;
            }
            System.out.println("\npos: " + pos + ", mid : " + mid);

            int val = arr[ind];
            arr[ind] = -1;

            update(ind, -1, n);

            System.out.println(Arrays.toString(arr));

            sb.append(val).append(' ');
        }

        out.print(sb);
        out.flush();
    }
}
