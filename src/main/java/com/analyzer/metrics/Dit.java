package com.analyzer.metrics;

import com.analyzer.interfaces.Metric;

import java.io.File;
import java.util.List;

public class Dit extends Metric {

	public Dit(List<String> javaFilePathList, List<File> javaFileObjects) {
		this.javaFilePathList = javaFilePathList;
		this.javaFileObjects = javaFileObjects;
	}

	@Override
	public void run() {
		System.out.println("\nDIT is running");
		
	}

}
