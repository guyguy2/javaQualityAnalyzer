package com.analyzer.metrics;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.analyzer.interfaces.Metric;

public class CountComments implements Metric {

	
	private static final Pattern SCOM_PATTERN = Pattern.compile("(//.*?$)|(/\\*.*?\\*/)", Pattern.MULTILINE | Pattern.DOTALL);
    private static int totalComments = 0;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
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
