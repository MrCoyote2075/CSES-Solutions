import java.util.Scanner;
// import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = in.nextInt();

        long moves = 0;

        for (int i = 1; i < n; i++) {
            if (arr[i - 1] > arr[i]) {
                int move = (arr[i - 1] - arr[i]);
                moves += move;
                arr[i] += move;
            }
        }

        System.out.print(moves);

        in.close();
    }
}

/*
 * @Question
 * 
 * You want to modify the array so that it is increasing,
 * i.e., every element is at least as large as the previous element.
 * On each move, you may increase the value of any element by one.
 * What is the minimum number of moves required?
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