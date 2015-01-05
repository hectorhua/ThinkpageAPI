package com.table;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * @Project: ThinkpageAPI
 * @Author: hectorhua
 * @Date: 2014
 */

import com.NssLogger;

public class StaticTable {
	private  static HashMap<String, String> forcastTable;
	
	private  static HashMap<String, String> weatherPhenomenaTable;
	
	private static HashMap<String, String> windDirectionTable;
	
	private static HashMap<String, String> windPowerTable;
	
	private static HashMap<String, String> cityCodeTable;
	
	private static StaticTable instance = null;
	private StaticTable(){
		cityCodeTable = new HashMap<String, String>();
		cityCodeTableInit();
		
	}
	
	public HashMap<String, String> getWeatherPhenomenaTable() {
		return weatherPhenomenaTable;
	}

	public void setWeatherPhenomenaTable(HashMap<String, String> weatherPhenomenaTable) {
		StaticTable.weatherPhenomenaTable = weatherPhenomenaTable;
	}

	public void setForcastTable(HashMap<String, String> forcastTable) {
		StaticTable.forcastTable = forcastTable;
	}

	public HashMap<String, String> getForcastTable(){
		return forcastTable;
	}
	
	public HashMap<String, String> getWindDirectionTable() {
		return windDirectionTable;
	}

	public void setWindDirectionTable(HashMap<String, String> windDirectionTable) {
		StaticTable.windDirectionTable = windDirectionTable;
	}

	public HashMap<String, String> getWindPowerTable() {
		return windPowerTable;
	}

	public void setWindPowerTable(HashMap<String, String> windPowerTable) {
		StaticTable.windPowerTable = windPowerTable;
	}
	
	public static StaticTable getInstance(){
		if(instance == null){
			instance = new StaticTable();
		}
		return instance;
	}
	
	private static void cityCodeTableInit(){
		File file = new File("config/areaid_list.csv");
		try {
			BufferedReader bw = new BufferedReader(new FileReader(file));
			String line = null;
			while((line = bw.readLine()) != null){
				String[] temp = line.split(",");
				if(temp != null  && temp.length == 2){
					String name = line.split(",")[1];				
					String code = line.split(",")[0];
					cityCodeTable.put(code, name);			
					//NssLogger.debug(name+":"+code);
				}
			}	
			bw.close();
		} catch (IOException e) {
			NssLogger.error("Fail to parse SmartWeatherApi area code config file!");
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	//public static void main(String[] args){
		//StaticTable.getInstance();
	//}
}
