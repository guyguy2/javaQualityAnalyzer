package com.analyzer.metrics;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.analyzer.interfaces.Metric;

public class Loc implements Metric {

	private static final Pattern EOL_PATTERN= Pattern.compile("\r\n|\n|\r");
    
	
	
	@Override
	public void run() {
	
	}

	
	 public static int calcTotalLines(String fileContent) {
	        int lines = 1;
	        Matcher m = EOL_PATTERN.matcher(fileContent);

	        while (m.find()) {
	            lines ++;
	        }

	        return lines;
	    }

	    
	
}
