package com.myProj.bl;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.myProj.DO.Bus;
import com.myProj.DO.Drivers;
import com.myProj.dao.BusDAO;
import com.myProj.dao.DriverDAO;

public class BusDepotManager {

	public static void addBus(int age,String busName,String modelYear,long driverId,boolean isOutforService) throws IOException
	{
		Bus bus = new Bus();
		bus.setBusName(busName);
		bus.setAge(age);
		bus.setModelYear(modelYear);
		bus.setDriverID(driverId);
		bus.setOutForService(isOutforService);
		BusDAO.addBus(bus);

	}

	public static String assignDriver(long driverId, long busId) throws JsonParseException, JsonMappingException, JsonProcessingException, IOException {
		// TODO Auto-generated method stub
		String status = driverId+" is assigned to bus "+busId;
		System.out.println(busId);
		Bus bus = BusDAO.getBus(busId);
		System.out.println(bus);
		Drivers driver = DriverDAO.getDriver(driverId);
		if(bus!=null)
		{
			bus.setDriverID(driverId);
			BusDAO.updateBus(bus);
		}
		else
		{
			status = "Requested bus "+busId+" not found to assign";
		}
		
		if(driver!=null)
		{
			driver.setBusId(busId);
			DriverDAO.updateDriver(driver);
		}
		else
		{
			status = "Requested driver "+driverId+" not found to assign";
		}
		return status;
	}

	public static void unAssignDriver(long driverId, long busId) throws IOException {
		// TODO Auto-generated method stub
		Bus bus = BusDAO.getBus(busId);
		bus.setDriverID(0);
		BusDAO.updateBus(bus);
		Drivers driver = DriverDAO.getDriver(driverId);
		driver.setBusId(0);
		DriverDAO.updateDriver(driver);

	}

	public static void removeBus(long busId) throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		BusDAO.removeBus(busId);

	}

	public static void removeDriver(long driverId) throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		DriverDAO.removeDriver(driverId);		
	}

	public static List<Bus> getListofBusesOutOfService() throws JsonParseException, JsonMappingException, IOException
	{
		return BusDAO.getBusesOutForService();
	}

	public static void addDriver(int age,long busId,String driverName) throws IOException {
		// TODO Auto-generated method stub
		Drivers driver = new Drivers();
		driver.setAge(age);
		driver.setBusId(busId);
		driver.setDriverName(driverName);
		DriverDAO.addDriver(driver);
	}

}
