package org.wuzl.test.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

public class TestForkJoin {
    public static void main(String[] args) throws InterruptedException {
        int parallelism = Runtime.getRuntime().availableProcessors();
        System.out.println("parallelism:" + parallelism);
        ForkJoinPool fjp = new ForkJoinPool(parallelism);
        Stopwatch stopwatch = Stopwatch.createStarted();
        int result = fjp.invoke(new SumNumTask(0, 10000000));
        System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS));
        System.out.println("result:" + result);
    }

    static class SumNumTask extends RecursiveTask<Integer> {
        static final int max = 10;
        final int start;
        final int end;

        public SumNumTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            if (end - start > max) {
                // 分解任务
                int mid = (start + end) / 2;
                SumNumTask left = new SumNumTask(start, mid);
                SumNumTask right = new SumNumTask(mid + 1, end);
                // left.fork();
                // right.fork();
                 super.invokeAll(left, right);// 比分别fork快
                int leftSum = left.join();
                int rightSum = right.join();
                return leftSum + rightSum;
            }
            return calcSum();
        }

        private int calcSum() {
            int sum = 0;
            for (int i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        }
    }
}
