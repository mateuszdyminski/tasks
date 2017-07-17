package com.rle;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class RleTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "", "" }, { "W", "1W" }, { "WWWWHHHH", "4W4H" }, { "WWWWWWWWWWWWBWWWWWWWWWWWWBBBWWWWWWWWWWWWWWWWWWWWWWWWBWWWWWWWWWWWWWW", "12W1B12W3B24W1B14W" }
        });
    }

    private final String input;
    private final String expected;

    public RleTest(String input, String expected) {
        this.input = input;
        this.expected = expected;
    }

    @Test
    public void rleTest() throws Exception {
        assertEquals(expected, Rle.encode(input));
    }
}