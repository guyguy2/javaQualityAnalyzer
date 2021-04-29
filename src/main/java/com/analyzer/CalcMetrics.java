package com.analyzer;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalcMetrics {

    private static final Pattern EOL_PATTERN= Pattern.compile("\r\n|\n|\r");

    public static void calcLoc(List<File> files) throws IOException {
        for (File file : files) {
            String fileContent = FileUtils.readFileToString(file, Charset.defaultCharset());
            int totalLines = calcTotalLines(fileContent);
            System.out.println("CalcMetrics " + file + " -> " + totalLines + " lines");
        }
    }

    private static int calcTotalLines(String fileContent) {
        int lines = 1;
        Matcher m = EOL_PATTERN.matcher(fileContent);

        while (m.find())
        {
            lines ++;
        }

        return lines;
    }
}
