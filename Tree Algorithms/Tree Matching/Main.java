
import java.io.*;
import java.util.*;

public class Main {
    public static class FastReader {
        public BufferedReader br;
        public StringTokenizer st;

        public FastReader() {
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

    public static int N;
    public static ArrayList<ArrayList<Integer>> grp;
    public static int[][] dp;
    public static boolean[][] vis;

    public static int rec(int u, int par, int taken) {

        if (vis[u][taken])
            return dp[u][taken];

        int sum = 0;
        for (int v : grp.get(u))
            if (v != par)
                sum += rec(v, u, 0);

        int ans = sum;

        if (taken == 0) {
            for (int v : grp.get(u))
                if (v != par)
                    ans = Math.max(ans, (sum - rec(v, u, 0)) + (rec(v, u, 1) + 1));
        }

        vis[u][taken] = true;
        return dp[u][taken] = ans;
    }

    public static void main(String[] args) throws IOException {
        FastReader in = new FastReader();
        try (PrintWriter out = new PrintWriter(System.out)) {
            N = in.nextInt();
            grp = new ArrayList<>();

            dp = new int[N + 1][2];
            vis = new boolean[N + 1][2];

            for (int i = 0; i <= N; i++)
                grp.add(new ArrayList<>());

            for (int i = 1; i < N; i++) {
                int u = in.nextInt();
                int v = in.nextInt();

                grp.get(u).add(v);
                grp.get(v).add(u);
            }

            out.println(rec(1, 0, 0));

            out.flush();
        }
    }
}

// 5 nodes
// Edges:
// 1 2
// 1 3
// 3 4
// 3 5

// 1
// / \
// 2 3
// / \
// 4 5