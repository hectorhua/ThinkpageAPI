package com;

import java.util.ArrayList;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.http.HttpCaller;
import com.logger.NssLogger;
import com.table.WFTableItem;

/**
 * @Project: ThinkpageAPI
 * @Author: hectorhua
 * @Date: 2014
 */

public class ThinkpageAPI {

	/**
	 * @param args
	 */
	
	private static HttpCaller httpCaller;
	private static int skipNum=0;
	private static String lang_CN;
	private static String lang_EN;
	private static String url_Header;
	private static String url_Tail;
	
	public ThinkpageAPI(String proxyHost, String proxyPort, String proxyFlag, int skip, String url_header, String url_tail, String lang_cn, String lang_en){
		httpCaller = new HttpCaller(proxyHost, proxyPort, Boolean.parseBoolean(proxyFlag));
		skipNum=skip;
		lang_CN = lang_cn;
		lang_EN = lang_en;
		url_Header = url_header;
		url_Tail = url_tail;
	}
	
	public String getContent(String areaID,String key, String lang) throws Exception{
		
		String result = null;
		if(areaID != null){
			String url = url_Header+areaID+"&language="+lang+url_Tail+key;
			int flag=0;
			do{
				result = httpCaller.getURLContent(url,"UTF-8");
				flag++;
			} while(result.equals("-1")&&flag<skipNum);
		}
		return result;
	}	
	
	public ArrayList<WFTableItem> getWFItemList(String areaID, String areaName_en, String key) throws Exception{
		ArrayList<WFTableItem>  list = new ArrayList<WFTableItem> ();
		list.clear();
		JSONObject msgJson = null;
		WFTableItem wfitem = new WFTableItem();
		wfitem.setCity_ID(areaID.toString());
		wfitem.setCity_name_en(areaName_en.toString());
		
        //以下是获取中文thinkpage数据
		String thinkpage_All = getContent(areaID, key, lang_CN);
		if(thinkpage_All == null){
			NssLogger.error("******"+areaID+" No Data!******");
			list.add(wfitem);
			return list;
		}else{
			try{
				msgJson = JSON.parseObject(thinkpage_All);
				if (msgJson.getString("status").equals("OK")){
					JSONObject Jthinkpage_weather = msgJson.getJSONArray("weather").getJSONObject(0);
					wfitem.setCity_name (Jthinkpage_weather.getString("city_name").toString());
					wfitem.setCity_name_weather ((Jthinkpage_weather.getString("city_name")+"地区天气预报:").toString());
					wfitem.setCity_name_en_weather ("WEATHER FORECAST FOR "+areaName_en+":");
					wfitem.setObserve_time (("更新时间:"+Jthinkpage_weather.getString("last_update")).toString());
					
					//实时气象
					JSONObject Jthinkpage_weather_now = Jthinkpage_weather.getJSONObject("now");
					wfitem.setCurrent_status ("当前气象："+Jthinkpage_weather_now.getString("text"));
					wfitem.setCurrent_temperature (("气温"+Jthinkpage_weather_now.getString("temperature")+"C").toString());
					wfitem.setCurrent_humidity (("湿度"+Jthinkpage_weather_now.getString("humidity")+"%").toString());
					wfitem.setCurrent_wind_power ((Jthinkpage_weather_now.getString("wind_scale")+"级").toString());
					wfitem.setCurrent_wind_direction ((Jthinkpage_weather_now.getString("wind_direction")+"风").toString());
					wfitem.setFeel_temperature (("体感"+Jthinkpage_weather_now.getString("feels_like")+"C").toString());
					wfitem.setVisibility (("能见度"+Jthinkpage_weather_now.getString("visibility")+"公里").toString());
					wfitem.setAtmospheric (("气压"+Jthinkpage_weather_now.getString("pressure")+"hpa").toString());
					wfitem.setObserve_all (("实时气象:"+Jthinkpage_weather_now.getString("text")+" "+
							("气温:"+Jthinkpage_weather_now.getString("temperature")+"C").toString()+" "+
							("体感:"+Jthinkpage_weather_now.getString("feels_like")+"C").toString()+" "+
							("湿度:"+Jthinkpage_weather_now.getString("humidity")+"%").toString()+" "+
							(Jthinkpage_weather_now.getString("wind_direction")+"风").toString()+" "+
							(Jthinkpage_weather_now.getString("wind_scale")+"级").toString()+" "+
							("能见度:"+Jthinkpage_weather_now.getString("visibility")).toString()+"公里 "+
							("气压:"+Jthinkpage_weather_now.getString("pressure")).toString()+"hpa "+
							("更新时间:"+Jthinkpage_weather.getString("last_update")+" ").toString()
							).toString());
					
					//未来天气
					JSONArray Jthinkpage_weather_future = Jthinkpage_weather.getJSONArray("future");
					for(int i=0; i<7; i++){
						wfitem.setForecast_date (""+Jthinkpage_weather_future.getJSONObject(i).getString("date"), i);
						wfitem.setForecast_week (""+Jthinkpage_weather_future.getJSONObject(i).getString("day"), i);
						wfitem.setStatus_day_night (""+Jthinkpage_weather_future.getJSONObject(i).getString("text"), i);
						wfitem.setWind_direction_power (""+Jthinkpage_weather_future.getJSONObject(i).getString("wind"), i);
						wfitem.setTemperature_high (("最高气温:"+Jthinkpage_weather_future.getJSONObject(i).getString("high")+"C").toString(), i);
						wfitem.setTemperature_low (("最低气温:"+Jthinkpage_weather_future.getJSONObject(i).getString("low")+"C").toString(), i);
						wfitem.setForecast_all ((Jthinkpage_weather_future.getJSONObject(i).getString("date")+
								Jthinkpage_weather_future.getJSONObject(i).getString("day")+":"+
								Jthinkpage_weather_future.getJSONObject(i).getString("text")+" "+
								Jthinkpage_weather_future.getJSONObject(i).getString("wind")+" "+
								"最高气温:"+Jthinkpage_weather_future.getJSONObject(i).getString("high")+"C"+" "+
								"最低气温:"+Jthinkpage_weather_future.getJSONObject(i).getString("low")+"C").toString(), i);
			        }
					
					//生活指数
					JSONObject Jthinkpage_weather_today = Jthinkpage_weather.getJSONObject("today");
					JSONObject Jthinkpage_weather_today_suggestion = Jthinkpage_weather_today.getJSONObject("suggestion");
					wfitem.setDressing_brief (""+Jthinkpage_weather_today_suggestion.getJSONObject("dressing").getString("brief"));
					wfitem.setDressing_details (""+Jthinkpage_weather_today_suggestion.getJSONObject("dressing").getString("details"));
					wfitem.setUltraviolet_brief (""+Jthinkpage_weather_today_suggestion.getJSONObject("uv").getString("brief"));
					wfitem.setUltraviolet_details (""+Jthinkpage_weather_today_suggestion.getJSONObject("uv").getString("details"));
					wfitem.setCar_washing_brief (""+Jthinkpage_weather_today_suggestion.getJSONObject("car_washing").getString("brief"));
					wfitem.setCar_washing_details (""+Jthinkpage_weather_today_suggestion.getJSONObject("car_washing").getString("details"));
					wfitem.setTravel_brief (""+Jthinkpage_weather_today_suggestion.getJSONObject("travel").getString("brief"));
					wfitem.setTravel_details (""+Jthinkpage_weather_today_suggestion.getJSONObject("travel").getString("details"));
					wfitem.setFlu_brief (""+Jthinkpage_weather_today_suggestion.getJSONObject("flu").getString("brief"));
					wfitem.setFlu_details (""+Jthinkpage_weather_today_suggestion.getJSONObject("flu").getString("details"));
					wfitem.setSport_brief (""+Jthinkpage_weather_today_suggestion.getJSONObject("sport").getString("brief"));
					wfitem.setSport_details (""+Jthinkpage_weather_today_suggestion.getJSONObject("sport").getString("details"));
					wfitem.setIndex_all ((wfitem.getDressing_index()+
							Jthinkpage_weather_today_suggestion.getJSONObject("dressing").getString("brief")+" "+
							//Jthinkpage_weather_today_suggestion.getJSONObject("dressing").getString("details")+" "+
							wfitem.getUltraviolet_index()+
							Jthinkpage_weather_today_suggestion.getJSONObject("uv").getString("brief")+" "+
							//Jthinkpage_weather_today_suggestion.getJSONObject("uv").getString("details")+" "+
							wfitem.getCar_washing_index()+
							Jthinkpage_weather_today_suggestion.getJSONObject("car_washing").getString("brief")+" "+
							//Jthinkpage_weather_today_suggestion.getJSONObject("car_washing").getString("details")+" "+
							wfitem.getTravel_index()+
							Jthinkpage_weather_today_suggestion.getJSONObject("travel").getString("brief")+" "+
							//Jthinkpage_weather_today_suggestion.getJSONObject("travel").getString("details")+" "+
							wfitem.getFlu_index()+
							Jthinkpage_weather_today_suggestion.getJSONObject("flu").getString("brief")+" "+
							//Jthinkpage_weather_today_suggestion.getJSONObject("flu").getString("details")+" "+
							wfitem.getSport_index()+
							Jthinkpage_weather_today_suggestion.getJSONObject("sport").getString("brief")+" "
							//Jthinkpage_weather_today_suggestion.getJSONObject("sport").getString("details")+
							).toString());
			        
					//空气质量
					JSONObject Jthinkpage_weather_now_air_quality_city = Jthinkpage_weather.getJSONObject("now").getJSONObject("air_quality").getJSONObject("city");
					wfitem.setAQI (("AQI:"+Jthinkpage_weather_now_air_quality_city.getString("aqi")).toString());
					wfitem.setPM25 (("PM25:"+Jthinkpage_weather_now_air_quality_city.getString("pm25")).toString());
					wfitem.setPM10 (("PM10:"+Jthinkpage_weather_now_air_quality_city.getString("pm10")).toString());
					wfitem.setSO2 (("SO2:"+Jthinkpage_weather_now_air_quality_city.getString("so2")).toString());
					wfitem.setNO2 (("NO2:"+Jthinkpage_weather_now_air_quality_city.getString("no2")).toString());
					wfitem.setCO (("CO:"+Jthinkpage_weather_now_air_quality_city.getString("co")).toString());
					wfitem.setO3 (("O3:"+Jthinkpage_weather_now_air_quality_city.getString("o3")).toString());
					wfitem.setAir_quality (("空气质量:"+Jthinkpage_weather_now_air_quality_city.getString("quality")).toString());
					wfitem.setAir_last_update (("空气质量更新时间:"+Jthinkpage_weather_now_air_quality_city.getString("last_update")).toString());
					wfitem.setAir_all (("空气质量:"+Jthinkpage_weather_now_air_quality_city.getString("quality")+" "+
							"AQI:"+Jthinkpage_weather_now_air_quality_city.getString("aqi")+" "+
							"PM25:"+Jthinkpage_weather_now_air_quality_city.getString("pm25")+" "+
							"PM10:"+Jthinkpage_weather_now_air_quality_city.getString("pm10")+" "+
							"SO2:"+Jthinkpage_weather_now_air_quality_city.getString("so2")+" "+
							"NO2:"+Jthinkpage_weather_now_air_quality_city.getString("no2")+" "+
							"CO:"+Jthinkpage_weather_now_air_quality_city.getString("co")+" "+
							"O3:"+Jthinkpage_weather_now_air_quality_city.getString("o3")+" "
							//"空气质量更新时间:"+Jthinkpage_weather_now_air_quality_city.getString("last_update")+" "
							).toString());
					
					Jthinkpage_weather_now_air_quality_city.clear(); 
					Jthinkpage_weather_today_suggestion.clear();
					Jthinkpage_weather_now.clear();
					Jthinkpage_weather_today.clear();
					Jthinkpage_weather_future.clear();
					Jthinkpage_weather.clear();
					
					msgJson.clear();
				}else{
					NssLogger.error("******"+areaID+"Status not OK!******");
				}
		    	
			} catch(NullPointerException e_null){
				//NssLogger.warn("******"+areaID+" JSON NullPointerException******");
			}
			catch(Exception e){
				NssLogger.error(e.getMessage());
		    } 
		}
		//对应英文来一遍，以下是获取中文thinkpage数据
		String thinkpage_All_EN = getContent(areaID, key, lang_EN);
		if(thinkpage_All_EN == null){
			NssLogger.error("******"+areaID+" No Data!******");
			list.add(wfitem);
			return list;
		}else{
			try{
				msgJson = JSON.parseObject(thinkpage_All_EN);
				if (msgJson.getString("status").equals("OK")){
					JSONObject Jthinkpage_weather = msgJson.getJSONArray("weather").getJSONObject(0);
					
					//实时气象
					JSONObject Jthinkpage_weather_now = Jthinkpage_weather.getJSONObject("now");
					wfitem.setCurrent_status_en (Jthinkpage_weather_now.getString("text"));
					wfitem.setCurrent_temperature_en (("Temp:"+Jthinkpage_weather_now.getString("temperature")+"C").toString());
					wfitem.setCurrent_humidity_en (("Humidity:"+Jthinkpage_weather_now.getString("humidity")+"%").toString());
					wfitem.setCurrent_wind_power_en ((Jthinkpage_weather_now.getString("wind_scale")+"scale").toString());
					wfitem.setCurrent_wind_direction_en (("Wind:"+Jthinkpage_weather_now.getString("wind_direction")).toString());
					wfitem.setFeel_temperature_en (("Feel:"+Jthinkpage_weather_now.getString("feels_like")+"C").toString());
					wfitem.setVisibility_en (("Visibility:"+Jthinkpage_weather_now.getString("visibility")+"Km").toString());
					wfitem.setAtmospheric_en (("Pressure:"+Jthinkpage_weather_now.getString("pressure")).toString());
					wfitem.setObserve_all_en (("Now:"+Jthinkpage_weather_now.getString("text")+" "+"Temp:"+Jthinkpage_weather_now.getString("temperature")+"C"+" "+
							"Feel:"+Jthinkpage_weather_now.getString("feels_like")+"C"+" "+"Humidity:"+Jthinkpage_weather_now.getString("humidity")+"%"+" "+
							"Wind:"+Jthinkpage_weather_now.getString("wind_direction")+" "+
							Jthinkpage_weather_now.getString("wind_scale")+"scale"+" "+
							"Visibility:"+Jthinkpage_weather_now.getString("visibility")+"Km"+" "+"Pressure:"+Jthinkpage_weather_now.getString("pressure")+" "+
							Jthinkpage_weather.getString("last_update")+";").toString());
					
					//未来天气
					JSONArray Jthinkpage_weather_future = Jthinkpage_weather.getJSONArray("future");
					for(int i=0; i<7; i++){
						wfitem.setForecast_date_en (""+Jthinkpage_weather_future.getJSONObject(i).getString("date"), i);
						wfitem.setForecast_week_en (""+Jthinkpage_weather_future.getJSONObject(i).getString("day"), i);
						wfitem.setStatus_day_night_en (""+Jthinkpage_weather_future.getJSONObject(i).getString("text"), i);
						wfitem.setWind_direction_power_en (""+Jthinkpage_weather_future.getJSONObject(i).getString("wind"), i);
						wfitem.setTemperature_high_en (("High:"+Jthinkpage_weather_future.getJSONObject(i).getString("high")+"C").toString(), i);
						wfitem.setTemperature_low_en (("Low:"+Jthinkpage_weather_future.getJSONObject(i).getString("low")+"C").toString(), i);
						wfitem.setForecast_all_en ((Jthinkpage_weather_future.getJSONObject(i).getString("date")+
								Jthinkpage_weather_future.getJSONObject(i).getString("day")+":"+
								Jthinkpage_weather_future.getJSONObject(i).getString("text")+","+
								Jthinkpage_weather_future.getJSONObject(i).getString("wind")+","+
								"High:"+Jthinkpage_weather_future.getJSONObject(i).getString("high")+"C"+","+
								"Low:"+Jthinkpage_weather_future.getJSONObject(i).getString("low")+"C").toString(), i);
			        }
					
					//生活指数
					JSONObject Jthinkpage_weather_today = Jthinkpage_weather.getJSONObject("today");
					JSONObject Jthinkpage_weather_today_suggestion = Jthinkpage_weather_today.getJSONObject("suggestion");
					wfitem.setDressing_brief_en (""+Jthinkpage_weather_today_suggestion.getJSONObject("dressing").getString("brief"));
					wfitem.setDressing_details_en (""+Jthinkpage_weather_today_suggestion.getJSONObject("dressing").getString("details"));
					wfitem.setUltraviolet_brief_en (""+Jthinkpage_weather_today_suggestion.getJSONObject("uv").getString("brief"));
					wfitem.setUltraviolet_details_en (""+Jthinkpage_weather_today_suggestion.getJSONObject("uv").getString("details"));
					wfitem.setCar_washing_brief_en (""+Jthinkpage_weather_today_suggestion.getJSONObject("car_washing").getString("brief"));
					wfitem.setCar_washing_details_en (""+Jthinkpage_weather_today_suggestion.getJSONObject("car_washing").getString("details"));
					wfitem.setTravel_brief_en (""+Jthinkpage_weather_today_suggestion.getJSONObject("travel").getString("brief"));
					wfitem.setTravel_details_en (""+Jthinkpage_weather_today_suggestion.getJSONObject("travel").getString("details"));
					wfitem.setFlu_brief_en (""+Jthinkpage_weather_today_suggestion.getJSONObject("flu").getString("brief"));
					wfitem.setFlu_details_en (""+Jthinkpage_weather_today_suggestion.getJSONObject("flu").getString("details"));
					wfitem.setSport_brief_en (""+Jthinkpage_weather_today_suggestion.getJSONObject("sport").getString("brief"));
					wfitem.setSport_details_en (""+Jthinkpage_weather_today_suggestion.getJSONObject("sport").getString("details"));
					wfitem.setIndex_all_en ((wfitem.getDressing_index_en()+
							Jthinkpage_weather_today_suggestion.getJSONObject("dressing").getString("brief")+","+
							Jthinkpage_weather_today_suggestion.getJSONObject("dressing").getString("details")+" "+
							wfitem.getUltraviolet_index_en()+
							Jthinkpage_weather_today_suggestion.getJSONObject("uv").getString("brief")+","+
							Jthinkpage_weather_today_suggestion.getJSONObject("uv").getString("details")+" "+
							wfitem.getCar_washing_index_en()+
							Jthinkpage_weather_today_suggestion.getJSONObject("car_washing").getString("brief")+","+
							Jthinkpage_weather_today_suggestion.getJSONObject("car_washing").getString("details")+" "+
							wfitem.getTravel_index_en()+
							Jthinkpage_weather_today_suggestion.getJSONObject("travel").getString("brief")+","+
							Jthinkpage_weather_today_suggestion.getJSONObject("travel").getString("details")+" "+
							wfitem.getFlu_index_en()+
							Jthinkpage_weather_today_suggestion.getJSONObject("flu").getString("brief")+","+
							Jthinkpage_weather_today_suggestion.getJSONObject("flu").getString("details")+" "+
							wfitem.getSport_index_en()+
							Jthinkpage_weather_today_suggestion.getJSONObject("sport").getString("brief")+","+
							Jthinkpage_weather_today_suggestion.getJSONObject("sport").getString("details")+" ").toString());
			        
					//空气质量
					JSONObject Jthinkpage_weather_now_air_quality_city = Jthinkpage_weather.getJSONObject("now").getJSONObject("air_quality").getJSONObject("city");
					wfitem.setAQI_en (("AQI:"+Jthinkpage_weather_now_air_quality_city.getString("aqi")).toString());
					wfitem.setPM25_en (("PM25:"+Jthinkpage_weather_now_air_quality_city.getString("pm25")).toString());
					wfitem.setPM10_en (("PM10:"+Jthinkpage_weather_now_air_quality_city.getString("pm10")).toString());
					wfitem.setSO2_en (("SO2:"+Jthinkpage_weather_now_air_quality_city.getString("so2")).toString());
					wfitem.setNO2_en (("NO2:"+Jthinkpage_weather_now_air_quality_city.getString("no2")).toString());
					wfitem.setCO_en (("CO:"+Jthinkpage_weather_now_air_quality_city.getString("co")).toString());
					wfitem.setO3_en (("O3:"+Jthinkpage_weather_now_air_quality_city.getString("o3")).toString());
					wfitem.setAir_quality_en (("Air_quality:"+Jthinkpage_weather_now_air_quality_city.getString("quality")).toString());
					wfitem.setAir_last_update_en (("Time:"+Jthinkpage_weather_now_air_quality_city.getString("last_update")).toString());
					wfitem.setAir_all_en (("Air_quality:"+Jthinkpage_weather_now_air_quality_city.getString("quality")+","+
							"AQI:"+Jthinkpage_weather_now_air_quality_city.getString("aqi")+","+
							"PM25:"+Jthinkpage_weather_now_air_quality_city.getString("pm25")+","+
							"PM10:"+Jthinkpage_weather_now_air_quality_city.getString("pm10")+","+
							"SO2:"+Jthinkpage_weather_now_air_quality_city.getString("so2")+","+
							"NO2:"+Jthinkpage_weather_now_air_quality_city.getString("no2")+","+
							"CO:"+Jthinkpage_weather_now_air_quality_city.getString("co")+","+
							"O3:"+Jthinkpage_weather_now_air_quality_city.getString("o3")+","+
							"Time:"+Jthinkpage_weather_now_air_quality_city.getString("last_update")+";").toString());
					
					Jthinkpage_weather_now_air_quality_city.clear(); 
					Jthinkpage_weather_today_suggestion.clear();
					Jthinkpage_weather_now.clear();
					Jthinkpage_weather_today.clear();
					Jthinkpage_weather_future.clear();
					Jthinkpage_weather.clear();
					
					msgJson.clear();
				}else{
					NssLogger.error("******"+areaID+"Status not OK!******");
				}
		    	
			} catch(NullPointerException e_null){
				//NssLogger.warn("******"+areaID+" JSON NullPointerException******");
			}
			catch(Exception e){
				NssLogger.error(e.getMessage());
		    } 
		}
		list.add(wfitem);
		return list;
	}
}
