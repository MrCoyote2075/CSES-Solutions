
// import java.io.BufferedReader;
import java.io.InputStream;
import java.io.PrintWriter;
// import java.util.StringTokenizer;

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
                val = val * 10 + (c - '0');
                c = read();
            }

            return val * sign;
        }
    }

    // public static class FastReader {
    // public BufferedReader br;
    // public StringTokenizer st;

    // public FastReader() {
    // br = new BufferedReader(new InputStreamReader(System.in));
    // }

    // public String next() throws Exception {
    // while (st == null || !st.hasMoreElements())
    // st = new StringTokenizer(br.readLine());

    // return st.nextToken();
    // }

    // public int nextInt() throws Exception {
    // return Integer.parseInt(next());
    // }
    // }

    public static long[] segArr;
    public static long[] lazyArr;

    public static long buildSegmentTree(int l, int r, int segInd, int[] arr) {
        if (l == r)
            return segArr[segInd] = arr[l];

        int mid = (l + r) / 2;

        segArr[segInd] = buildSegmentTree(l, mid, segInd * 2 + 1, arr)
                + buildSegmentTree(mid + 1, r, segInd * 2 + 2, arr);

        return segArr[segInd];
    }

    public static void applyLazy(int segInd, int len, long add) {
        segArr[segInd] += len * add;
        lazyArr[segInd] += add;
    }

    public static void pushDownLazy(int l, int r, int segInd) {
        if (lazyArr[segInd] == 0 || l == r)
            return;

        int mid = (l + r) / 2;

        applyLazy(segInd * 2 + 1, mid - l + 1, lazyArr[segInd]);
        applyLazy(segInd * 2 + 2, r - mid, lazyArr[segInd]);

        lazyArr[segInd] = 0;
    }

    public static long query(int l, int r, int segInd, int ql, int qr) {

        if (r < ql || qr < l)
            return 0;

        if (ql <= l && r <= qr)
            return segArr[segInd];

        pushDownLazy(l, r, segInd);
        int mid = (l + r) / 2;

        long left = query(l, mid, segInd * 2 + 1, ql, qr);
        long right = query(mid + 1, r, segInd * 2 + 2, ql, qr);

        return left + right;
    }

    public static void rangeUpdate(int l, int r, int segInd, int ql, int qr, long add) {

        if (r < ql || qr < l)
            return;

        if (ql <= l && r <= qr) {
            applyLazy(segInd, r - l + 1, add);
            return;
        }

        pushDownLazy(l, r, segInd);

        int mid = (l + r) / 2;

        rangeUpdate(l, mid, segInd * 2 + 1, ql, qr, add);
        rangeUpdate(mid + 1, r, segInd * 2 + 2, ql, qr, add);

        segArr[segInd] = segArr[segInd * 2 + 1] + segArr[segInd * 2 + 2];
    }

    // // Finqick Tree
    // public static long[] bit;

    // public static long finwickQuery(int ind) {
    // long val = 0;

    // while (0 < ind) {
    // val += bit[ind];
    // ind -= ind & -ind;
    // }

    // return val;
    // }

    // public static void finwickUpdate(int ind, long val, int n) {
    // while (ind <= n) {
    // bit[ind] += val;
    // ind += ind & -ind;
    // }
    // }

    public static void main(String[] args) throws Exception {
        // FastReader in = new FastReader();
        UltraFastReader in = new UltraFastReader();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int q = in.nextInt();

        int[] arr = new int[n];

        for (int i = 0; i < n; i++)
            arr[i] = in.nextInt();

        segArr = new long[n * 4];
        lazyArr = new long[n * 4];
        buildSegmentTree(0, n - 1, 0, arr);

        // bit = new long[n + 2];

        StringBuilder sb = new StringBuilder();
        while (q-- > 0) {
            int type = in.nextInt();

            if (type == 1) {
                int a = in.nextInt() - 1;
                int b = in.nextInt() - 1;
                int u = in.nextInt();

                // finwickUpdate(a, u, n);
                // finwickUpdate(b + 1, -u, n);
                rangeUpdate(0, n - 1, 0, a, b, u);

            } else {
                int k = in.nextInt() - 1;

                sb.append(query(0, n - 1, 0, k, k)).append('\n');
                // sb.append(arr[k - 1] + finwickQuery(k)).append('\n');
                // out.println(arr[k - 1] + finwickQuery(k));
            }
        }

        out.println(sb);

        out.flush();
    }
}