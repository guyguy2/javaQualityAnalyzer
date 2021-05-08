package com.analyzer.metrics;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.analyzer.Utils;
import com.analyzer.interfaces.Metric;
import org.apache.commons.io.FileUtils;

public class Loc extends Metric {

    private static final Pattern EOL_PATTERN = Pattern.compile("\r\n|\n|\r");

    public Loc(List<String> javaFilePathList, List<File> javaFileObjects) {
        this.javaFilePathList = javaFilePathList;
        this.javaFileObjects = javaFileObjects;
    }

    @Override
    public void run() {
        System.out.println("\nLOC is running");

        for (File file : javaFileObjects) {
            System.out.println(file + " -> " + calcTotalLines(Utils.getFileContent(file)));
        }
    }


    private int calcTotalLines(String fileContent) {
        int lines = 1;
        Matcher m = EOL_PATTERN.matcher(fileContent);

        while (m.find()) {
            lines++;
        }

        return lines;
    }


}
