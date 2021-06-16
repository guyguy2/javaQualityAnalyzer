package com.analyzer;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//TODO save data
//use https://github.com/gt4dev/yet-another-tree-structure for inheritance / dependencies tree?
public class SourceCodeLoader {
    private static final List<String> javaFilePathList = new ArrayList<>();
    private static final List<File> javaFileObjects = new ArrayList<>();

    public static void load(String directory) {
        Path path = Paths.get(directory);
        if (Files.isDirectory(path)) {
            System.out.println("Path is a directory\n");
            File directoryPath = new File(directory);

            try {
                System.out.println("Getting all java files in " + directoryPath.getCanonicalPath() + " including those in subdirectories:");
                List<File> files = (List<File>) FileUtils.listFiles(directoryPath, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
                for (File file : files) {
                    javaFileObjects.add(file);
                    System.out.println("file: " + file.getCanonicalPath());
                    if (file.getCanonicalPath().endsWith(".java")) {
                        javaFilePathList.add(file.getCanonicalPath());
                    }
                }

                calculateMetrics();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.err.println("path is not a directory. Exiting.");
            System.exit(1);
        }
    }

    private static void calculateMetrics() throws IOException {
        MetricsManager metricsManager = new MetricsManager(javaFilePathList, javaFileObjects);
        metricsManager.calcMetrics();
    }

}
