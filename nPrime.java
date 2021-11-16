package Other;

public class nPrime {

    /**
     * Checks whether n is prime by dividing it by all numbers up to the square root of n.
     * Room for improvement: divide by only other primes up to the square root of n through recursive logic?
     * @param n - checks whether n is prime.
     * @return
     */
    public boolean isPrime(int n){
        for (int i = 2; i <= Math.sqrt(n); i++)
            if (n % i == 0)
                return false;
        return true;
    }

    /**
     * Takes an integer argument (n) and generates a list n-long of prime numbers.
     * @param n - the array of primes should be this long.
     * @return int array containing n prime numbers.
     */
    public int[] primesArray(int n){
        nPrime a = new nPrime();

        int[] nums = new int[n];
        int num = 2;
        int i = 0;

        while (nums[n-1] == 0){
            if (a.isPrime(num)) {
                nums[i] = num;
                i++;
            }
            num++;
        }
        return nums;
    }

    public static void main(String[] args) {

        nPrime a = new nPrime();
        int[] tester = a.primesArray(50);

        for (int i : tester){
            System.out.println(i);
        }
    }


}
