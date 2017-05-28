package com.myProj.controller;
import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myProj.bl.BusDepotManager;

@RestController
public class Controller {
	
	@RequestMapping(value="/addBus",method=RequestMethod.GET)
	public void addBus(int age,String busName,String modelYear,boolean isOutforService) throws IOException
	{
		System.out.println("inside Rest");
		BusDepotManager.addBus(age, busName, modelYear, 0, isOutforService);
	}
	
	@RequestMapping(value="/addDriver",method=RequestMethod.GET)
	public void addDriver(int age,long busId,String driverName) throws IOException
	{
		System.out.println("inside Rest");
		BusDepotManager.addDriver(age, busId, driverName);
	}
	
	@RequestMapping(value="/assignBus",method=RequestMethod.GET)
	public String assignBus(long driverId,long busId) throws IOException
	{
		System.out.println("inside Rest");
		return BusDepotManager.assignDriver(driverId,busId);
	}

	@RequestMapping(value="/unassignBus",method=RequestMethod.GET)
	public void unAssignBus(long driverId,long busId) throws IOException
	{
		System.out.println("inside Rest");
		BusDepotManager.unAssignDriver(driverId,busId);
	}
	
	@RequestMapping(value="/removeBus",method=RequestMethod.GET)
	public void removeBus(long busId) throws IOException
	{
		System.out.println("inside Rest");
		BusDepotManager.removeBus(busId);
	}
	
	@RequestMapping(value="/removeDriver",method=RequestMethod.GET)
	public void removeDriver(long driverId) throws IOException
	{
		System.out.println("inside Rest");
		BusDepotManager.removeDriver(driverId);
	}
	
	@RequestMapping(value="/getBusesOutForService",method=RequestMethod.GET)
	public String getBusesOutForService() throws IOException
	{
		 ObjectMapper mapper = new ObjectMapper();
		System.out.println("inside Rest");
		return mapper.writeValueAsString(BusDepotManager.getListofBusesOutOfService());
	}
}
