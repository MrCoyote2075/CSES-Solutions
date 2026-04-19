import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<List<Integer>> g = new ArrayList<>();
        for (int i = 0; i <= n; i++)
            g.add(new ArrayList<>());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 2; i <= n; i++) {
            int p = Integer.parseInt(st.nextToken());
            g.get(p).add(i);
        }

        int[] sub = new int[n + 1];
        int[] parent = new int[n + 1];
        int[] order = new int[n];
        int idx = 0;

        // BFS
        Queue<Integer> q = new ArrayDeque<>();
        q.add(1);

        while (!q.isEmpty()) {
            int u = q.poll();
            order[idx++] = u;
            for (int v : g.get(u)) {
                parent[v] = u;
                q.add(v);
            }
        }

        // process from leaves to root
        for (int i = n - 1; i > 0; i--) {
            int u = order[i];
            sub[parent[u]] += 1 + sub[u];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++)
            sb.append(sub[i]).append(" ");
            
        System.out.print(sb);
    }
}
