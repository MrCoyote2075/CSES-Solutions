
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

    public static int[] segArr;

    public static int buildSegement(int l, int r, int segInd, int[] arr) {
        if (l == r)
            return segArr[segInd] = arr[l];

        int mid = (r + l) / 2;

        return segArr[segInd] = Math.min(buildSegement(l, mid, segInd * 2 + 1, arr),
                buildSegement(mid + 1, r, segInd * 2 + 2, arr));
    }

    public static int query(int l, int r, int segInd, int ql, int qr) {

        if (r < ql || qr < l)
            return (int) 1e9;

        if (ql <= l && r <= qr)
            return segArr[segInd];

        int mid = (l + r) / 2;

        int left = query(l, mid, segInd * 2 + 1, ql, qr);
        int right = query(mid + 1, r, segInd * 2 + 2, ql, qr);

        return Math.min(left, right);
    }

    public static void main(String[] args) throws Exception {
        FastReader in = new FastReader();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int q = in.nextInt();

        int[] arr = new int[n];
        segArr = new int[n * 4];

        for (int i = 0; i < n; i++)
            arr[i] = in.nextInt();

        buildSegement(0, n - 1, 0, arr);

        StringBuilder sb = new StringBuilder();
        while (q-- > 0) {
            int l = in.nextInt() - 1;
            int r = in.nextInt() - 1;

            sb.append(query(0, n - 1, 0, l, r)).append('\n');
        }

        out.println(sb);
        out.flush();
    }
}