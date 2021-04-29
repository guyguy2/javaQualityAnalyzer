package com.analyzer;

public class Main {

    public static void main(String[] args) {
        System.out.println("Java Quality Analyzer (c) Spring 2021 - SE433 - Group 2");

        if (args.length == 0) {
            printUsage();
        } else if (args.length > 1){
            System.out.println("Only one argument expected");
            printUsage();
        } else {
            System.out.println("Working Directory = " + System.getProperty("user.dir"));
            System.out.println("Scanning directory '" + args[0] + "'");
        }
    }

    private static void printUsage() {
        System.out.println("\nUsage: ");
        System.out.println("\t<path of source code to scan>");
        System.out.println("\toutput will be to the console and/or to current directory, as a file");
    }
}
