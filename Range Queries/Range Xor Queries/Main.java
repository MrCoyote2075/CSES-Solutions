
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

    public static void main(String[] args) throws Exception {
        FastReader in = new FastReader();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int q = in.nextInt();

        int[] arr = new int[n];

        for (int i = 0; i < n; i++)
            arr[i] = in.nextInt();

        int[] pref = new int[n + 1];

        for (int i = 0; i < n; i++)
            pref[i + 1] = pref[i] ^ arr[i];

        StringBuilder sb = new StringBuilder();

        while (q-- > 0) {
            int l = in.nextInt();
            int r = in.nextInt();

            sb.append(pref[r] ^ pref[l - 1]).append('\n');
        }

        out.println(sb);
        out.flush();
    }
}