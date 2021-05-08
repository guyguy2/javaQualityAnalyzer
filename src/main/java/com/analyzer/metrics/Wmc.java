package com.analyzer.metrics;

import com.analyzer.interfaces.Metric;

import java.io.File;
import java.util.List;

//Weighted methods per class
public class Wmc extends Metric {

	public Wmc(List<String> javaFilePathList, List<File> javaFileObjects) {
		this.javaFilePathList = javaFilePathList;
		this.javaFileObjects = javaFileObjects;
	}

	@Override
	public void run() {
		System.out.println("\nWMC is running");
		
	}
}
