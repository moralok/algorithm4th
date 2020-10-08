package com.moralok.algorithm4th.ch5strings;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author moralok
 * @since 2020/10/7
 */
class ForceSearchTests {

    private String pat;

    private String txt;

    @BeforeEach
    void setUp() {
        pat = "ABRA";
        txt = "ABACADABRAC";
    }

    @Test
    void testSearch1() {
        assertEquals(6, ForceSearch.search1(pat, txt));
    }

    @Test
    void testSearch2() {
        assertEquals(6, ForceSearch.search2(pat, txt));
    }
}
