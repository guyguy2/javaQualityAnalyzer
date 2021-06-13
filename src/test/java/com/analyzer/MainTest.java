package com.analyzer;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class MainTest {

    private Main main;
    private String[] args = {""};

    @Before
    public void setUp() throws Exception {
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyArg() {
        Main.main(args);
    }
}