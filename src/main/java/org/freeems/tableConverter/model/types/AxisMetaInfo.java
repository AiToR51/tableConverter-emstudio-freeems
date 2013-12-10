package org.freeems.tableConverter.model.types;

public class AxisMetaInfo {

	private final String axisName;
	private final String axisLabel;
	private final String axisUnit;
	
	public AxisMetaInfo(String axisName, String axisLabel, String axisUnit) {
		this.axisName = axisName;
		this.axisLabel = axisLabel;
		this.axisUnit = axisUnit;
	}

	public String getAxisName() {
		return axisName;
	}

	public String getAxisLabel() {
		return axisLabel;
	}

	public String getAxisUnit() {
		return axisUnit;
	}
	
	

}
