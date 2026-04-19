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

    public static int solve(int n, int m, int k, int[] desAprtSizeArr, int[] aprtSizeArr) {
        Arrays.sort(desAprtSizeArr);
        Arrays.sort(aprtSizeArr);

        int i = 0, j = 0, ans = 0;
        while (i < n && j < m) {
            int need = desAprtSizeArr[i];
            int have = aprtSizeArr[j];

            if (need - k <= have && have <= need + k) {
                ans++;
                j++;
                i++;
            } else if (need - k <= have) {
                i++;
            } else {
                j++;
            }
        }
        return ans;
    }

    public static void main(String[] args) throws Exception {
        FileReader fs = new FileReader();
        PrintWriter out = new PrintWriter(System.out);

        int n = fs.nextInt();
        int m = fs.nextInt();
        int k = fs.nextInt();

        int desAprtSizeArr[] = new int[n];
        for (int i = 0; i < n; i++)
            desAprtSizeArr[i] = fs.nextInt();

        int aprtSizeArr[] = new int[m];
        for (int i = 0; i < m; i++)
            aprtSizeArr[i] = fs.nextInt();

        out.print(solve(n, m, k, desAprtSizeArr, aprtSizeArr));

        out.flush();
        out.close();
    }
}
