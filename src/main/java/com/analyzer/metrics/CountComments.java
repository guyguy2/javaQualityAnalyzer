package com.analyzer.metrics;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.analyzer.Utils;
import com.analyzer.interfaces.Metric;

public class CountComments extends Metric {


    private static final Pattern SCOM_PATTERN = Pattern.compile("(//.*?$)|(/\\*.*?\\*/)", Pattern.MULTILINE | Pattern.DOTALL);
    private static int totalComments = 0;

    public CountComments(List<String> javaFilePathList, List<File> javaFileObjects) {
        this.javaFilePathList = javaFilePathList;
        this.javaFileObjects = javaFileObjects;
    }

    @Override
    public void run() {
        System.out.println("\nCountComments is running");

		for (File file : javaFileObjects) {
			System.out.println(file + " -> " + countComments(Utils.getFileContent(file)));
		}

    }

    private int countComments(String fileContent) {

        int comments = 0;

        Matcher m = SCOM_PATTERN.matcher(fileContent);
        while (m.find()) {
            comments++;
            totalComments++;
        }

        return comments;

    }

    private float calcCodeToCommentRatio(List<File> files) {
        return 0.0f;
    }

    private int totalComments() {
        return totalComments;
    }
}
