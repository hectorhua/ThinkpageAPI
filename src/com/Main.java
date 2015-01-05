package com;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;

import com.ThinkpageAPI;
import com.fileconfig.StaticConfig;
import com.logger.NssLogger;
import com.logger.NssLogger.CfgType;
import com.table.WFTableItem;
import com.file.WriteFile;
import com.redis.WriteRedis;

/**
 * @Project: ThinkpageAPI
 * @Author: hectorhua
 * @Date: 2014
 */

public class Main {

	/**
	 * @param args
	 */
	
	private static LinkedHashMap<String, String> areaCode;
	//private static HttpCaller httpCaller;
	
	private static String areaid_list = null;
	private static String proxyFlag = null;
	private static String proxyHost = null;
	private static String proxyPort = null;
	private static String redisHost = null;
	private static int redisPort = 0;
	private static String thinkpageKey = null;
	private static int skipNum=0;
	private static String url_header = null;
	private static String url_tail = null;
	private static String lang_CN = null;
	private static String lang_EN = null;
	private static int city_num = 0;
	//private static int sleepTime=0;
	
	public static void init(String configFileName){
		
		StaticConfig sc = new StaticConfig(configFileName);
		
		try{
			System.out.println("Program parameters:");
			areaid_list = sc.getProperty("areaid_list");
			System.out.println("areaid_list = " + areaid_list );
			proxyFlag = sc.getProperty("proxyFlag");
			System.out.println("proxyFlag = " + proxyFlag );
			proxyHost = sc.getProperty("proxyHost");
			System.out.println("proxyHost = " + proxyHost );
			proxyPort = sc.getProperty("proxyPort");
			System.out.println("proxyPort = " + proxyPort );
			redisHost = sc.getProperty("redisHost");
			System.out.println("redisHost = " + redisHost );
			redisPort = Integer.parseInt(sc.getProperty("redisPort"));
			System.out.println("redisPort = " + redisPort );
			thinkpageKey = sc.getProperty("key");
			System.out.println("thinkpageKey = " + thinkpageKey );
			skipNum = Integer.parseInt(sc.getProperty("skipNum"));
			System.out.println("skipNum = " + skipNum );
			url_header = sc.getProperty("url_header");
			System.out.println("url_header = " + url_header );
			url_tail = sc.getProperty("url_tail");
			System.out.println("url_tail = " + url_tail );
			lang_CN = sc.getProperty("lang_CN");
			System.out.println("lang_CN = " + lang_CN );
			lang_EN = sc.getProperty("lang_EN");
			System.out.println("lang_EN = " + lang_EN );
			city_num = Integer.parseInt(sc.getProperty("city_num"));
			System.out.println("city_num = " + city_num );
		}catch(Exception e){
			NssLogger.error("******Read config file error!******");
		}
		
		areaCode = new LinkedHashMap<String, String>();
		initAreaCode(areaid_list);
	}
	
	private static void initAreaCode(String filename){
		File file = new File(filename);
		try {
			BufferedReader bw = new BufferedReader(new InputStreamReader(new FileInputStream(file),"gb2312"));
			String line = null;
			while((line = bw.readLine()) != null){
				String[] temp = line.split(",");
				if(temp != null  && temp.length == 3){
					String code = line.split(",")[0];
					String name = line.split(",")[2];
					areaCode.put(code, name);			
				}
			}	
			bw.close();
		} catch (IOException e) {
			NssLogger.error("******Fail to parse ThinkpageAPI area code config file!******");
			NssLogger.error(e.getMessage());
		}
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		System.out.println("Program starts.");
		long startTime=System.currentTimeMillis();   //获取开始时间
		NssLogger.init("config/log4j.cfg", CfgType.Property);
		Main.init("config/wfintegrator.cfg");
		WriteFile wf = new WriteFile("config/wfintegrator.cfg");
		
		ThinkpageAPI thinkpagetest = new ThinkpageAPI(proxyHost, proxyPort, proxyFlag, skipNum, url_header, url_tail, lang_CN, lang_EN);
		ArrayList<WFTableItem> ResultArray = new ArrayList<WFTableItem> ();
		Iterator<String> itor = areaCode.keySet().iterator(); 
		
		String areaID="";
		String areaName_en="";
		
		System.out.println("Program running......");
		int temp=0;
		WriteRedis.getInstance().setPara(redisHost, redisPort);
		while(itor.hasNext()&&temp<city_num){
			areaID = (String)itor.next();
			areaName_en = areaCode.get(areaID).toString();
			//NssLogger.info(areaID+"/"+areaName_en);
			ResultArray = thinkpagetest.getWFItemList(areaID, areaName_en, thinkpageKey);
			if(!ResultArray.isEmpty()&&!ResultArray.get(0).getCity_name().equals("")){
				wf.recordItemList(ResultArray);
				WriteRedis.getInstance().writeTORedis(ResultArray);
			}else{
				NssLogger.error("******ID:"+areaID+" get data failed.******");
			}
			temp++;
		}
		System.out.println("Program end.");
		System.out.println("Get data: "+temp+" items.");
		long endTime=System.currentTimeMillis(); //获取结束时间
		System.out.println("Time consuming: "+(endTime-startTime)+"ms.");
	}

}