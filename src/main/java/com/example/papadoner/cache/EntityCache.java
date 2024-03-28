package com.example.papadoner.cache;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class EntityCache<K, V> {

    private final Map<K, V> cacheMap = new ConcurrentHashMap<>();

    public void put(K key, V value) {
        cacheMap.put(key, value);
    }

    public V get(K key) {
        return cacheMap.get(key);
    }

    public List<V> getAll() {
        return new ArrayList<>(cacheMap.values());
    }

    public void remove(K key) {
        cacheMap.remove(key);
    }
}
