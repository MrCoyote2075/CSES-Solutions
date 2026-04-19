import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // public static Long[] dp;

    // public static long rec(int n) {
    // if (n == 0)
    // return 1;

    // if (dp[n] != null)
    // return dp[n];

    // long ans = 0;
    // for (int i = 1; i <= 6; i++)
    // if (n - i >= 0)
    // ans += rec(n - i);

    // return dp[n] = ans % ((long) (1e9 + 7));
    // }

    public static int tab(int n) {

        int dp[] = new int[n + 1];
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            long ans = 0;
            for (int j = 1; j <= 6; j++) {
                if (i - j >= 0) {
                    ans += dp[i - j];
                }
            }
            dp[i] = (int) (ans % ((long) (1e9 + 7)));
        }

        return dp[n];
    }

    public static void solve(int n) {
        // dp = new Long[n + 1];
        System.out.print(tab(n));
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) {
        try {
            int n = nextInt();
            solve(n);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String next() throws Exception {
        while (st == null || !st.hasMoreElements()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }

    public static int nextInt() throws Exception {
        return Integer.parseInt(next());
    }
}