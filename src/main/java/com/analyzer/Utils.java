package com.analyzer;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    private static JavaParser javaParser = new JavaParser();

    public static String getFileContent(File file) {
        String fileContent = null;
        try {
            fileContent = FileUtils.readFileToString(file, Charset.defaultCharset());
        } catch (IOException e) {
            System.err.println("Error while reading file");
            e.printStackTrace();
        }
        return fileContent;
    }

    public static List<String> parseParents(File classFile) {
        List<String> parentNames = new ArrayList<>();
        try {
            CompilationUnit cu = StaticJavaParser.parse(classFile);
            new VoidVisitorAdapter<Object>() {
                @Override
                public void visit(ClassOrInterfaceDeclaration cid, Object arg) {
                    super.visit(cid, arg);
                    parentNames.add(cid.getExtendedTypes().toString());
                }
            }.visit(cu, null);
        } catch (Exception e) {
        }
        return parentNames;
    }

    public static List<String> parseClassMethods(File classFile) {
        List<String> classNames = new ArrayList<>();
        try {
            CompilationUnit cu = StaticJavaParser.parse(classFile);
            new VoidVisitorAdapter<Object>() {
                @Override
                public void visit(MethodDeclaration methodDeclaration, Object arg) {
                    super.visit(methodDeclaration, arg);
                    System.out.println("Method name: " + methodDeclaration.getName());
                    classNames.add(methodDeclaration.toString());
                }
            }.visit(cu, null);
        } catch (Exception e) {
        }
        return classNames;
    }

    public static List<String> parseClassName(File classFile) {
        System.out.println("parseClassName"); ///
        List<String> classNames = new ArrayList<>();
        try {
            ParseResult<CompilationUnit> results = javaParser.parse(classFile);
			CompilationUnit s = results.getResult().get();
			System.out.println(s.getTypes().size());
			System.out.println(s.getTypes().get(0).getName());
			System.out.println(s.getTypes().get(0).getMethods().size());
			if (s.getTypes().get(0).getMethods().size() > 0) {
				System.out.println(s.getTypes().get(0).getMethods().get(1).getName());
			}
        } catch (Exception e) {
        }
        return classNames;
    }

}

