package org.freeems.tableConverter.model.types;

public enum TableType {

	VE_TABLE(1, "VE"),
	TIMING_TABLE(2, "IT"),
	LAMBDA_TABLE(3, "LR");
	
	private final int id;
	private final String dataType;
	
	private TableType(final int id, final String dataType) {
		this.id = id;
		this.dataType = dataType;
	}

	public int getId() {
		return id;
	}

	public String getDataType() {
		return dataType;
	}
}
