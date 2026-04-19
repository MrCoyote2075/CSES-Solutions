import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            long n = in.nextLong();
            long sum = 0L;

            for (int i = 1; i < n; i++)
                sum += in.nextInt();

            long total = (n * (n + 1)) / 2;

            System.out.println(total - sum);
        }
    }
}

// 1 2 3 4 5
// #Question

// You are given all numbers between 1,2,...,n except one.
// Your task is to find the missing number.

// Input:
// 5
// 2 3 1 5

// Output:
// 4