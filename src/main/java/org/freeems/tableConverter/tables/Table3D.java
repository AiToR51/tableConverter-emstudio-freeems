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
	public String printFreeEMSTable(boolean decimals) {
		int maxSize = getMaxSize(decimals) + 1; 
		for (int i = data.size() -1 ; i >= 0; i--) {
			LinkedList<String> row = data.get(i);
			for (int j = 0; j < row.size(); j++) {
				String value;
				if (!decimals) {
					value = truncateDecimals(row.get(j));
				} else {
					value = row.get(j);
				}
				for (int s = 0 ; s < maxSize - value.length(); s++) {
					System.out.print(" ");
				}
				if ( i == 0 && j == (row.size() - 1)) {
					System.out.print(this.getTableType().getDataType() + "(" + value + ") ");
				} else {
					System.out.print(this.getTableType().getDataType() + "(" + value + "),");
				}
			}
			System.out.println(" // " + truncateDecimals(this.getAxle(Constants.AXLE_Y).get(i)));
		}
		System.out.print("//");
		for (int v = 0; v < this.getAxle(Constants.AXLE_X).size(); v++) {
			String value = this.getAxle(Constants.AXLE_X).get(v);
			value = value.replaceAll("[.]0+", "");
			for (int s = 0 ; s < this.getTableType().getDataType().length() + (v > 0?2:0) + maxSize - value.length(); s++) {
				System.out.print(" ");
			}
			if (v < this.getAxle(Constants.AXLE_X).size() - 1) {
				System.out.print(value + ",");
			} else {
				System.out.print(value);
			}
		}
		System.out.println();
		return ""; //future version could return the String of all data to save it to a file
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
		return number.replaceAll("[.]0+", "");
	}
}
