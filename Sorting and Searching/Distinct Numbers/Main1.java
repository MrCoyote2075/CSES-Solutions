import java.util.*;

public class Main1 {
    public static int solve(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int num : arr)
            set.add(num);

        return set.size();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int arr[] = new int[n];

        for (int i = 0; i < n; i++)
            arr[i] = in.nextInt();

        System.out.print(solve(arr));

        in.close();
    }
}
