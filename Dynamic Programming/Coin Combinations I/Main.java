
import java.io.InputStream;

public class Main {
    public static class UltraFastReader {
        public final InputStream in = System.in;
        public final byte[] buffer = new byte[1 << 16];
        public int ptr = 0, len = 0;

        public int read() throws Exception {
            if (ptr >= len) {
                ptr = 0;
                len = in.read(buffer);
                if (len <= 0)
                    return -1;
            }

            return buffer[ptr++];
        }

        public int nextInt() throws Exception {
            int sign = 1, val = 0, c;

            do
                c = read();
            while (c <= ' ');

            if (c == '-') {
                sign = -1;
                c = read();
            }

            while (c > ' ') {
                val = (val * 10) + (c - '0');
                c = read();
            }

            return val * sign;
        }
    }

    public static int n;
    public static long[] dp;
    public static long MOD = (long) 1e9 + 7;

    public static long rec(int sum, int[] arr) {
        if (sum == 0)
            return 1;

        if (dp[sum] != -1)
            return dp[sum];

        long ans = 0;

        for (int num : arr) {
            if (0 <= sum - num)
                ans += rec(sum - num, arr);
        }

        return dp[sum] = ans % MOD;
    }

    public static int iter(int target, int[] arr) {
        dp[0] = 1;

        for (int sum = 0; sum <= target; sum++) {
            long ans = 0;

            for (int num : arr) {
                if (0 <= sum - num)
                    ans += dp[sum - num];
            }

            dp[sum] += ans % MOD;
        }

        return (int) dp[target];
    }

    public static int solve(int[] arr, int x) {
        dp = new long[x + 1];
        // Arrays.fill(dp, -1);
        // return (int) rec(x, arr);

        return iter(x, arr);
    }

    public static void main(String[] args) throws Exception {
        UltraFastReader in = new UltraFastReader();
        n = in.nextInt();
        int x = in.nextInt();
        int arr[] = new int[n];

        // HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();

            // int val = in.nextInt();
            // if (set.add(val))
            // arr[i] = val;
        }

        System.out.println(solve(arr, x));
    }

}
