package FibFact;

import java.util.Arrays;
import java.util.Scanner;

public class FibFact {

   public static int fibonacci(int n) {
        double sqOf5 = Math.sqrt(5);

        double phi = (1 + sqOf5) / 2; //1.61803...
        double negPhi = 1 - phi; //-0.61803...

        double value = (Math.pow(goldenRatio, n) - Math.pow(negGoldenRatio, n)) / sqOf5;

        return (int) Math.floor(value + 0.5);
    }

    public static int[] fibArray(int min, int max) {
        int length = (max - min);
        int nums[] = new int[length];

        for (int i = 0; i < length; i++) {
            nums[i] = fibonacci(min - 1);
            min++;
        }

        return nums;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nEnter a range and receive the Fibonacci numbers in that range.\nMinimum:");
        int min = sc.nextInt();
        System.out.println("Maximum:");
        int max = sc.nextInt();

        int[] fibArray = fibArray(min, max);
        String result = Arrays.toString(fibArray);

        System.out.println(result);
    }
}
