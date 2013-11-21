package org.freeems.tableConverter.model.types;

import java.util.HashMap;
import java.util.LinkedList;

public abstract class Table {

	private final int dimensions;
	private final TableType tableType;
	
	//axis name, axis values
	private HashMap<String,LinkedList<String>> axisInfo;
	
	public Table(int dimensions, TableType tableType) {
		this.dimensions = dimensions;
		this.tableType = tableType;
		axisInfo = new HashMap<String,LinkedList<String>>();
	}
	
	public abstract String printFreeEMSTable(boolean decimals, int extraSpaces);

	public int getDimensions() {
		return dimensions;
	}

	public TableType getTableType() {
		return tableType;
	}
	
	public void insertAxis(String axisName, LinkedList<String> values) {
		this.axisInfo.put(axisName, values);
	}
	
	public LinkedList<String> getAxis(String axisName) {
		if (this.axisInfo.containsKey(axisName)) {
			return this.axisInfo.get(axisName);
		} else {
			return null;
		}
	}
	
}
