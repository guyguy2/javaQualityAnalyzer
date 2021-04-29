package com.analyzer;

import com.sun.javafx.font.Metrics;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//TODO save data
//use https://github.com/gt4dev/yet-another-tree-structure for inheritance / dependencies tree?
public class SourceCodeLoader {
    private String location; /// remove?
    private static int fileCount = 0;
    private static List<String> javaFileList = new ArrayList<>();

    public static void load(String directory) {
        Path path = Paths.get(directory);
        if (Files.isDirectory(path)) {
            System.out.println("path is a directory");
            File directoryPath = new File(directory);

            try {
                System.out.println("Getting all files in " + directoryPath.getCanonicalPath() + " including those in subdirectories");
                List<File> files = (List<File>) FileUtils.listFiles(directoryPath, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
                for (File file : files) {
                    System.out.println("file: " + file.getCanonicalPath());
                    if (file.getCanonicalPath().endsWith(".java")) {
                        fileCount++;
                        javaFileList.add(file.getCanonicalPath());
                    }
                    String fileContent = FileUtils.readFileToString(file, Charset.defaultCharset());
                    System.out.println(fileContent);

                }

                CalcMetrics.calcLoc(files);

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("path is not a directory");
            ///exit
        }
        System.out.println();
        System.out.println("Total java files found: " + fileCount);
    }
}
