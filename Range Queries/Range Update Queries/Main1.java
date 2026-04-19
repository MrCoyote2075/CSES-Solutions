
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
                val = (val * 10) + (c - '0');
                c = read();
            }

            return val * sign;
        }
    }

    public static int[] arr;
    public static long[] segArr;
    public static long[] lazy;

    public static long buildSegmentTree(int l, int r, int segInd) {
        if (l == r)
            return segArr[segInd] = arr[l];

        int mid = (l + r) / 2;

        return segArr[segInd] = buildSegmentTree(l, mid, segInd * 2 + 1) +
                buildSegmentTree(mid + 1, r, segInd * 2 + 2);
    }

    public static void update(int l, int r, int segInd, int ql, int qr, long val) {
        if (r < ql || qr < l)
            return;

        if (ql <= l && r <= qr) {
            applyLazy(segInd, r - l + 1, val);
            return;
        }

        relaxLazy(l, r, segInd);
        int mid = (l + r) / 2;

        update(l, mid, segInd * 2 + 1, ql, qr, val);
        update(mid + 1, r, segInd * 2 + 2, ql, qr, val);
    }

    public static long query(int l, int r, int segInd, int ind) {
        if (l == r)
            return segArr[segInd];

        relaxLazy(l, r, segInd);

        int mid = (l + r) / 2;

        if (ind <= mid)
            return query(l, mid, segInd * 2 + 1, ind);

        else
            return query(mid + 1, r, segInd * 2 + 2, ind);
    }

    public static void applyLazy(int segInd, int len, long val) {
        segArr[segInd] += val * len;
        lazy[segInd] += val;
    }

    public static void relaxLazy(int l, int r, int segInd) {
        if (lazy[segInd] == 0 || l == r)
            return;

        int mid = (l + r) / 2;

        applyLazy(segInd * 2 + 1, mid - l + 1, lazy[segInd]);
        applyLazy(segInd * 2 + 2, r - mid, lazy[segInd]);

        lazy[segInd] = 0;
    }

    public static void main(String[] args) throws Exception {
        UltraFastReader in = new UltraFastReader();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int q = in.nextInt();

        arr = new int[n];
        segArr = new long[n * 4];
        lazy = new long[n * 4];

        for (int i = 0; i < n; i++)
            arr[i] = in.nextInt();

        buildSegmentTree(0, n - 1, 0);

        StringBuilder sb = new StringBuilder();
        while (q-- > 0) {
            int type = in.nextInt();

            if (type == 1) {
                int a = in.nextInt() - 1;
                int b = in.nextInt() - 1;
                int u = in.nextInt();

                update(0, n - 1, 0, a, b, u);
            } else {
                int k = in.nextInt() - 1;

                sb.append(query(0, n - 1, 0, k)).append('\n');
            }
        }

        out.print(sb);
        out.flush();
    }
}