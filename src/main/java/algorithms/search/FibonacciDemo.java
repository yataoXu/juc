package algorithms.search;

import java.util.concurrent.CountDownLatch;

public class FibonacciDemo {

    public static int fibonacci(int n) {
        int result = 0;
        if (n < 0) {
            throw new RuntimeException("n should more than 0");
        }
        if (n <= 1) {
            result = 1;
            return result;
        }
        for (int i = 2; i <= n; i++) {
            result = fibonacci(i - 1) + fibonacci(i - 2);
        }
        return result;
    }


    public static void main(String[] args) {
            CountDownLatch latch = new CountDownLatch(100);
            for (int i = 0; i < 100; i++) {
                new Thread(() -> {
                    fibonacci(Integer.MAX_VALUE);
                }, i + "").start();
                latch.countDown();
            }

            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}
