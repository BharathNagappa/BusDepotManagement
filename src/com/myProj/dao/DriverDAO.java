package com.myProj.dao;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myProj.DO.Drivers;
import com.myProj.util.DAOUtil;

public class DriverDAO {




	static ObjectMapper mapper = new ObjectMapper();
	static String dataSource = "Driver.json";
	static String busDepotManagementDataSource = "D:\\busDepotManagementDatasource\\";

	public static long addDriver(Drivers drivers) throws IOException
	{	
		drivers.setDriverId(DriverDAO.getMaxDriversId()+1);
		drivers.setCreatedDate(new Date());
		DAOUtil.writeToFile(mapper.writeValueAsString(drivers),dataSource,true);

		return drivers.getBusId();
	}

	public static Drivers getDriver(long driversId) throws JsonParseException, JsonMappingException, IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(new File(busDepotManagementDataSource+dataSource)));
		Drivers drivers = null;
		while(reader.ready())
		{
			String readLine = reader.readLine();
			System.out.println(readLine);
			if(readLine==null || readLine.equals(""))
			{			
				continue;
			}
			drivers = mapper.readValue(readLine, Drivers.class);
			if(drivers.getDriverId()==driversId)
			{
				break;
			}
			else
			{
				drivers = null;
			}
		}
		return drivers;
	}

	public static boolean removeDriver(long driverId) throws JsonParseException, JsonMappingException, IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(new File(busDepotManagementDataSource+dataSource)));
		Drivers driver = null;
		StringBuffer data = new StringBuffer();;
		boolean isRemoved = false;
		while(reader.ready())
		{
			String readLine = reader.readLine();
			if(readLine.equals(""))
				continue;
			driver = mapper.readValue(readLine, Drivers.class);
			if(driver.getBusId()!=driverId)
			{
				data.append(readLine);
			}

		}
		DAOUtil.writeToFile(data.toString(),dataSource,false);

		return isRemoved;


	}

	public static boolean updateDriver(Drivers driverToupdate) throws JsonParseException, JsonMappingException, JsonProcessingException, IOException
	{

		BufferedReader reader = new BufferedReader(new FileReader(new File(busDepotManagementDataSource+dataSource)));
		Drivers driver = null;
		StringBuffer data = new StringBuffer();;
		boolean isupdated = false;
		while(reader.ready())
		{
			String readLine = reader.readLine();
			if(readLine.equals(""))
				continue;
			driver = mapper.readValue(readLine, Drivers.class);
			if(driver.getDriverId()!=driverToupdate.getDriverId())
			{
				data.append(readLine+"\n");
			}
			else
			{
				isupdated = true;
				driverToupdate.setCreatedDate(driver.getCreatedDate());
				data.append(mapper.writeValueAsString(driverToupdate)+"\n");
			}

		}
		DAOUtil.writeToFile(data.toString(),dataSource,false);
		return isupdated;
	}

	/*public static void main(String[] args) throws IOException
	{
		Drivers bus = new Drivers();
		bus.setAge(2);
		bus.setDriverName("Govinda");
		bus.setBusId(8);
		bus.setDriverId(9);
	
		//DriverDAO.addDriver(bus);
		DriverDAO.updateDriver(bus);
		//BusDAO.removeBus(1);

	}
*/
	public static long getMaxDriversId() throws IOException
	{
		long maxBusId = 0;
		BufferedReader reader = new BufferedReader(new FileReader(new File(busDepotManagementDataSource+"Driver.json")));
		String data = null;
		while(reader.ready())
		{
			data = reader.readLine();
		}

		if(data!=null && !data.equals(""))
		{
			Drivers bus = mapper.readValue(data, Drivers.class);
			maxBusId = bus.getDriverId();
		}

		return maxBusId;

	}
}


