package com.analyzer.metrics;

import com.analyzer.Utils;
import com.analyzer.interfaces.Metric;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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

		for (File file : javaFileObjects) {
			System.out.println("---------------------------------");///
			List<String> classNames = extractClassNamesFromClassFiles(file);

			for (String name : classNames) {
				System.out.println("**class name: " + name);
			}
		}
	}



	private List<String> extractClassNamesFromClassFiles(File file) {
		System.out.println("Extracting classes from file '" + file + "'");
		///
//		JavaParser javaParser = new JavaParser();
//		try {
//			ParseResult<CompilationUnit> results = javaParser.parse(file);
//			CompilationUnit s = results.getResult().get();
//			System.out.println(s.getTypes().size());
//			System.out.println(s.getTypes().get(0).getName());
//			System.out.println(s.getTypes().get(0).getMethods().size());
//			if (s.getTypes().get(0).getMethods().size() > 1) {
//				System.out.println(s.getTypes().get(0).getMethods().get(1).getName());
//			}
//			System.out.println("Parent: ");
//
//			CompilationUnit cu = StaticJavaParser.parse(file);
//			VoidVisitor<Void> methodinherts = new InheritedClasses();
//			methodinherts.visit(cu, null);
//
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
		///
		List<String> parseParents = Utils.parseParents(file);

		Utils.parseClassMethods(file);

		Utils.parseClassName(file);
//		System.out.println("parseParents: " + parseParents);

//		String fileContent = Utils.getFileContent(file);
//		int indexOfClassKeyword = fileContent.indexOf("class");
//		while(indexOfClassKeyword >= 0) {
//			System.out.println(indexOfClassKeyword);///
//			String temp = fileContent.substring(indexOfClassKeyword + "class".length());
//			temp = temp.trim();
//			int spaceIndex = temp.indexOf(' ');
//			System.out.println(temp.substring(spaceIndex));
//			indexOfClassKeyword = fileContent.indexOf("class", indexOfClassKeyword+1);
//
//			int nextSpace = fileContent.indexOf(" ", (indexOfClassKeyword + "class".length()+1));
//			System.out.println("nextSpace: "  + nextSpace);
//			String className = fileContent.substring(indexOfClassKeyword,nextSpace);
//			System.out.println("class name: " + className);
//
//		}

		return parseParents;
	}

	private List<String> parseDoc(File classFile) {
		List<String> classNames = new ArrayList<>();
		try {
			CompilationUnit cu = StaticJavaParser.parse(classFile);
			new VoidVisitorAdapter<Object>() {
				@Override
				public void visit(JavadocComment comment, Object arg) {
					super.visit(comment, arg);
					if (comment.getCommentedNode().get() instanceof MethodDeclaration) {
						MethodDeclaration node = (MethodDeclaration) comment.getCommentedNode().get();
						classNames.add(comment.toString());
					}
				}
			}.visit(cu, null);
		} catch (Exception e) {
		}
		return classNames;
	}



}


