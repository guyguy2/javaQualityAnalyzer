package com.analyzer;

import org.junit.Test;

import static org.junit.Assert.*;

public class SourceCodeLoaderTest {

    @Test (expected = IllegalArgumentException.class)
    public void testLoadEmptyArg() {
        SourceCodeLoader.load("");
    }
}