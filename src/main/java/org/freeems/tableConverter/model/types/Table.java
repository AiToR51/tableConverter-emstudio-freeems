package org.freeems.tableConverter.model.types;

import java.util.HashMap;
import java.util.LinkedList;

public abstract class Table {

	private String title;
	private final int dimensions;
	private final TableType tableType;

	//axis name, axis values
	private HashMap<String,LinkedList<String>> axisInfo;

	private HashMap<String, AxisMetaInfo> axisMetaInfo;
	private AxisMetaInfo dataMetaInfo;

	public Table(int dimensions, TableType tableType, String title) {
		this.dimensions = dimensions;
		this.tableType = tableType;
		axisInfo = new HashMap<String,LinkedList<String>>();
		axisMetaInfo = new HashMap<String, AxisMetaInfo>();
		this.title = title;
	}


	public abstract String printFreeEMSTable(boolean decimals, int extraSpaces, boolean showMetaData);

	public int getDimensions() {
		return dimensions;
	}

	public TableType getTableType() {
		return tableType;
	}

	public void insertAxis(String axisName, LinkedList<String> values, AxisMetaInfo metaInfo) {
		this.axisInfo.put(axisName, values);
		this.axisMetaInfo.put(axisName, metaInfo);
	}

	public void setDataMetaInfo(AxisMetaInfo metaInfo) {
		this.dataMetaInfo = metaInfo;
	}

	public AxisMetaInfo getDataMetaInfo() {
		return this.dataMetaInfo;
	}

	public LinkedList<String> getAxis(String axisName) {
		if (this.axisInfo.containsKey(axisName)) {
			return this.axisInfo.get(axisName);
		} else {
			return null;
		}
	}

	public void printAxisMetaInfo() {
		for (String s : this.axisMetaInfo.keySet()) {
			System.out.println("Axis " + s);
			System.out.println("\tLabel: " + this.axisMetaInfo.get(s).getAxisLabel());
			System.out.println("\tUnit: " + this.axisMetaInfo.get(s).getAxisUnit());
		}
		System.out.println("Data");
		System.out.println("\tLabel: " + this.dataMetaInfo.getAxisLabel());
		System.out.println("\tUnit: " + this.dataMetaInfo.getAxisUnit());
	}

	public String getTitle() {
		return this.title;
	}

}
