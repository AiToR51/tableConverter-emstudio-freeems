package org.freeems.tableConverter.main;

import java.io.File;

import org.freeems.tableConverter.model.types.Table;
import org.freeems.tableConverter.parser.EMStudioTableParser;

public class Main {

	public static void main(String args[]) {
		if (args.length >= 1 ) {
			String inputFileName = args[0];
			
			
			File inputFile = new File(inputFileName);
			if (!inputFile.exists()) {
				System.out.println("The file '" + inputFileName + "' doesn't exists.");
				return;
			}
			boolean decimals = getDecimals(args);
			int extraSpaces = getExtraSpaces(args);
			boolean dumpMetaData = getDumpMetaData(args);
			EMStudioTableParser parser = new EMStudioTableParser(inputFileName);
			
			Table table = parser.parseFile();
			if (table != null) {
				table.printFreeEMSTable(decimals, extraSpaces, dumpMetaData);
			}
		
			
		} else {
			printHelp();
		}
	}
	
	public static void printHelp() {
		System.out.println("Use: \n" 
				+ "\tjava -jar tableConverter.jar input.json [-noDecimals] [-extraSpaces=2] [-dump-meta-data]");
	}
	
	private static boolean getDecimals(String args[]) {
		for (String s : args) {
			if (s.equalsIgnoreCase("-noDecimals")) {
				return false;
			}
		}
		return true;
	}
	private static int getExtraSpaces(String args[]) {
		int extraSpaces = 1;
		for (String s : args) {
			if (s.startsWith("-extraSpaces=")) {
				extraSpaces = Integer.parseInt(s.substring(13));
			}
		}
		
		return extraSpaces;
	}
	
	private static boolean getDumpMetaData(String args[]) {
		for (String s : args) {
			if (s.equalsIgnoreCase("-dump-meta-data")) {
				return true;
			}
		}
		return false;
	}
}
