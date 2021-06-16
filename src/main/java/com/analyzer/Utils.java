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
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.utils.SourceRoot;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		// System.out.println("parseClassName"); ///
		List<String> classNames = new ArrayList<>();
		try {
			ParseResult<CompilationUnit> results = javaParser.parse(classFile);
			CompilationUnit s = results.getResult().get();
			System.out.println("Class name: " + s.getTypes().get(0).getName());
			/*
			 * if (s.getTypes().get(0).getMethods().size() > 0) {
			 * System.out.println(s.getTypes().get(0).getMethods().get(1).getName()); }
			 */
		} catch (Exception e) {
		}
		return classNames;
	}

	public static List<String> parseDIT(File classFile, Map<String,String> DIT) {
		// List<String> classNames = new ArrayList<>();
		try {
		
			 CompilationUnit s = StaticJavaParser.parse(classFile);
			  String className = s.getTypes().get(0).getName().toString();
				/*
				 * if(DIT.get(className)==null) { DIT.put(className, ""); }
				 */
			  ClassOrInterfaceDeclaration classToCheck = s.getClassByName(className).orElse(null);
			  String _class = classToCheck.getExtendedTypes().toString();

			  if(DIT.get(_class)==null) {
				  DIT.put(_class, "");
			  }
			  
			  DIT.put("["+className+"]", _class);

		} catch (Exception e) {
			System.out.println("error");
		}
		return null;
	}

	/*
	 * private static Map<String,Integer> checkDIT(Map<String,String> map) {
	 * Map<String,Integer> newMap = new HashMap<>();
	 * 
	 * for(String key : map.keySet()) { newMap.put(key, 0); }
	 * 
	 * for(String s:map.keySet()) { if(map.get(s)!=null) {
	 * 
	 * newMap.put(s, newMap.get(s)+1); } }
	 * 
	 * for(String s: map) newMap.put(s, newMap.get(map.get(s)));
	 * 
	 * return newMap; }
	 */

	public static List<String> parseNOC(File classFile, Map<String,Integer> numOfChildren) {
		
		  CompilationUnit s;
		try {
			  s = StaticJavaParser.parse(classFile);
			  String className = s.getTypes().get(0).getName().toString();
			  if(numOfChildren.get(className)==null) {
				  numOfChildren.put("["+className+"]", 0); 
			  }
			  ClassOrInterfaceDeclaration classToCheck = s.getClassByName(className).orElse(null);
			  String _class = classToCheck.getExtendedTypes().toString();

			  if(numOfChildren.get(_class)==null) {
				  numOfChildren.put(_class, 0);
			  }
			  
			  numOfChildren.put(_class, numOfChildren.get(_class)+1);
			  
			
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		 return null; 
		 
	}

}
