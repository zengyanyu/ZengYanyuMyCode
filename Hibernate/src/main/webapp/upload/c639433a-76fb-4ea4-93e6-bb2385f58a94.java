package com.frame.family.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 计算一个指定磁盘目录下的所有以.java文件为后缀的java代码行数
 * @author 86134
 */
@SuppressWarnings("all")
public class CalcJavaCodeLine {

	private static ArrayList<File> list = new ArrayList<>();

	private static int num = 0;

	public static void main(String[] args) throws IOException {
		//指定文件目录
		File dir = new File("D:\\ideaProject");
		List<File> javaDirCollection = listAllFiles(dir);
		for (File file : javaDirCollection) {
			calcLine(file);
		}
		System.out.println("Code_TotalCountLine_For:  " + num);
	}

	public static void calcLine(File file) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(file));
		String line = null;
		while ((line = in.readLine()) != null) {
			if (line.startsWith("import")) {
				continue;
			}
			if (line.startsWith("package")) {
				continue;
			}
			if (line.startsWith("@")) {
				continue;
			}
			if (line.startsWith("//")) {
				continue;
			}
			//打印java文件中的每一行代码
			System.out.println(line);
			num++;
		}
	}

	public static List<File> listAllFiles(File dir) {
		File[] AllJavaFile = dir.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return new File(dir, name).isFile() && name.endsWith(".java");
			}
		});
		for (File file : AllJavaFile) {
			list.add(file);
		}

		File[] javaFiles = dir.listFiles();
		for (File file : javaFiles) {
			if (file.isDirectory()) {
				listAllFiles(file);
			}
		}
		return list;
	}

}
