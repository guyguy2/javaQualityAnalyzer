package com.analyzer.metrics;

import com.analyzer.Utils;
import com.analyzer.interfaces.Metric;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Dit extends Metric {

	HashMap<String, String> numberOfChildrenPerFile = new LinkedHashMap();
	Map<String, Integer> count = new HashMap<>();
	
	public Dit(List<String> javaFilePathList, List<File> javaFileObjects) {
		this.javaFilePathList = javaFilePathList;
		this.javaFileObjects = javaFileObjects;
	}

	@Override
	public void run() {
		System.out.println();
		System.out.println("DIT is running");
		for (File file : javaFileObjects) {
			List<String> classNames = extractDITFromClassFiles(file, numberOfChildrenPerFile);
			

		}
		numberOfChildrenPerFile.remove("[]");
		for(HashMap.Entry<String, String> numChildren: numberOfChildrenPerFile.entrySet()) {
			count.put(numChildren.getKey(),1);
						
		}
		for(HashMap.Entry<String, String> numChildren: numberOfChildrenPerFile.entrySet()) {
		String dit = numChildren.getValue();
		if(dit!="" && dit!="[]" && dit!=null) {
			if(count.get(dit)!=null)
				count.put(dit, count.get(dit)+1);
		}
		}
		System.out.println("Depth of Inheritance (DIT):");
		System.out.println("--------------------------------------------------");
		for(Map.Entry<String, Integer> num: count.entrySet()) {
			
			
			System.out.println(String.format("Class: "+"%-8s --> %-28s |", num.getKey(), num.getValue() ));
			System.out.println("--------------------------------------------------");

		}

	}
	
	
	
	private List<String> extractDITFromClassFiles(File file, Map<String,String> DIT) {
		System.out.println("Extracting DIT from file '" + file + "'");
		Utils.parseDIT(file, numberOfChildrenPerFile);
		
		
		return javaFilePathList;
		
	}
}
