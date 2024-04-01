package com.example.papadoner.cache;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class EntityCache<K, V> {

    private final Map<K, V> mCacheMap = new ConcurrentHashMap<>();

    public void put(K key, V value) {
        mCacheMap.put(key, value);
    }

    public V get(K key) {
        return mCacheMap.get(key);
    }

    public Set<V> getAll() {
        return new HashSet<>(mCacheMap.values());
    }

    public void remove(K key) {
        mCacheMap.remove(key);
    }
}
