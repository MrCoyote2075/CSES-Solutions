import java.io.InputStream;
import java.util.TreeMap;

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

    public static String solve(int n, int m, int[] ticket, int[] customer) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int tic : ticket)
            map.merge(tic, 1, Integer::sum);

        StringBuilder sb = new StringBuilder();
        for (int cus : customer) {
            Integer val = map.floorKey(cus);

            if (val == null)
                sb.append(-1);
            else {
                sb.append(val);
                map.computeIfPresent(val, (k, v) -> v == 1 ? null : v - 1);
            }

            sb.append('\n');
        }

        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        UltraFastReader in = new UltraFastReader();
        int n = in.nextInt();
        int m = in.nextInt();
        int[] ticket = new int[n];
        int[] customer = new int[m];

        for (int i = 0; i < n; i++)
            ticket[i] = in.nextInt();

        for (int i = 0; i < m; i++)
            customer[i] = in.nextInt();

        System.out.println(solve(n, m, ticket, customer));
    }
}