package com.analyzer.interfaces;

import java.io.File;
import java.util.List;

//base class for all metrics
public abstract class Metric {
	protected List<String> javaFilePathList;
	protected List<File> javaFileObjects;

	public abstract void run();
}
