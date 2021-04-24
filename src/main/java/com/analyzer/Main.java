package com.analyzer;

public class Main {

    public static void main(String[] args) {
        System.out.println("Java Quality Analyzer (c) 2021 - SE433");
        System.out.println("\nUsage: ");
        System.out.println("\t-help - print usage");
        System.out.println("\t-input=<source directory> - scan this directory for .java files");

        if (args.length == 0) {
            System.out.println("\nPlease enter some arguments");
        } else {
            //parse arguments
            System.out.println("Arguments detected: ");
            for (String arg : args) {
                System.out.println(arg);
            }
        }
    }
}
