package org.freeems.tableConverter.model.types;

public enum TableType {

	VE_TABLE(1, "VE",7),
	TIMING_TABLE(2, "IT",7),
	LAMBDA_TABLE(3, "LR",7);
	
	private final int id;
	private final String dataType;
	private final int blockSize;
	
	private TableType(final int id, final String dataType,final int blockSize) {
		this.id = id;
		this.dataType = dataType;
		this.blockSize = blockSize;
	}

	public int getId() {
		return id;
	}

	public String getDataType() {
		return dataType;
	}
	
	public int getBlockSize() {
		return blockSize;
	}
}
