package com.sxc;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description: 使用LinkedHashMap实现一个固定大小的缓存器
 * @Date:
 */
public class LRUCacheUtil<K, V> extends LinkedHashMap<K, V> {

    // 最大缓存容量
    private static final int CACHE_MAX_SIZE = 100;

    private int limit;

    public LRUCacheUtil() {
        this(CACHE_MAX_SIZE);
    }

    public LRUCacheUtil(int cacheSize) {
        // true表示更新到末尾
        super(cacheSize, 0.75f, true);
        this.limit = cacheSize;
    }

	/**
	 * 加锁同步，防止多线程时出现多线程安全问题
	 */
    public synchronized V save(K key, V val) {
        return put(key, val);
    }

    public V getOne(K key) {
        return get(key);
    }

    public boolean exists(K key) {
        return containsKey(key);
    }

    /**
     * 判断是否超限
     * @param elsest
     * @return 超限返回true，否则返回false
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry elsest) {
        // 在put或者putAll方法后调用，超出容量限制，按照LRU最近最少未使用进行删除
        return size() > limit;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<K, V> entry : entrySet()) {
            sb.append(String.format("%s:%s ", entry.getKey(), entry.getValue()));
        }
        return sb.toString();
    }
}
