package org.freeems.tableConverter.model.types;

import java.util.HashMap;
import java.util.LinkedList;

public abstract class Table {

	private final int dimensions;
	private final TableType tableType;
	
	//axle name, axle values
	private HashMap<String,LinkedList<String>> axleInfo;
	
	public Table(int dimensions, TableType tableType) {
		this.dimensions = dimensions;
		this.tableType = tableType;
		axleInfo = new HashMap<String,LinkedList<String>>();
	}
	
	public abstract String printFreeEMSTable();

	public int getDimensions() {
		return dimensions;
	}

	public TableType getTableType() {
		return tableType;
	}
	
	public void insertAxle(String axleName, LinkedList<String> values) {
		this.axleInfo.put(axleName, values);
	}
	
	public LinkedList<String> getAxle(String axleName) {
		if (this.axleInfo.containsKey(axleName)) {
			return this.axleInfo.get(axleName);
		} else {
			return null;
		}
	}
	
}
