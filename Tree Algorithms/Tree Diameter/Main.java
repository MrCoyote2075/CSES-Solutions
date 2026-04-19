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

    public static int n;
    public static int ans;
    public static ArrayList<ArrayList<Integer>> grp;

    public static int rec(int u, int par) {

        int max1 = 0;
        int max2 = 0;

        for (int v : grp.get(u)) {
            if (v != par) {

                int val = rec(v, u) + 1;
                if (max1 < val) {
                    max2 = max1;
                    max1 = val;

                } else if (max2 < val)
                    max2 = val;
            }
        }

        ans = Math.max(ans, max1 + max2);

        return max1;
    }

    public static int[] BFS(int src) {
        int[] dist = new int[n + 1];
        boolean[] vis = new boolean[n + 1];

        ArrayDeque<Integer> Q = new ArrayDeque<>();

        Q.offer(src);
        vis[src] = true;

        int maxDist = src;

        while (!Q.isEmpty()) {
            int u = Q.poll();

            for (int v : grp.get(u)) {

                if (!vis[v]) {
                    dist[v] = dist[u] + 1;
                    vis[v] = true;

                    Q.offer(v);

                    if (dist[maxDist] < dist[v])
                        maxDist = v;
                }
            }
        }

        return new int[] { maxDist, dist[maxDist] };
    }

    public static void main(String[] args) throws IOException {
        try (PrintWriter out = new PrintWriter(System.out)) {
            FastReader in = new FastReader();
            // Scanner in = new Scanner(System.in);

            n = in.nextInt();

            grp = new ArrayList<>();
            for (int i = 0; i <= n; i++)
                grp.add(new ArrayList<>());

            for (int i = 1; i < n; i++) {
                int u = in.nextInt();
                int v = in.nextInt();

                grp.get(u).add(v);
                grp.get(v).add(u);
            }

            // ans = 0;
            // rec(1, 0);
            // out.println(ans);

            // A is furthest leaf node from 1...
            int[] A = BFS(1);

            // BFS From A Will Give the Longest Diameter in the tree, that gives B
            int[] B = BFS(A[0]);

            out.println(B[1]);
            out.flush();
        }
    }
}