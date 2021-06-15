package com.analyzer.metrics;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.analyzer.Utils;
import com.analyzer.interfaces.Metric;

public class NOC extends Metric {

	Map<String, Integer> numberOfChildrenPerFile = new HashMap<>();
	
	public NOC(List<String> javaFilePathList, List<File> javaFileObjects) {
		this.javaFilePathList = javaFilePathList;
		this.javaFileObjects = javaFileObjects;
	}
	
	@Override
	public void run() {
		System.out.println();
		System.out.println("NOC is running");
		for (File file : javaFileObjects) {
			List<String> classNames = extractChildrenFromClassFiles(file, numberOfChildrenPerFile);
			

		}
		numberOfChildrenPerFile.remove("[]");
		for(Map.Entry<String, Integer> numChildren: numberOfChildrenPerFile.entrySet()) {
			
			System.out.println("Class: " + numChildren.getKey());
			System.out.println("\t Number of children: " + numChildren.getValue());
		}
	}
	
	private List<String> extractChildrenFromClassFiles(File file, Map<String,Integer> numOfChildren) {
		System.out.println("Extracting children from file '" + file + "'");
		
		List<String> parseChildren = Utils.parseNOC(file, numberOfChildrenPerFile);
		
		
		return javaFilePathList;
		
	}

}
