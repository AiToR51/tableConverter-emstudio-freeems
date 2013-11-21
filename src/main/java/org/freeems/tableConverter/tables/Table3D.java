package org.freeems.tableConverter.tables;

import java.util.LinkedList;

import org.freeems.tableConverter.model.types.Constants;
import org.freeems.tableConverter.model.types.Table;
import org.freeems.tableConverter.model.types.TableType;

public class Table3D extends Table {

	final private LinkedList<LinkedList<String>> data;
	public Table3D(TableType tableType, LinkedList<LinkedList<String>> data) {
		super(2, tableType);
		this.data = data;
	}

	@Override
	public String printFreeEMSTable(boolean decimals, int extraSpaces) {
		int maxSize = getMaxSize(decimals) + 2 + extraSpaces; 
//		int maxSize = this.getTableType().getBlockSize();
		printAxis(maxSize, true);
		for (int i = data.size() -1 ; i >= 0; i--) {
			LinkedList<String> row = data.get(i);
			for (int j = 0; j < row.size(); j++) {
				String value;
				if (!decimals) {
					value = truncateDecimals(row.get(j));
				} else {
					value = row.get(j);
				}
				//value lenght + () + data type
				int usedLength = value.length() + 2;
				for (int s = 0 ; s < maxSize - usedLength; s++) {
					System.out.print(" ");
				}
				if ( i == 0 && j == (row.size() - 1)) {
					System.out.print(this.getTableType().getDataType() + "(" + value + ") ");
				} else {
					System.out.print(this.getTableType().getDataType() + "(" + value + "),");
				}
			}
			
			System.out.print(" //");
			String yAxisValue = truncateDecimals(this.getAxis(Constants.AXIS_Y).get(i));
			for (int y = 0; y < 4 - yAxisValue.length(); y++) {
				System.out.print(" ");
			}
			System.out.println(yAxisValue + "kPa");
		}
		printAxis(maxSize, false);
		return ""; //future version could return the String of all data to save it to a file
	}
	
	private void printAxis(int maxSize, boolean top) {
		System.out.print("//");
		for (int v = 0; v < this.getAxis(Constants.AXIS_X).size(); v++) {
			String value = this.getAxis(Constants.AXIS_X).get(v);
			value = value.replaceAll("[.]0+", "");
			//(v > 0?0:-2) is to help in first print with comment.
			for (int s = 0 ; s < (v > 0?0:-2) + maxSize - value.length() +2 ; s++) {
				System.out.print(" ");
			}
			if (v < this.getAxis(Constants.AXIS_X).size() - 1) {
				System.out.print(value + ",");
			} else {
				System.out.print(value);
			}
		}
		String end = top? "Vacuum":"Boost";
		System.out.println("  // " + end);
	}

	private int getMaxSize(boolean decimals) {
		int maxSize = 0;
		for (LinkedList<String> row : this.data) {
			for (String value : row) {
				int length = decimals? value.length(): truncateDecimals(value).length();
				if (length > maxSize) {
					maxSize = length;
				}
			}
		}
		return maxSize;
	}
	
	private String truncateDecimals(String number) {
		return number.replaceAll("[.][0-9]+", "");
	}
}
