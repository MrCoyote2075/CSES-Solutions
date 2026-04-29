
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

            if (sign == '-') {
                sign = -1;
                c = read();
            }

            while (c > ' ') {
                val = (val * 10) + (c - '0');
                c = read();
            }

            return sign * val;
        }
    }

    public static void main(String[] args) throws Exception {
        UltraFastReader in = new UltraFastReader();
        int n = in.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = in.nextInt();

        long totalMoves = 0;

        for (int i = 1; i < n; i++) {
            if (arr[i - 1] <= arr[i])
                continue;

            int move = (arr[i - 1] - arr[i]);
            totalMoves += move;
            arr[i] += move;

        }

        System.out.print(totalMoves);
    }
}

/*
 * @Question
 * 
 * You want to modify the array so that it is increasing,
 * i.e., every element is at least as large as the previous element.
 * On each move, you may increase the value of any element by one.
 * What is the minimum number of totalMoves required?
 * 
 * Example
 * Input:
 * 5
 * 3 2 5 1 7
 * 
 * Output:
 * 5
 * 
 */