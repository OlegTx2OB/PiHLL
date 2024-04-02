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

    public Optional<V> get(K key) {
        return Optional.of(mCacheMap.get(key));
    }

    public List<V> getAll() {
        return new ArrayList<>(mCacheMap.values());
    }

    public void remove(K key) {
        mCacheMap.remove(key);
    }
}
