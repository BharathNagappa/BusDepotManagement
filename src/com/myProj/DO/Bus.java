package com.myProj.DO;

public class Bus {
	

long busId;
String busName;
int age;
String modelYear;
long driverID;
boolean isOutForService;

/*public Bus(String busName, int age, String modelYear,
		long driverID, boolean isOutForService) {
	super();
	this.busId = busId;
	this.busName = busName;
	this.age = age;
	this.modelYear = modelYear;
	this.driverID = driverID;
	this.isOutForService = isOutForService;
}*/

public long getBusId() {
	return busId;
}
public void setBusId(long busId) {
	this.busId = busId;
}
public String getBusName() {
	return busName;
}
public void setBusName(String busName) {
	this.busName = busName;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public String getModelYear() {
	return modelYear;
}
public void setModelYear(String modelYear) {
	this.modelYear = modelYear;
}
public long getDriverID() {
	return driverID;
}
public void setDriverID(long driverID) {
	this.driverID = driverID;
}
public boolean isOutForService() {
	return isOutForService;
}
public void setOutForService(boolean isOutForService) {
	this.isOutForService = isOutForService;
}



}
