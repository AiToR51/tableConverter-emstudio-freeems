package org.freeems.tableConverter.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.freeems.tableConverter.model.types.AxisMetaInfo;
import org.freeems.tableConverter.model.types.Constants;
import org.freeems.tableConverter.model.types.Table;
import org.freeems.tableConverter.model.types.TableType;
import org.freeems.tableConverter.tables.Table3D;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class EMStudioTableParser {

	private final String inputFile;

	public EMStudioTableParser(String inputFile) {
		this.inputFile = inputFile;

	}

	public Table parseFile() {
		Table table = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(inputFile));

			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append('\n');
				line = br.readLine();
			}
			String text = sb.toString();

			JSONParser parser = new JSONParser();
			ContainerFactory containerFactory = new ContainerFactory() {
				public List creatArrayContainer() {
					return new LinkedList();
				}

				public Map createObjectContainer() {
					return new LinkedHashMap();
				}

			};
			;
			try {
				Map json = (Map) parser.parse(text, containerFactory);
					if (json.containsKey(Constants.TABLE_3D)) {

						LinkedList<String> axisX = (LinkedList<String>) ((Map) json.get("X"))
								.get("values");
						AxisMetaInfo axisXMetaInfo = new AxisMetaInfo(Constants.AXIS_X,
								(String)((Map) json.get("X"))
								.get("label"), (String)((Map) json.get("X"))
								.get("unit"));
						LinkedList<String> axisY = (LinkedList<String>) ((Map) json.get("Y"))
								.get("values");
						AxisMetaInfo axisYMetaInfo = new AxisMetaInfo(Constants.AXIS_Y,
								(String)((Map) json.get("Y"))
								.get("label"), (String)((Map) json.get("Y"))
								.get("unit"));

						Map data = (Map) json.get("Z");

						AxisMetaInfo axisZMetaInfo = new AxisMetaInfo(Constants.AXIS_Z,
								(String) data.get("label"), (String) data.get("unit"));

						String unit = (String) data.get("unit");
						TableType tableType;
						if (unit.equalsIgnoreCase(Constants.TABLE_VE)) {
							tableType = TableType.VE_TABLE;
						} else if (unit.equalsIgnoreCase(Constants.TABLE_TIMING)) {
							tableType = TableType.TIMING_TABLE;
						} else if (unit.equalsIgnoreCase(Constants.TABLE_LAMBDA)) {
							tableType = TableType.LAMBDA_TABLE;
						} else if (unit.equalsIgnoreCase(Constants.TABLE_ALPHAN)) {
							tableType = TableType.ALPHAN_TABLE;
						} else {
							System.out.println("currently not supported this kind of tables");
							return null;
						}

						table = new Table3D(tableType, (String) json.get("title"), (LinkedList<LinkedList<String>>) data
								.get("values"));
						table.insertAxis(Constants.AXIS_X, axisX, axisXMetaInfo);
						table.insertAxis(Constants.AXIS_Y, axisY, axisYMetaInfo);
						table.setDataMetaInfo(axisZMetaInfo);
						return table;
					} else if (json.containsKey(Constants.TABLE_2D)) {
						System.out.println("currently not supported 2D tables");
						return null;
					}

//				Iterator iter = json.entrySet().iterator();
//				while (iter.hasNext()) {
//					Map.Entry entry = (Map.Entry) iter.next();
//					System.out.println(entry.getKey() + "=>" + entry.getValue() + "|"
//							+ entry.getKey().getClass());
//					if (entry.getKey().equals("X") || entry.getKey().equals("Y")
//							|| entry.getKey().equals("Z")) {
//						Object o = ((HashMap) entry.getValue()).get("values");
//						System.out.println(o + " " + o.getClass());
//					}
//				}
			} catch (ParseException pe) {
				System.out.println(pe);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return table;

	}
}
