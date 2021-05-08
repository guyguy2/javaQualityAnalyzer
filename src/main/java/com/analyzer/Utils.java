package com.analyzer;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class Utils {

    public static String getFileContent(File file)  {
        String fileContent = null;
        try {
            fileContent = FileUtils.readFileToString(file, Charset.defaultCharset());
        } catch (IOException e) {
            System.err.println("Error while reading file");
            e.printStackTrace();
        }
        return fileContent;
    }
}
