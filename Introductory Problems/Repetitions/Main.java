import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            String s = in.next();
            int max = 0;
            int count = 0;
            char prev = '$';

            for (char ch : s.toCharArray()) {
                if (prev != ch) {
                    max = Math.max(max, count);
                    prev = ch;
                    count = 0;
                }
                count++;
            }
            max = Math.max(max, count);

            System.out.print(max);
        }
    }
}

/*
 * @Question
 * a string consisting of characters A, C, G, and T.
 * Your task is to find the longest repetition in the sequence.
 * This is a maximum-length substring containing only one type of character.
 * 
 * Example
 * Input:
 * ATTCGGGA
 * 
 * Output:
 * 3
 */