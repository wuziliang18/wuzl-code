package org.wuzl.test.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 设计推特
 *TODO 没有考虑排序的问题 改成链表吧
 * 设计一个简化版的推特(Twitter)，可以让用户实现发送推文，关注/取消关注其他用户，能够看见关注人（包括自己）的最近十条推文。你的设计需要支持以下的几个功能：
 * 
 * postTweet(userId, tweetId): 创建一条新的推文 getNewsFeed(userId): 检索最近的十条推文。每个推文都必须是由此用户关注的人或者是用户自己发出的。推文必须按照时间顺序由最近的开始排序。
 * follow(followerId, followeeId): 关注一个用户 unfollow(followerId, followeeId): 取消关注一个用户
 * 
 * @author ziliang.wu
 *
 */
public class Twitter {
    private final Map<Integer, Set<Integer>> followMap = new HashMap<>();
    private final Map<Integer, Set<Integer>> followedMap = new HashMap<>();
    private final Map<Integer, LinkedHashSet<Integer>> myTweetDataMap = new HashMap<Integer, LinkedHashSet<Integer>>();
    private final Map<Integer, LinkedHashSet<Integer>> tweetDataMap = new HashMap<Integer, LinkedHashSet<Integer>>();

    /** Initialize your data structure here. */
    public Twitter() {

    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        LinkedHashSet<Integer> set = tweetDataMap.get(userId);
        if (set == null) {
            set = new LinkedHashSet<Integer>();
            tweetDataMap.put(userId, set);
        }
        set.add(tweetId);

        set = myTweetDataMap.get(userId);
        if (set == null) {
            set = new LinkedHashSet<Integer>();
            myTweetDataMap.put(userId, set);
        }
        set.add(tweetId);

        this.addDataByFollowed(userId, tweetId);
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users
     * who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
        LinkedHashSet<Integer> set = tweetDataMap.get(userId);
        if (set == null || set.isEmpty()) {
            return Collections.emptyList();
        }
        List<Integer> result = new ArrayList<>();
        int count = set.size() >= 10 ? 10 : set.size();
        Iterator<Integer> ite = set.iterator();
        int i = 0;
        while (ite.hasNext() && i++ < count) {
            result.add(ite.next());
        }
        return result;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (followerId <= 0 || followeeId <= 0) {
            return;
        }
        Set<Integer> follewSet = followMap.get(followerId);
        if (follewSet == null) {
            follewSet = new HashSet<>();
            followMap.put(followerId, follewSet);
        }
        if (follewSet.contains(followeeId)) {
            return;
        }
        follewSet.add(followeeId);
        Set<Integer> follewedSet = followedMap.get(followeeId);
        if (follewedSet == null) {
            follewedSet = new HashSet<Integer>();
            followedMap.put(followeeId, follewedSet);
        }
        follewedSet.add(followerId);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (followerId <= 0 || followeeId <= 0) {
            return;
        }
        Set<Integer> follewSet = followMap.get(followerId);
        if (follewSet == null || follewSet.isEmpty()) {
            return;
        }
        boolean result = follewSet.remove(followeeId);
        if (result) {
            Set<Integer> follewedSet = followedMap.get(followeeId);
            if (follewedSet != null) {
                follewedSet.remove(followerId);
            }
            this.removeDataByFollow(followerId, followeeId);
        }
    }

    private void addDataByFollowed(int userId, int tweetId) {
        Set<Integer> follewedSet = followedMap.get(userId);
        if (follewedSet == null) {
            return;
        }
        for (int followUserId : follewedSet) {
            LinkedHashSet<Integer> set = tweetDataMap.get(followUserId);
            if (set == null) {
                set = new LinkedHashSet<Integer>();
                tweetDataMap.put(followUserId, set);
            }
            set.add(tweetId);
        }
    }

    private void removeDataByFollow(int followerId, int followeeId) {
        LinkedHashSet<Integer> set = tweetDataMap.get(followerId);
        if (set == null) {
            return;
        }
        LinkedHashSet<Integer> delSet = myTweetDataMap.get(followeeId);
        if (delSet != null && !delSet.isEmpty()) {
            set.removeAll(delSet);
        }
    }
}
