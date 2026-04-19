import java.io.InputStream;
import java.io.PrintWriter;

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

        public String next() throws Exception {
            StringBuilder sb = new StringBuilder();
            int c;

            do
                c = read();
            while (c <= ' ');

            while (c > ' ') {
                sb.append((char) c);
                c = read();
            }

            return sb.toString();
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

    public static int[] segArr;
    public static int[] arr;

    public static void buildSegmentTree(int l, int r, int segInd) {
        if (l == r) {
            segArr[segInd] = l;
            return;
        }

        int mid = (l + r) / 2;

        buildSegmentTree(l, mid, segInd * 2 + 1);
        buildSegmentTree(mid + 1, r, segInd * 2 + 2);

        if (arr[segArr[segInd * 2 + 1]] >= arr[segArr[segInd * 2 + 2]])
            segArr[segInd] = segArr[segInd * 2 + 1];

        else
            segArr[segInd] = segArr[segInd * 2 + 2];

    }

    public static int query(int l, int r, int segInd, int val) {

        if (l == r)
            return segArr[segInd];

        int mid = (l + r) / 2;

        if (arr[segArr[segInd * 2 + 1]] >= val)
            return query(l, mid, segInd * 2 + 1, val);

        else
            return query(mid + 1, r, segInd * 2 + 2, val);
    }

    public static void update(int l, int r, int segInd, int ind) {
        if (l == r)
            return;

        int mid = (l + r) / 2;

        if (ind <= mid)
            update(l, mid, segInd * 2 + 1, ind);
        else
            update(mid + 1, r, segInd * 2 + 2, ind);

        if (arr[segArr[segInd * 2 + 1]] >= arr[segArr[segInd * 2 + 2]])
            segArr[segInd] = segArr[segInd * 2 + 1];

        else
            segArr[segInd] = segArr[segInd * 2 + 2];
    }

    public static void main(String[] args) throws Exception {
        UltraFastReader in = new UltraFastReader();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int m = in.nextInt();

        arr = new int[n];
        segArr = new int[n * 4];

        for (int i = 0; i < n; i++)
            arr[i] = in.nextInt();

        buildSegmentTree(0, n - 1, 0);

        StringBuilder sb = new StringBuilder();
        while (m-- > 0) {

            int ind = 0;
            int groupSize = in.nextInt();

            if (groupSize <= arr[segArr[0]]) {
                ind = query(0, n - 1, 0, groupSize);
                arr[ind] -= groupSize;
                update(0, n - 1, 0, ind);
                ind++;
            }
            sb.append(ind).append(' ');
        }

        out.print(sb);
        out.flush();
    }
}