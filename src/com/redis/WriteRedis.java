package com.redis;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.table.WFTableItem;
import com.logger.NssLogger;
import com.redis.RedisManager;

/**
 * @Project: ThinkpageAPI
 * @Author: hectorhua
 * @Date: 2014
 */

public class WriteRedis {
	
	private static WriteRedis instance=null;
	private String redisHost;
	private int redisPort;
	//Map<byte[], byte[]>  weathermap = new HashMap<byte[], byte[]>();
	Map<String, String>  weathermap = new HashMap<String, String>();
	
	public WriteRedis(){
	}
	
	public static WriteRedis getInstance(){
		if(instance==null){
			instance =new WriteRedis();
		}
		return instance;
	}
	
	public void setPara(String redisHost, int redisPort){
		this.redisHost = redisHost;
		this.redisPort = redisPort;
	}
	public void writeTORedis(ArrayList<WFTableItem> wfResult) throws UnsupportedEncodingException{
		RedisManager.getInstance().init(redisHost, redisPort, 4, 0, 0);
		WFTableItem wi= wfResult.get(0);
		
		/**GBK方式写入Redis
		Charset cs=Charset.forName("GBK");  
		CharBuffer cb = CharBuffer.wrap(wi.getCity_name());
		ByteBuffer bbf = cs.encode(cb); 
		byte[] valueByte = new byte[bbf.limit()];  
        System.arraycopy(bbf.array(), 0, valueByte, 0, bbf.limit());
		weathermap.put("city_name".getBytes(), valueByte);
		*/
		
		
		weathermap.put("city_name", wi.getCity_name());
		weathermap.put("city_name_en", wi.getCity_name_en());
		weathermap.put("city_name_weather", wi.getCity_name_weather());
		weathermap.put("city_name_en_weather", wi.getCity_name_en_weather());
		
		weathermap.put("current_status", wi.getCurrent_status());
		weathermap.put("current_temperature", wi.getCurrent_temperature());
		weathermap.put("current_humidity", wi.getCurrent_humidity());
		weathermap.put("current_wind_power", wi.getCurrent_wind_power());
		weathermap.put("current_wind_direction", wi.getCurrent_wind_direction());
		weathermap.put("feel_temperature", wi.getFeel_temperature());
		weathermap.put("visibility", wi.getVisibility());
		weathermap.put("atmospheric", wi.getAtmospheric());
		weathermap.put("observe_time", wi.getObserve_time());
		weathermap.put("observe_all", wi.getObserve_all());
		weathermap.put("current_status_en", wi.getCurrent_status_en());
		weathermap.put("current_temperature_en", wi.getCurrent_temperature_en());
		weathermap.put("current_humidity_en", wi.getCurrent_humidity_en());
		weathermap.put("current_wind_power_en", wi.getCurrent_wind_power_en());
		weathermap.put("current_wind_direction_en", wi.getCurrent_wind_direction_en());
		weathermap.put("feel_temperature_en", wi.getFeel_temperature_en());
		weathermap.put("visibility_en", wi.getVisibility_en());
		weathermap.put("atmospheric_en", wi.getAtmospheric_en());
		weathermap.put("observe_time_en", wi.getObserve_time_en());
		weathermap.put("observe_all_en", wi.getObserve_all_en());
		
		weathermap.put("forecast_name", wi.getForecast_name());
		weathermap.put("forecast_date0", wi.getForecast_date(0));
		weathermap.put("forecast_date1", wi.getForecast_date(1));
		weathermap.put("forecast_date2", wi.getForecast_date(2));
		weathermap.put("forecast_date3", wi.getForecast_date(3));
		weathermap.put("forecast_date4", wi.getForecast_date(4));
		weathermap.put("forecast_date5", wi.getForecast_date(5));
		weathermap.put("forecast_date6", wi.getForecast_date(6));
		weathermap.put("forecast_week0", wi.getForecast_week(0));
		weathermap.put("forecast_week1", wi.getForecast_week(1));
		weathermap.put("forecast_week2", wi.getForecast_week(2));
		weathermap.put("forecast_week3", wi.getForecast_week(3));
		weathermap.put("forecast_week4", wi.getForecast_week(4));
		weathermap.put("forecast_week5", wi.getForecast_week(5));
		weathermap.put("forecast_week6", wi.getForecast_week(6));
		weathermap.put("status_day_night0", wi.getStatus_day_night(0));
		weathermap.put("status_day_night1", wi.getStatus_day_night(1));
		weathermap.put("status_day_night2", wi.getStatus_day_night(2));
		weathermap.put("status_day_night3", wi.getStatus_day_night(3));
		weathermap.put("status_day_night4", wi.getStatus_day_night(4));
		weathermap.put("status_day_night5", wi.getStatus_day_night(5));
		weathermap.put("status_day_night6", wi.getStatus_day_night(6));
		weathermap.put("wind_direction_power0", wi.getWind_direction_power(0));
		weathermap.put("wind_direction_power1", wi.getWind_direction_power(1));
		weathermap.put("wind_direction_power2", wi.getWind_direction_power(2));
		weathermap.put("wind_direction_power3", wi.getWind_direction_power(3));
		weathermap.put("wind_direction_power4", wi.getWind_direction_power(4));
		weathermap.put("wind_direction_power5", wi.getWind_direction_power(5));
		weathermap.put("wind_direction_power6", wi.getWind_direction_power(6));
		weathermap.put("temperature_high0", wi.getTemperature_high(0));
		weathermap.put("temperature_high1", wi.getTemperature_high(1));
		weathermap.put("temperature_high2", wi.getTemperature_high(2));
		weathermap.put("temperature_high3", wi.getTemperature_high(3));
		weathermap.put("temperature_high4", wi.getTemperature_high(4));
		weathermap.put("temperature_high5", wi.getTemperature_high(5));
		weathermap.put("temperature_high6", wi.getTemperature_high(6));
		weathermap.put("temperature_low0", wi.getTemperature_low(0));
		weathermap.put("temperature_low1", wi.getTemperature_low(1));
		weathermap.put("temperature_low2", wi.getTemperature_low(2));
		weathermap.put("temperature_low3", wi.getTemperature_low(3));
		weathermap.put("temperature_low4", wi.getTemperature_low(4));
		weathermap.put("temperature_low5", wi.getTemperature_low(5));
		weathermap.put("temperature_low6", wi.getTemperature_low(6));
		weathermap.put("forecast_all0", wi.getForecast_all(0));
		weathermap.put("forecast_all1", wi.getForecast_all(1));
		weathermap.put("forecast_all2", wi.getForecast_all(2));
		weathermap.put("forecast_all3", wi.getForecast_all(3));
		weathermap.put("forecast_all4", wi.getForecast_all(4));
		weathermap.put("forecast_all5", wi.getForecast_all(5));
		weathermap.put("forecast_all6", wi.getForecast_all(6));
		
		weathermap.put("forecast_name_en", wi.getForecast_name_en());
		weathermap.put("forecast_date_en0", wi.getForecast_date_en(0));
		weathermap.put("forecast_date_en1", wi.getForecast_date_en(1));
		weathermap.put("forecast_date_en2", wi.getForecast_date_en(2));
		weathermap.put("forecast_date_en3", wi.getForecast_date_en(3));
		weathermap.put("forecast_date_en4", wi.getForecast_date_en(4));
		weathermap.put("forecast_date_en5", wi.getForecast_date_en(5));
		weathermap.put("forecast_date_en6", wi.getForecast_date_en(6));
		weathermap.put("forecast_week_en0", wi.getForecast_week_en(0));
		weathermap.put("forecast_week_en1", wi.getForecast_week_en(1));
		weathermap.put("forecast_week_en2", wi.getForecast_week_en(2));
		weathermap.put("forecast_week_en3", wi.getForecast_week_en(3));
		weathermap.put("forecast_week_en4", wi.getForecast_week_en(4));
		weathermap.put("forecast_week_en5", wi.getForecast_week_en(5));
		weathermap.put("forecast_week_en6", wi.getForecast_week_en(6));
		weathermap.put("status_day_night_en0", wi.getStatus_day_night_en(0));
		weathermap.put("status_day_night_en1", wi.getStatus_day_night_en(1));
		weathermap.put("status_day_night_en2", wi.getStatus_day_night_en(2));
		weathermap.put("status_day_night_en3", wi.getStatus_day_night_en(3));
		weathermap.put("status_day_night_en4", wi.getStatus_day_night_en(4));
		weathermap.put("status_day_night_en5", wi.getStatus_day_night_en(5));
		weathermap.put("status_day_night_en6", wi.getStatus_day_night_en(6));
		weathermap.put("wind_direction_power_en0", wi.getWind_direction_power_en(0));
		weathermap.put("wind_direction_power_en1", wi.getWind_direction_power_en(1));
		weathermap.put("wind_direction_power_en2", wi.getWind_direction_power_en(2));
		weathermap.put("wind_direction_power_en3", wi.getWind_direction_power_en(3));
		weathermap.put("wind_direction_power_en4", wi.getWind_direction_power_en(4));
		weathermap.put("wind_direction_power_en5", wi.getWind_direction_power_en(5));
		weathermap.put("wind_direction_power_en6", wi.getWind_direction_power_en(6));
		weathermap.put("temperature_high_en0", wi.getTemperature_high_en(0));
		weathermap.put("temperature_high_en1", wi.getTemperature_high_en(1));
		weathermap.put("temperature_high_en2", wi.getTemperature_high_en(2));
		weathermap.put("temperature_high_en3", wi.getTemperature_high_en(3));
		weathermap.put("temperature_high_en4", wi.getTemperature_high_en(4));
		weathermap.put("temperature_high_en5", wi.getTemperature_high_en(5));
		weathermap.put("temperature_high_en6", wi.getTemperature_high_en(6));
		weathermap.put("temperature_low_en0", wi.getTemperature_low_en(0));
		weathermap.put("temperature_low_en1", wi.getTemperature_low_en(1));
		weathermap.put("temperature_low_en2", wi.getTemperature_low_en(2));
		weathermap.put("temperature_low_en3", wi.getTemperature_low_en(3));
		weathermap.put("temperature_low_en4", wi.getTemperature_low_en(4));
		weathermap.put("temperature_low_en5", wi.getTemperature_low_en(5));
		weathermap.put("temperature_low_en6", wi.getTemperature_low_en(6));
		weathermap.put("forecast_all_en0", wi.getForecast_all_en(0));
		weathermap.put("forecast_all_en1", wi.getForecast_all_en(1));
		weathermap.put("forecast_all_en2", wi.getForecast_all_en(2));
		weathermap.put("forecast_all_en3", wi.getForecast_all_en(3));
		weathermap.put("forecast_all_en4", wi.getForecast_all_en(4));
		weathermap.put("forecast_all_en5", wi.getForecast_all_en(5));
		weathermap.put("forecast_all_en6", wi.getForecast_all_en(6));
		
		weathermap.put("dressing", wi.getDressing_index());
		weathermap.put("dressing_brief", wi.getDressing_brief());
		weathermap.put("dressing_details", wi.getDressing_details());
		weathermap.put("ultraviolet", wi.getUltraviolet_index());
		weathermap.put("ultraviolet_brief", wi.getUltraviolet_brief());
		weathermap.put("ultraviolet_details", wi.getUltraviolet_details());
		weathermap.put("car_washing", wi.getCar_washing_index());
		weathermap.put("car_washing_brief", wi.getCar_washing_brief());
		weathermap.put("car_washing_details", wi.getCar_washing_details());
		weathermap.put("travel", wi.getTravel_index());
		weathermap.put("travel_brief", wi.getTravel_brief());
		weathermap.put("travel_details", wi.getTravel_details());
		weathermap.put("flu", wi.getFlu_index());
		weathermap.put("flu_brief", wi.getFlu_brief());
		weathermap.put("flu_details", wi.getFlu_details());
		weathermap.put("sport", wi.getSport_index());
		weathermap.put("sport_brief", wi.getSport_brief());
		weathermap.put("sport_details", wi.getSport_details());
		weathermap.put("index_all", wi.getIndex_all());

		weathermap.put("dressing_en", wi.getDressing_index_en());
		weathermap.put("dressing_brief_en", wi.getDressing_brief_en());
		weathermap.put("dressing_details_en", wi.getDressing_details_en());
		weathermap.put("ultraviolet_en", wi.getUltraviolet_index_en());
		weathermap.put("ultraviolet_brief_en", wi.getUltraviolet_brief_en());
		weathermap.put("ultraviolet_details_en", wi.getUltraviolet_details_en());
		weathermap.put("car_washing_en", wi.getCar_washing_index_en());
		weathermap.put("car_washing_brief_en", wi.getCar_washing_brief_en());
		weathermap.put("car_washing_details_en", wi.getCar_washing_details_en());
		weathermap.put("travel_en", wi.getTravel_index_en());
		weathermap.put("travel_brief_en", wi.getTravel_brief_en());
		weathermap.put("travel_details_en", wi.getTravel_details_en());
		weathermap.put("flu_en", wi.getFlu_index_en());
		weathermap.put("flu_brief_en", wi.getFlu_brief_en());
		weathermap.put("flu_details_en", wi.getFlu_details_en());
		weathermap.put("sport_en", wi.getSport_index_en());
		weathermap.put("sport_brief_en", wi.getSport_brief_en());
		weathermap.put("sport_details_en", wi.getSport_details_en());
		weathermap.put("index_all_en", wi.getIndex_all_en());

		weathermap.put("AQI", wi.getAQI());
		weathermap.put("PM25", wi.getPM25());
		weathermap.put("PM10", wi.getPM10());
		weathermap.put("SO2", wi.getSO2());
		weathermap.put("NO2", wi.getNO2());
		weathermap.put("CO", wi.getCO());
		weathermap.put("O3", wi.getO3());
		weathermap.put("air_quality", wi.getAir_quality());
		weathermap.put("air_last_update", wi.getAir_last_update());
		weathermap.put("air_all", wi.getAir_all());
		weathermap.put("AQI_en", wi.getAQI_en());
		weathermap.put("PM25_en", wi.getPM25_en());
		weathermap.put("PM10_en", wi.getPM10_en());
		weathermap.put("SO2_en", wi.getSO2_en());
		weathermap.put("NO2_en", wi.getNO2_en());
		weathermap.put("CO_en", wi.getCO_en());
		weathermap.put("O3_en", wi.getO3_en());
		weathermap.put("air_quality_en", wi.getAir_quality_en());
		weathermap.put("air_last_update_en", wi.getAir_last_update_en());
		weathermap.put("air_all_en", wi.getAir_all_en());
		
		RedisManager.getInstance().set_hashmap(wi.getCity_ID(),weathermap);
		weathermap.clear();
		
	}
}