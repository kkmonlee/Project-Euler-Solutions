/**
 * Created by hampe on 26 October 2016.
 */
public class p187 {
    public static void main(String[] args) {
        long start = System.nanoTime();

        int limit = (int) Math.pow(10, 8);
        int result = 1;
        int sieve_bound = (limit - 1) / 2;
        boolean[] sieve = new boolean[sieve_bound];
        int cross_limit = ((int) (Math.sqrt(limit)) - 1) / 2;
        sieve[0] = true;

        for (int i = 1; i <= cross_limit; i++) {
            if (!sieve[i]) {
                for (int j = (2 * i * (i + 1)); j < sieve_bound; j += (2 * i + 1)) {
                    sieve[j] = true;
                }
            }
        }

        boolean outer = true;
        boolean inner;
        int p, q, j, i;
        int temp;

        for (i = 0; outer; i++) {
            if (!sieve[i]) {
                temp = 4 * i + 2;

                if (temp < limit) {
                    result++;
                } else {
                    outer = false;
                }
            }
        }

        outer = true;

        for (i = 0; outer; i++) {
            if (!sieve[i]) {
                p = 2 * i + 1;

                if ((p * p) < limit) {
                    temp = 0;
                    inner = true;

                    for (j = i; inner; j++) {
                        if (!sieve[j]) {
                            q = 2 * j + 1;
                            temp = p * q;

                            if (temp < limit) {
                                result++;
                            } else {
                                inner = false;
                            }
                        }
                    }
                } else {
                    outer = false;
                }
            }
        }

        long end = System.nanoTime();
        long runtime = end - start;
        System.out.println(result);
        System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
                + "ns)");
    }
}