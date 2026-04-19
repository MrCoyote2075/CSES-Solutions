
import java.io.*;
import java.util.*;

public class Template {
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

    public static int solve(int n, int[] arr) {

        return 0;
    }

    public static void main(String[] args) throws Exception {
        FileReader fs = new FileReader();
        PrintWriter out = new PrintWriter(System.out);

        int n = fs.nextInt();

        int arr[] = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = fs.nextInt();

        out.print(solve(n, arr));

        out.flush();
        out.close();
    }
}
