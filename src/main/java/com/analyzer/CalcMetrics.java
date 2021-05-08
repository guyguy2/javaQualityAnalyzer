package com.analyzer;

import org.apache.commons.io.FileUtils;

import com.analyzer.metrics.CountComments;
import com.analyzer.metrics.Loc;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalcMetrics {

    
    //add pattern matcher for single and multi line comments
    //maybe stick to only single comments

    public static void calcLoc(List<File> files) throws IOException {
        for (File file : files) {
            String fileContent = FileUtils.readFileToString(file, Charset.defaultCharset());
            int totalLines = Loc.calcTotalLines(fileContent);
            int totalComments = CountComments.countComments(fileContent);
            System.out.println("CalcMetrics " + file + " -> " + totalLines + " lines");
            System.out.println("coutComments " + file + " -> " + totalComments + " comments" );
        }
    }

   
}
