package com.myProj.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myProj.DO.Bus;
import com.myProj.util.DAOUtil;

public class BusDAO {


	static ObjectMapper mapper = new ObjectMapper();
	static String dataSource = "Bus.json";
	static String busDepotManagementDataSource = "D:\\busDepotManagementDatasource\\";

	public static long addBus(Bus bus) throws IOException
	{	
		bus.setBusId(BusDAO.getMaxBusId()+1);
		DAOUtil.writeToFile(mapper.writeValueAsString(bus),dataSource,true);

		return bus.getBusId();
	}

	public static Bus getBus(long busId) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(new File(busDepotManagementDataSource+dataSource)));
		Bus bus = null;
		while(reader.ready())
		{
			String readLine = reader.readLine();
			System.out.println(readLine);
			if(readLine!=null && !readLine.equals(""))
			{
				System.out.println(readLine);
				bus = mapper.readValue(readLine, Bus.class);
				if(bus.getBusId()==busId)
				{
					break;
				}
				else
				{
					bus = null;
				}
			}
		}
		return bus;
	}

	public static boolean removeBus(long busId) throws JsonParseException, JsonMappingException, IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(new File(busDepotManagementDataSource+dataSource)));
		Bus bus = null;
		StringBuffer data = new StringBuffer();;
		boolean isRemoved = false;
		while(reader.ready())
		{
			String readLine = reader.readLine();
			if(readLine.equals(""))
				continue;
			bus = mapper.readValue(readLine, Bus.class);
			if(bus.getBusId()!=busId)
			{
				data.append(readLine);
			}

		}
		DAOUtil.writeToFile(data.toString(),dataSource,false);

		return isRemoved;


	}

	public static boolean updateBus(Bus busToupdate) throws JsonParseException, JsonMappingException, JsonProcessingException, IOException
	{

		BufferedReader reader = new BufferedReader(new FileReader(new File(busDepotManagementDataSource+dataSource)));
		Bus bus = null;
		StringBuffer data = new StringBuffer();;
		boolean isupdated = false;
		while(reader.ready())
		{
			String readLine = reader.readLine();
			if(readLine.equals(""))
				continue;
			bus = mapper.readValue(readLine, Bus.class);
			if(bus.getBusId()!=busToupdate.getBusId())
			{
				data.append(readLine);
			}
			else
			{
				isupdated = true;

				data.append(mapper.writeValueAsString(busToupdate));
			}

		}
		DAOUtil.writeToFile(data.toString(),dataSource,false);
		return isupdated;
	}

	public static void main(String[] args) throws IOException
	{
		/*Bus bus = new Bus();
		bus.setAge(2);
		bus.setBusName("Volvo");
		bus.setModelYear("2017");
		bus.setOutForService(true);
		bus.setBusId(1);
		bus.setDriverID(67);*/
		System.out.println(BusDAO.getBus(2).getBusId());
		//BusDAO.removeBus(1);

	}

	public static long getMaxBusId() throws IOException
	{
		long maxBusId = 0;
		BufferedReader reader = new BufferedReader(new FileReader(new File(busDepotManagementDataSource+"Bus.json")));
		String data = null;
		while(reader.ready())
		{
			data = reader.readLine();
		}

		if(data!=null && !data.equals(""))
		{
			Bus bus = mapper.readValue(data, Bus.class);
			maxBusId = bus.getBusId();
		}

		return maxBusId;

	}

	public static List<Bus> getBusesOutForService() throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		List<Bus> list = new ArrayList<Bus>();
		BufferedReader reader = new BufferedReader(new FileReader(new File(busDepotManagementDataSource+dataSource)));
		Bus bus = null;
		StringBuffer data = new StringBuffer();;
		boolean isRemoved = false;
		while(reader.ready())
		{
			String readLine = reader.readLine();
			if(readLine.equals(""))
				continue;
			bus = mapper.readValue(readLine, Bus.class);
			if(bus.isOutForService())
			{
				list.add(bus);
			}

		}
		return list;
	}
}
