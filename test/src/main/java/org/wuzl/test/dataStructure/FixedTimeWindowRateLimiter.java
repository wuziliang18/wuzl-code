package org.wuzl.test.dataStructure;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Stopwatch;

/**
 * This class implements fixed time window rate limiter algorithm. This class is thread-safe.
 */
public class FixedTimeWindowRateLimiter {

    /* timeout for {@code Lock.tryLock() }. */
    private static final long TRY_LOCK_TIMEOUT = 200L; // 200ms.

    private Stopwatch stopwatch;

    private AtomicInteger currentCount = new AtomicInteger(0);

    /* the max permitted access count per second */
    private final int limit;

    private Lock lock = new ReentrantLock();

    public FixedTimeWindowRateLimiter(int limit) {
        this(limit, Stopwatch.createStarted());
    }

    @VisibleForTesting
    protected FixedTimeWindowRateLimiter(int limit, Stopwatch stopwatch) {
        this.limit = limit;
        this.stopwatch = stopwatch;
    }

    /**
     * try to acquire an access token.
     * 
     * @return true if get an access token successfully, otherwise, return false.
     * @throws InternalErrorException if some internal error occurs.
     */
    public boolean tryAcquire() {
        int updatedCount = currentCount.incrementAndGet();
        if (updatedCount <= limit) {
            return true;
        }

        try {
            if (lock.tryLock(TRY_LOCK_TIMEOUT, TimeUnit.MILLISECONDS)) {
                try {
                    if (stopwatch.elapsed(TimeUnit.MILLISECONDS) > TimeUnit.SECONDS.toMillis(1)) {
                        currentCount.set(0);
                        stopwatch.reset();
                    }
                    updatedCount = currentCount.incrementAndGet();
                    return updatedCount <= limit;
                } finally {
                    lock.unlock();
                }
            } else {
                throw new RuntimeException("tryAcquire() wait lock too long:" + TRY_LOCK_TIMEOUT + "ms");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("tryAcquire() is interrupted by lock-time-out.", e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        FixedTimeWindowRateLimiter limiter = new FixedTimeWindowRateLimiter(10);
        System.out.println(LocalDateTime.now());
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            Thread.sleep(random.nextInt(50));
            System.out.println(LocalDateTime.now() + " : " + i + " result:" + limiter.tryAcquire());
        }
    }
}
