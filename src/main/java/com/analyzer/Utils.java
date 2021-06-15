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

	public static List<Path> parseDIT(List<Path> main) {
		// List<String> classNames = new ArrayList<>();
		try {
			/*
			 * int maxDIT = 1; CompilationUnit s = StaticJavaParser.parse(classFile); String
			 * className = s.getTypes().get(0).getName().toString();
			 * ClassOrInterfaceDeclaration classToCheck =
			 * s.getClassByName(className).orElse(null); System.out.println("Extends: "
			 * +classToCheck.getExtendedTypes().toString());
			 * System.out.println("Implements: "
			 * +classToCheck.getImplementedTypes().toString());
			 */
			/*
			 * if(classToCheck.getExtendedTypes().size() != 0) {
			 * System.out.println("Has parent: "+
			 * classToCheck.getExtendedTypes().get(0).toString());
			 * 
			 * maxDIT++; classToCheck =
			 * s.getClassByName(classToCheck.getExtendedTypes().getFirst().toString()).
			 * orElse(null); System.out.println("Extends: " +classToCheck);
			 * 
			 * }
			 */
			// CompilationUnit s = StaticJavaParser.parse
			// JavaParserTypeSolver s = new JavaParserTypeSolver(main.get(0));
			// System.out.println(main.get(0).getFileName().toString());

			// ParseResult<CompilationUnit> mainS = new JavaParser().parse(main.get(0));
			int j = 0;
			Map<String, String> map = new HashMap<>();
			System.out.println(map.toString());
			SourceRoot sourceRoot = new SourceRoot(main.get(0));
			List<ParseResult<CompilationUnit>> mainS = sourceRoot.tryToParse();
			List<CompilationUnit> compilations = sourceRoot.getCompilationUnits();
			for (int i = 0; i < compilations.size(); i++) {
				String className = compilations.get(i).getTypes().get(0).getName().toString();
				ClassOrInterfaceDeclaration classToCheck = compilations.get(i).getClassByName(className).orElse(null);
				if (!map.containsKey(className)) {
					if (classToCheck.getExtendedTypes().size() > 0) {
						map.put(className, classToCheck.getExtendedTypes().get(0).toString());
						j++;
					}
				}

			}

			System.out.println(map.toString());
			// Map<String,Integer> check = checkDIT(map);
			// System.out.println(check.toString());

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
				  numOfChildren.put(className, 0); 
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
