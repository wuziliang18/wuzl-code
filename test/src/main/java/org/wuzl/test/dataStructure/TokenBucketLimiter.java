package org.wuzl.test.dataStructure;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * 令牌桶
 * 
 * @author ziliang.wu
 *
 */
public class TokenBucketLimiter {
    private final int permitsPerSecond;// 每秒许可数
    private final int maxTokenCount;// 最大令牌数
    private final long millisPerAdd;// 每次添加时间
    private int currentTokenCount;
    private long lastTime = System.currentTimeMillis();

    public TokenBucketLimiter(int permitsPerSecond) {
        this(permitsPerSecond, permitsPerSecond);
    }

    public TokenBucketLimiter(int permitsPerSecond, int maxTokenCount) {
        this.permitsPerSecond = permitsPerSecond;
        this.maxTokenCount = maxTokenCount;
        this.millisPerAdd = 1000 / permitsPerSecond;
    }

    public boolean acquire(int num) {// 暂时先做不支持预消费的
        addToken();
        if (currentTokenCount >= num) {
            currentTokenCount -= num;
            return true;
        }
        return false;
    }

    private void addToken() {
        long currentTime = System.currentTimeMillis();
        int addToken = (int) ((currentTime - lastTime) / millisPerAdd);
        if (addToken == 0) {
            return;
        }
        System.out.println(LocalDateTime.now() + " add " + addToken);
        if (currentTokenCount + addToken >= maxTokenCount) {
            currentTokenCount = maxTokenCount;
        } else {
            currentTokenCount += addToken;
        }
        lastTime = lastTime + addToken * millisPerAdd;
    }

    public int getPermitsPerSecond() {
        return permitsPerSecond;
    }

    public static void main(String[] args) throws InterruptedException {
        TokenBucketLimiter limiter = new TokenBucketLimiter(20);
        System.out.println(LocalDateTime.now());
        // Thread.sleep(1000l);
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            Thread.sleep(random.nextInt(50));
            System.out.println(LocalDateTime.now() + " : " + i + " result:" + limiter.acquire(1));
        }
    }
}
