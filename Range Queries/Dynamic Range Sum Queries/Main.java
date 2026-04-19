
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {
    public static class FastReader {
        public BufferedReader br;
        public StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() throws Exception {
            while (st == null || !st.hasMoreElements())
                st = new StringTokenizer(br.readLine());

            return st.nextToken();
        }

        public int nextInt() throws Exception {
            return Integer.parseInt(next());
        }
    }

    public static long[] segArr;

    public static long buildSegmentTree(int l, int r, int segInd, int[] arr) {
        if (l == r)
            return segArr[segInd] = arr[l];

        int mid = (l + r) / 2;

        return segArr[segInd] = buildSegmentTree(l, mid, segInd * 2 + 1, arr)
                + buildSegmentTree(mid + 1, r, segInd * 2 + 2, arr);
    }

    public static long query(int l, int r, int segInd, int ql, int qr) {
        if (r < ql || qr < l)
            return 0;

        if (ql <= l && r <= qr)
            return segArr[segInd];

        int mid = (l + r) / 2;

        long left = query(l, mid, segInd * 2 + 1, ql, qr);
        long right = query(mid + 1, r, segInd * 2 + 2, ql, qr);

        return left + right;
    }

    public static void update(int l, int r, int segInd, int ind, long val) {

        if (l == r) {
            segArr[segInd] = val;
            return;
        }

        int mid = (l + r) / 2;

        if (ind <= mid)
            update(l, mid, segInd * 2 + 1, ind, val);
        else
            update(mid + 1, r, segInd * 2 + 2, ind, val);

        segArr[segInd] = segArr[segInd * 2 + 1] + segArr[segInd * 2 + 2];
    }

    public static void main(String[] args) throws Exception {
        FastReader in = new FastReader();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int q = in.nextInt();

        int[] arr = new int[n];
        segArr = new long[n * 4];

        for (int i = 0; i < n; i++)
            arr[i] = in.nextInt();

        buildSegmentTree(0, n - 1, 0, arr);

        StringBuilder sb = new StringBuilder();
        while (q-- > 0) {
            int type = in.nextInt();
            int a = in.nextInt();
            int b = in.nextInt();

            if (type == 1)
                update(0, n - 1, 0, a - 1, b);

            else
                sb.append(query(0, n - 1, 0, a - 1, b - 1)).append('\n');
        }

        out.print(sb);

        out.flush();
    }
}