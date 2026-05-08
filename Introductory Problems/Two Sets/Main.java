
import java.io.InputStream;
import java.util.ArrayDeque;

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

    public static ArrayDeque<Integer> tk = new ArrayDeque<>();
    public static ArrayDeque<Integer> ntk = new ArrayDeque<>();

    public static boolean rec(int lev, int sum, int len, int target) {

        if (lev > n)
            return (sum == target);

        tk.offerLast(lev);
        if (rec(lev + 1, sum + lev, len + 1, target))
            return true;
        tk.pollLast();

        ntk.offerLast(lev);
        if (rec(lev + 1, sum, len, target))
            return true;
        ntk.pollLast();

        return false;
    }

    public static String oldSolve() {
        int sum = (n * (n + 1)) / 2;

        if ((sum & 1) == 1)
            return "NO";

        if (rec(1, 0, 0, sum / 2)) {
            StringBuilder sb = new StringBuilder();

            sb.append("YES\n").append(tk.size()).append("\n");

            for (int num : tk)
                sb.append(num).append(" ");

            sb.append("\n").append(ntk.size()).append("\n");

            for (int num : ntk)
                sb.append(num).append(" ");

            return sb.toString();
        }

        return "NO";
    }

    public static String solve() {
        int sum = (n * (n + 1)) / 2;

        if ((sum & 1) == 1)
            return "NO";

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        if ((n & 1) == 0) {
            int i = 1;
            while (i <= n) {
                if (i <= n)
                    sb1.append(i++).append(" ");

                if (i <= n)
                    sb2.append(i++).append(" ");

                if (i <= n)
                    sb2.append(i++).append(" ");

                if (i <= n)
                    sb1.append(i++).append(" ");
            }

        } else {
            int i = 1;
            while (i <= n) {
                if (i <= n)
                    sb1.append(i++).append(" ");

                if (i <= n)
                    sb1.append(i++).append(" ");

                if (i <= n)
                    sb2.append(i++).append(" ");

                if (i <= n)
                    sb2.append(i++).append(" ");
            }
        }

        StringBuilder ans = new StringBuilder();

        ans.append("YES\n").append((int) Math.ceil(n / 2.0)).append('\n').append(sb1).append('\n').append(n / 2)
                .append('\n')
                .append(sb2);

        return ans.toString();
    }

    public static void main(String[] args) throws Exception {
        UltraFastReader in = new UltraFastReader();

        n = in.nextInt();

        System.out.println(solve());
    }

}

/*
 * ////
 * n : 3
 * 1 + 2 = 3
 * 3 = 3
 * 
 * n : 4
 * 1 + 4 = 5
 * 2 + 3 = 5
 * 
 * n : 7
 * 1 + 2 + 5 + 6 = 14
 * 3 + 4 + 7 = 14
 * 
 * n : 8
 * 1 + 4 + 5 + 8 = 18
 * 2 + 3 + 6 + 7 = 18
 * 
 * n : 11
 * 1 + 2 + 5 + 6 + 9 + 10 = 33
 * 3 + 4 + 7 + 8 + 11 = 33
 * 
 * n : 12
 * 1 + 4 + 5 + 8 + 9 + 12 = 39
 * 2 + 3 + 6 + 7 + 10 + 11 = 39
 * 
 */