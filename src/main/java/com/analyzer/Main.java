package com.analyzer;

public class Main {

    //TODO https://github.com/javaparser/javaparser ?
    public static void main(String[] args) {
        System.out.println("Java Quality Analyzer (c) Spring 2021 - SE433 - Group 2\n");

        if (args.length == 0) {
            printUsage();
        } else if (args.length > 1){
            System.out.println("Only one argument expected");
            printUsage();
        } else {
            String directory = args[0];
            System.out.println("Current working directory = '" + System.getProperty("user.dir") + "'"+"\n");
            System.out.println("Scanning directory '" + directory + "'"+"\n");
            SourceCodeLoader.load(directory);
        }
    }

    private static void printUsage() {
        System.out.println("\nUsage: ");
        System.out.println("\t<path of source code to scan>");
        System.out.println("\toutput will be to the console and/or to current directory, as a file");
    }
}
