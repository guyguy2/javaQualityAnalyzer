package com.analyzer;

import com.analyzer.interfaces.Metric;
import com.analyzer.metrics.Dit;
import com.analyzer.metrics.Wmc;
import org.apache.commons.io.FileUtils;

import com.analyzer.metrics.CountComments;
import com.analyzer.metrics.Loc;
import com.analyzer.metrics.NOC;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class MetricsManager {

    private List<String> javaFilePathList;
    private List<File> javaFileObjects;
    private List<Path> mainPath;
    private List<Metric> metrics = new ArrayList<>();

    public MetricsManager(List<String> javaFilePathList, List<File> javaFileObjects) {
        this.javaFilePathList = javaFilePathList;
        this.javaFileObjects = javaFileObjects;
       // this.mainPath = mainFile;

        metrics.add(new CountComments(javaFilePathList, javaFileObjects));
        metrics.add(new Dit(javaFilePathList, javaFileObjects));
        metrics.add(new Loc(javaFilePathList, javaFileObjects));
        metrics.add(new Wmc(javaFilePathList, javaFileObjects));
        metrics.add(new NOC(javaFilePathList, javaFileObjects));
    }



    public void calcMetrics()  {
        for (Metric metric : metrics) {
            metric.run();
        }
    }
}
