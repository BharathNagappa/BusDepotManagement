package com.myProj.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.fasterxml.jackson.core.JsonProcessingException;

public class DAOUtil {
	
	static String busDepotManagementDataSource = "D:\\busDepotManagementDatasource\\";

	public static void writeToFile(String dataString,String dataSource,boolean append) throws IOException,
	JsonProcessingException {
		
		File file = new File(busDepotManagementDataSource+dataSource);
		if(!file.exists())file.createNewFile();
		FileWriter writer = new FileWriter(file,append);
		PrintWriter printWriter = new PrintWriter(writer);
		printWriter.println(dataString);
		printWriter.close();
		
	}

}
