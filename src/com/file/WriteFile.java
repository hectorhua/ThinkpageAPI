package com.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.logger.NssLogger;
import com.table.WFTableItem;
import com.fileconfig.StaticConfig;

/**
 * @Project: ThinkpageAPI
 * @Author: hectorhua
 * @Date: 2014
 */

public class WriteFile {
	private ArrayList<WFTableItem> list;
	private String cfgFileName;
	private static String dumpFileName = "";
	
	public WriteFile(String cfgFileName){
		this.cfgFileName = cfgFileName;
		
		list = new ArrayList<WFTableItem>();
		dumpFileName = getDumpFileName();
		try{
			File file = new File(dumpFileName);
			if(file.exists()){
				file.delete();
			}
		} catch(Exception e){
			
		}
		
	}
	
	public String getDumpFileName(){
		String result = null;
		StaticConfig sc = new StaticConfig(cfgFileName);
		
		try{
			result = sc.getProperty("dumpFileDir");
			//System.out.println("dumpFileDir = " + result );
		
		}catch(Exception e){
			e.printStackTrace();
		}
		Date dt = Calendar.getInstance().getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd");
		String dateString = formatter.format(dt);
		
		return result + "/thinkpage" + "_" + dateString;
	}
	
	
	
	public void  recordItemList(ArrayList<WFTableItem> wfResult){
		
		BufferedWriter output = null;
		try{
		    
		    for(int i=0; i<wfResult.size(); i++){
		    	WFTableItem wi= wfResult.get(i);
		    	//更改编码，原来使用FileWriter如下：
		    	//output = new BufferedWriter(new FileWriter(dumpFileName[0], true));
		    	//现如下：
		    	output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dumpFileName,true), "UTF-8"));
		    	
		    	output.write(wi.getCity_ID()); 
		    	output.write(",");
		    	output.write(wi.getCity_name()); 
		    	output.write("/");
		    	output.write(wi.getCity_name_en()); 
		    	output.write(",");
		    	output.write(wi.getCity_name_weather()); 
		    	output.write("/");
		    	output.write(wi.getCity_name_en_weather()); 
		    	
		    	//observe forecast index中文
		    	output.write(wi.getObserve_all()); 
		    	output.write(wi.getAir_all()); 
		    	for (int j=0;j<7;j++){
		    		output.write(wi.getForecast_all(j));
		    		output.write(";");
		    	}
				output.write(wi.getIndex_all());
				//observe forecast index英文
		    	output.write(wi.getObserve_all_en()); 
		    	output.write(wi.getAir_all_en()); 
		    	for (int j=0;j<7;j++){
		    		output.write(wi.getForecast_all_en(j));
		    		output.write(";");
		    	}
				output.write(wi.getIndex_all_en());
				
				output.newLine();
				output.close();
		    }
		    
		}catch(Exception e){
			NssLogger.error("Fail to record weather info to file "+ dumpFileName);
			StringWriter sw = new StringWriter();
	        PrintWriter pw = new PrintWriter(sw);
	        e.printStackTrace(pw);
	        NssLogger.error(sw.toString());
		}
	}
}