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
    private static final Pattern SCOM_PATTERN = Pattern.compile("(//.*?$)|(/\\*.*?\\*/)", Pattern.MULTILINE | Pattern.DOTALL);
    private static int totalComments = 0;
    //add pattern matcher for single and multi line comments
    //maybe stick to only single comments

    public static void calcLoc(List<File> files) throws IOException {
        for (File file : files) {
            String fileContent = FileUtils.readFileToString(file, Charset.defaultCharset());
            int totalLines = calcTotalLines(fileContent);
            int totalComments = countComments(fileContent);
            System.out.println("CalcMetrics " + file + " -> " + totalLines + " lines");
            System.out.println("coutComments " + file + " -> " + totalComments + " comments" );
        }
    }

    private static int calcTotalLines(String fileContent) {
        int lines = 1;
        Matcher m = EOL_PATTERN.matcher(fileContent);

        while (m.find()) {
            lines ++;
        }

        return lines;
    }

    public static int countComments(String fileContent) {
    	
    	int comments = 0;
    	
    	Matcher m = SCOM_PATTERN.matcher(fileContent);
    	while(m.find()) {
    		comments++;
    		totalComments++;
    	}
    	
    	return comments;
    	
    	

    }

    public static float calcCodeToCommentRatio(List<File> files) {
        return 0.0f;
    }
    
    public static int totalComments(){
    	return totalComments;
    }
}
