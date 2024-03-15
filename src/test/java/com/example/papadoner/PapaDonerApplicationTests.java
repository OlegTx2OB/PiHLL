package com.example.papadoner;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PapaDonerApplicationTests {

    @Test
    void testSum() {
        int result = Integer.sum(2, 3);
        assertEquals(5, result);
    }

}
