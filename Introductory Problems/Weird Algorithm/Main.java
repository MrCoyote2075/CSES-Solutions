import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Long num = in.nextLong();

        while (true) {
            System.out.print(num + " ");
            if (num == 1)
                break;
            if ((num & 1) == 0) {
                num /= 2;
            } else {
                num = (num * 3) + 1;
            }
        }
        in.close();
    }
}

// #Question :

// If n is even, the algorithm divides it by two, and
// if n is odd, the algorithm multiplies it by three and adds one.

// Then : The algorithm repeats this, until n is one.

// Example
// Input:

// 3
// Output:

// 3 10 5 16 8 4 2 1