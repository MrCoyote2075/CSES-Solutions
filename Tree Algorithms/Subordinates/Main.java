
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

    static int dfs(int src, ArrayList<ArrayList<Integer>> grp, int[] result) {
        int ans = 0;
        for (int des : grp.get(src))
            ans += dfs(des, grp, result) + 1;

        return result[src - 1] = ans;
    }

    public static int[] solve(int n, int[] arr) {
        ArrayList<ArrayList<Integer>> grp = new ArrayList<>();
        for (int i = 0; i <= n; i++)
            grp.add(new ArrayList<>());

        for (int i = 0; i < arr.length; i++)
            grp.get(arr[i]).add(i + 2);

        int[] result = new int[n];
        dfs(1, grp, result);

        return result;
    }

    public static void main(String[] args) throws Exception {
        FileReader fs = new FileReader();
        PrintWriter out = new PrintWriter(System.out);

        int n = fs.nextInt();

        int arr[] = new int[n - 1];
        for (int i = 0; i < n - 1; i++)
            arr[i] = fs.nextInt();

        StringBuilder sb = new StringBuilder();
        for (int num : solve(n, arr)) {
            sb.append(num);
            sb.append(" ");
        }

        out.print(sb.toString());

        out.flush();
        out.close();
    }
}
