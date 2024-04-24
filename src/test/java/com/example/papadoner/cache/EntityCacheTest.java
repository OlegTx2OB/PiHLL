package com.example.papadoner.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EntityCacheTest {

    private EntityCache<String, Object> cache;

    @BeforeEach
    void setUp() {
        cache = new EntityCache<>();
    }

    @Test
    void testPutAndGet_EntityCacheTest() {
        String key = "testKey";
        String value = "testValue";

        cache.put(key, value);

        assertEquals(Optional.of(value), cache.get(key));
    }

    @Test
    void getAll_countIsEqualsToAddedCount_EntityCacheTest() {
        for (int i = 0; i < 201; i++) {
            cache.put("key" + i, "value" + i);
        }
        cache.put("keyNew", "valueNew");

        assertEquals(202, cache.getAll().size());
        assertNotNull(cache.get("keyNew"));
    }

    @Test
    void removeMethod_correctRemove_EntityCacheTest() {
        cache.put("key1", "value1");
        cache.put("key2", "value2");

        assertTrue(cache.remove("key1"));
    }

}
