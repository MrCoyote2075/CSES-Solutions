import java.io.*;
import java.util.*;

public class Main {
    static class FileReader {
        BufferedReader br;
        StringTokenizer st;

        public FileReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());

            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

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

    public static int solve(int n, int m, int k, int[] desAprtSizeArr, int[] aprtSizeArr) {
        Arrays.sort(desAprtSizeArr);
        Arrays.sort(aprtSizeArr);

        int i = 0, j = 0, ans = 0;
        while (i < n && j < m) {
            int need = desAprtSizeArr[i];
            int have = aprtSizeArr[j];

            if (need - k <= have && have <= need + k) {
                ans++;
                j++;
                i++;
            } else if (need - k <= have) {
                i++;
            } else {
                j++;
            }
        }
        return ans;
    }

    public static void main(String[] args) throws Exception {
        // FileReader fs = new FileReader();
        UltraFastReader fs = new UltraFastReader();
        PrintWriter out = new PrintWriter(System.out);

        int n = fs.nextInt();
        int m = fs.nextInt();
        int k = fs.nextInt();

        int desAprtSizeArr[] = new int[n];
        for (int i = 0; i < n; i++)
            desAprtSizeArr[i] = fs.nextInt();

        int aprtSizeArr[] = new int[m];
        for (int i = 0; i < m; i++)
            aprtSizeArr[i] = fs.nextInt();

        out.print(solve(n, m, k, desAprtSizeArr, aprtSizeArr));

        out.flush();
        out.close();
    }
}
