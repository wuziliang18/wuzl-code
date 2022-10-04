package org.wuzl.test.algorithm;

/**
 * 视频片段 clips[i] 都用区间进行表示：开始于 clips[i][0] 并于 clips[i][1] 结束。我们甚至可以对这些片段自由地再剪辑，例如片段 [0, 7] 可以剪切成 [0, 1] + [1, 3] + [3,
 * 7] 三部分。
 * 
 * 我们需要将这些片段进行再剪辑，并将剪辑后的内容拼接成覆盖整个运动过程的片段（[0, T]）。返回所需片段的最小数目，如果无法完成该任务，则返回 -1 。
 * 
 * 链接：https://leetcode-cn.com/problems/video-stitching
 * 
 * @author ziliang.wu
 *
 */
public class VideoStitching {
    public int videoStitching(int[][] clips, int t) {
        if (t == 0) {
            return 0;
        }
        if (clips == null | clips.length == 0) {
            return -1;
        }

        int[] maxArray = new int[t + 1];
        for (int[] clip : clips) {
            int start = clip[0];
            int end = clip[1];
            maxArray[start] = Math.max(maxArray[start], end);
        }
        if (maxArray[0] == 0) {
            return -1;
        }
        int count = 1;
        int start = maxArray[0];
//        while (true) {
//
//        }
        return count;
    }
}
