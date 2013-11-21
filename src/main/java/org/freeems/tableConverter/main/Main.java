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
				System.out.println("The file '" + inputFileName + "' don't exists.");
				return;
			}
			boolean decimals = true;
			//hack for Fred to don't have decimals
			if (args.length == 2 && args[1].equalsIgnoreCase("-noDecimals")) {
				decimals = false;
			}
			EMStudioTableParser parser = new EMStudioTableParser(inputFileName);
			
			Table table = parser.parseFile();
			if (table != null) {
				table.printFreeEMSTable(decimals);
			}
		
			
		} else {
			printHelp();
		}
	}
	
	public static void printHelp() {
		System.out.println("Use: \n" 
				+ "\tjava -jar tableConverter.jar input.json [-noDecimals]");
	}
}
