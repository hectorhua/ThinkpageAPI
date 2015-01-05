package com.table;

/**
 * @Project: ThinkpageAPI
 * @Author: hectorhua
 * @Date: 2014
 */

public class WFTableItem {
	
	//城市代码
	private String city_ID;
	//城市名
	private String city_name;
	//城市英文名
	private String city_name_en;
	//中文城市预报
	private String city_name_weather;
	//英文城市预报
	private String city_name_en_weather;
	/**城市所在省
	private String city_province;
	//城市所在省英文名
	private String city_province_en;
	//城市所在国家
	private String city_country;
	//城市所在国家英文名
	private String city_country_en;
	*/
	//当前气象
	private String current_status;
	//当前温度
	private String current_temperature;
	//当前湿度
	private String current_humidity;
	//当前风力（级）
	private String current_wind_power;
	//当前风向
	private String current_wind_direction;
	//体感温度
	private String feel_temperature;
	//能见度
	private String visibility;
	//气压
	private String atmospheric;
	//observe发布时间
	private String observe_time;
	//observe合集
	private String observe_all;
	//对应英文
	private String current_status_en;
	private String current_temperature_en;
	private String current_humidity_en;
	private String current_wind_power_en;
	private String current_wind_direction_en;
	private String feel_temperature_en;
	private String visibility_en;
	private String atmospheric_en;
	private String observe_time_en;
	private String observe_all_en;
	
	//空气质量
	private String AQI;
	private String PM25;
	private String PM10;
	private String SO2;
	private String NO2;
	private String CO;
	private String O3;
	private String air_quality;
	private String air_last_update;
	//空气质量合集
	private String air_all;
	//对应英文
	private String AQI_en;
	private String PM25_en;
	private String PM10_en;
	private String SO2_en;
	private String NO2_en;
	private String CO_en;
	private String O3_en;
	private String air_quality_en;
	private String air_last_update_en;
	private String air_all_en;
	
	//未来天气情况
	private String forecast_name = "未来预报:";
	//日期
	private String[] forecast_date;
	//星期
	private String[] forecast_week;
	//白天及夜间天气状况
	private String[] status_day_night;
	//风力风向	
	private String[] wind_direction_power;
	//白天最高气温	
	private String[] temperature_high;
	//夜间最低气温
	private String[] temperature_low;
	//未来天气合集
	private String[] forecast_all;
	//对应英文
	private String forecast_name_en = " Weather Forecast:";
	private String[] forecast_date_en;
	private String[] forecast_week_en;
	private String[] status_day_night_en;
	private String[] wind_direction_power_en;
	private String[] temperature_high_en;
	private String[] temperature_low_en;
	private String[] forecast_all_en;
	
	//指数
	//穿衣指数
	private String dressing = "穿衣指数:";
	//穿衣指数级别
	private String dressing_brief;
	//穿衣指数内容
	private String dressing_details;
	//紫外线指数
	private String ultraviolet = "紫外线指数:";
	//紫外线强度
	private String ultraviolet_brief;
	//紫外线描述
	private String ultraviolet_details;
	//洗车指数
	private String car_washing = "洗车指数:";
	//洗车级别
	private String car_washing_brief;
	//洗车描述
	private String car_washing_details;
	//旅游指数
	private String travel = "旅游指数:";
	//旅游级别
	private String travel_brief;
	//旅游描述
	private String travel_details;
	//感冒指数
	private String flu = "感冒指数:";
	//感冒指数级别
	private String flu_brief;
	//感冒描述
	private String flu_details;
	//运动指数
	private String sport = "运动指数:";
	//运动简述
	private String sport_brief;
	//运动描述
	private String sport_details;
	//指数合集
	private String index_all;
	//对应英文
	private String dressing_en = "Dressing:";
	private String dressing_brief_en;
	private String dressing_details_en;
	private String ultraviolet_en = "Ultraviolet:";
	private String ultraviolet_brief_en;
	private String ultraviolet_details_en;
	private String car_washing_en = "Car_washing:";
	private String car_washing_brief_en;
	private String car_washing_details_en;
	private String travel_en = "Travel:";
	private String travel_brief_en;
	private String travel_details_en;
	private String flu_en = "Flu:";
	private String flu_brief_en;
	private String flu_details_en;
	private String sport_en = "Sport:";
	private String sport_brief_en;
	private String sport_details_en;
	private String index_all_en;
	
	public WFTableItem(){
		city_ID = "";
		city_name = "";
		city_name_en = "";
		city_name_weather = "";
		city_name_en_weather = "";
		
		current_status = "";
		current_temperature = "";
		current_humidity = "";
		current_wind_power = "";
		current_wind_direction = "";
		feel_temperature = "";
		visibility = "";
		atmospheric = "";
		observe_time = "";
		observe_all = "";
		current_status_en = "";
		current_temperature_en = "";
		current_humidity_en = "";
		current_wind_power_en = "";
		current_wind_direction_en = "";
		feel_temperature_en = "";
		visibility_en = "";
		atmospheric_en = "";
		observe_time_en = "";
		observe_all_en = "";
		
		
		AQI = "";
		PM25 = "";
		PM10 = "";
		SO2 = "";
		NO2 = "";
		CO = "";
		O3 = "";
		air_quality = "";
		air_last_update = "";
		air_all = "";
		AQI_en = "";
		PM25_en = "";
		PM10_en = "";
		SO2_en = "";
		NO2_en = "";
		CO_en = "";
		O3_en = "";
		air_quality_en = "";
		air_last_update_en = "";
		air_all_en = "";
		
		forecast_date = new String[] {"","","","","","",""};
		forecast_week = new String[] {"","","","","","",""};
		status_day_night = new String[] {"","","","","","",""};
		wind_direction_power = new String[] {"","","","","","",""};
		temperature_high = new String[] {"","","","","","",""};
		temperature_low = new String[] {"","","","","","",""};
		forecast_all = new String[] {"","","","","","",""};
		forecast_date_en = new String[] {"","","","","","",""};
		forecast_week_en = new String[] {"","","","","","",""};
		status_day_night_en = new String[] {"","","","","","",""};
		wind_direction_power_en = new String[] {"","","","","","",""};
		temperature_high_en = new String[] {"","","","","","",""};
		temperature_low_en = new String[] {"","","","","","",""};
		forecast_all_en = new String[] {"","","","","","",""};
		
		dressing_brief = "";
		dressing_details = "";
		ultraviolet_brief = "";
		ultraviolet_details = "";
		car_washing_brief = "";
		car_washing_details = "";
		travel_brief = "";
		travel_details = "";
		flu_brief = "";
		flu_details = "";
		sport_brief = "";
		sport_details = "";
		index_all = "";
		dressing_brief_en = "";
		dressing_details_en = "";
		ultraviolet_brief_en = "";
		ultraviolet_details_en = "";
		car_washing_brief_en = "";
		car_washing_details_en = "";
		travel_brief_en = "";
		travel_details_en = "";
		flu_brief_en = "";
		flu_details_en = "";
		sport_brief_en = "";
		sport_details_en = "";
		index_all_en = "";
	}
	
	public String getCity_ID() {
		return city_ID;
	}
	public void setCity_ID(String city_ID) {
		this.city_ID = city_ID;
	}
	
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	
	public String getCity_name_en() {
		return city_name_en;
	}
	public void setCity_name_en(String city_name_en) {
		this.city_name_en = city_name_en;
	}
	
	public String getCity_name_weather() {
		return city_name_weather;
	}
	public void setCity_name_weather(String city_name_weather) {
		this.city_name_weather = city_name_weather;
	}
	
	public String getCity_name_en_weather() {
		return city_name_en_weather;
	}
	public void setCity_name_en_weather(String city_name_en_weather) {
		this.city_name_en_weather = city_name_en_weather;
	}
	
	//中文实时
	public String getCurrent_status() {
		return current_status;
	}
	public void setCurrent_status(String current_status) {
		this.current_status = current_status;
	}
	
	public String getCurrent_temperature() {
		return current_temperature;
	}
	public void setCurrent_temperature(String current_temperature) {
		this.current_temperature = current_temperature;
	}
	
	public String getCurrent_humidity() {
		return current_humidity;
	}
	public void setCurrent_humidity(String current_humidity) {
		this.current_humidity = current_humidity;
	}
	
	public String getCurrent_wind_power() {
		return current_wind_power;
	}
	public void setCurrent_wind_power(String current_power) {
		this.current_wind_power = current_power;
	}
	
	public String getCurrent_wind_direction() {
		return current_wind_direction;
	}
	public void setCurrent_wind_direction(String current_direction) {
		this.current_wind_direction = current_direction;
	}
	
	public String getFeel_temperature() {
		return feel_temperature;
	}
	public void setFeel_temperature(String feel_temperature) {
		this.feel_temperature = feel_temperature;
	}
	
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	
	public String getAtmospheric() {
		return atmospheric;
	}
	public void setAtmospheric(String atmospheric) {
		this.atmospheric = atmospheric;
	}
	
	public String getObserve_time() {
		return observe_time;
	}
	public void setObserve_time(String observe_time) {
		this.observe_time = observe_time;
	}
	
	public String getObserve_all() {
		return observe_all;
	}
	public void setObserve_all(String observe_all) {
		this.observe_all = observe_all;
	}
	//英文实时
	public String getCurrent_status_en() {
		return current_status_en;
	}
	public void setCurrent_status_en(String current_status_en) {
		this.current_status_en = current_status_en;
	}
	
	public String getCurrent_temperature_en() {
		return current_temperature_en;
	}
	public void setCurrent_temperature_en(String current_temperature_en) {
		this.current_temperature_en = current_temperature_en;
	}
	
	public String getCurrent_humidity_en() {
		return current_humidity_en;
	}
	public void setCurrent_humidity_en(String current_humidity_en) {
		this.current_humidity_en = current_humidity_en;
	}
	
	public String getCurrent_wind_power_en() {
		return current_wind_power_en;
	}
	public void setCurrent_wind_power_en(String current_power_en) {
		this.current_wind_power_en = current_power_en;
	}
	
	public String getCurrent_wind_direction_en() {
		return current_wind_direction_en;
	}
	public void setCurrent_wind_direction_en(String current_direction_en) {
		this.current_wind_direction_en = current_direction_en;
	}
	
	public String getFeel_temperature_en() {
		return feel_temperature_en;
	}
	public void setFeel_temperature_en(String feel_temperature_en) {
		this.feel_temperature_en = feel_temperature_en;
	}
	
	public String getVisibility_en() {
		return visibility_en;
	}
	public void setVisibility_en(String visibility_en) {
		this.visibility_en = visibility_en;
	}
	
	public String getAtmospheric_en() {
		return atmospheric_en;
	}
	public void setAtmospheric_en(String atmospheric_en) {
		this.atmospheric_en = atmospheric_en;
	}
	
	public String getObserve_time_en() {
		return observe_time_en;
	}
	public void setObserve_time_en(String observe_time_en) {
		this.observe_time_en = observe_time_en;
	}
	
	public String getObserve_all_en() {
		return observe_all_en;
	}
	public void setObserve_all_en(String observe_all_en) {
		this.observe_all_en = observe_all_en;
	}
	
	//中文空气质量
	public String getAQI() {
		return AQI;
	}
	public void setAQI(String AQI) {
		this.AQI = AQI;
	}
	
	public String getPM25() {
		return PM25;
	}
	public void setPM25(String PM25) {
		this.PM25 = PM25;
	}
	
	public String getPM10() {
		return PM10;
	}
	public void setPM10(String getPM10) {
		this.PM10 = getPM10;
	}
	
	public String getSO2() {
		return SO2;
	}
	public void setSO2(String SO2) {
		this.SO2 = SO2;
	}
	
	public String getNO2() {
		return NO2;
	}
	public void setNO2(String NO2) {
		this.NO2 = NO2;
	}
	
	public String getCO() {
		return CO;
	}
	public void setCO(String CO) {
		this.CO = CO;
	}

	public String getO3() {
		return O3;
	}
	public void setO3(String O3) {
		this.O3 = O3;
	}
	
	public String getAir_quality() {
		return air_quality;
	}
	public void setAir_quality(String air_quality) {
		this.air_quality = air_quality;
	}
	
	public String getAir_last_update() {
		return air_last_update;
	}
	public void setAir_last_update(String air_last_update) {
		this.air_last_update = air_last_update;
	}
	
	public String getAir_all() {
		return air_all;
	}
	public void setAir_all(String air_all) {
		this.air_all = air_all;
	}
	//英文空气质量
	public String getAQI_en() {
		return AQI_en;
	}
	public void setAQI_en(String AQI_en) {
		this.AQI_en = AQI_en;
	}
	
	public String getPM25_en() {
		return PM25_en;
	}
	public void setPM25_en(String PM25_en) {
		this.PM25_en = PM25_en;
	}
	
	public String getPM10_en() {
		return PM10_en;
	}
	public void setPM10_en(String getPM10_en) {
		this.PM10_en = getPM10_en;
	}
	
	public String getSO2_en() {
		return SO2_en;
	}
	public void setSO2_en(String SO2_en) {
		this.SO2_en = SO2_en;
	}
	
	public String getNO2_en() {
		return NO2_en;
	}
	public void setNO2_en(String NO2_en) {
		this.NO2_en = NO2_en;
	}
	
	public String getCO_en() {
		return CO_en;
	}
	public void setCO_en(String CO_en) {
		this.CO_en = CO_en;
	}

	public String getO3_en() {
		return O3_en;
	}
	public void setO3_en(String O3_en) {
		this.O3_en = O3_en;
	}
	
	public String getAir_quality_en() {
		return air_quality_en;
	}
	public void setAir_quality_en(String air_quality_en) {
		this.air_quality_en = air_quality_en;
	}
	
	public String getAir_last_update_en() {
		return air_last_update_en;
	}
	public void setAir_last_update_en(String air_last_update_en) {
		this.air_last_update_en = air_last_update_en;
	}
	
	public String getAir_all_en() {
		return air_all_en;
	}
	public void setAir_all_en(String air_all_en) {
		this.air_all_en = air_all_en;
	}
	
	//中文预报
	public String getForecast_name() {
		return forecast_name;
	}
	public void setForecast_name(String forecast_name) {
		this.forecast_name = forecast_name;
	}
	
	public String getForecast_date(int i) {
		return forecast_date[i];
	}
	public void setForecast_date(String forecast_date, int i) {
		this.forecast_date[i] = forecast_date;
	}

	public String getForecast_week(int i) {
		return forecast_week[i];
	}
	public void setForecast_week(String forecast_week, int i) {
		this.forecast_week[i] = forecast_week;
	}
	
	public String getStatus_day_night(int i) {
		return status_day_night[i];
	}
	public void setStatus_day_night(String status_day_night, int i) {
		this.status_day_night[i] = status_day_night;
	}
	
	public String getWind_direction_power(int i) {
		return wind_direction_power[i];
	}
	public void setWind_direction_power(String wind_direction_power, int i) {
		this.wind_direction_power[i] = wind_direction_power;
	}
	
	public String getTemperature_high(int i) {
		return temperature_high[i];
	}
	public void setTemperature_high(String temperature_high, int i) {
		this.temperature_high[i] = temperature_high;
	}
	
	public String getTemperature_low(int i) {
		return temperature_low[i];
	}
	public void setTemperature_low(String temperature_low, int i) {
		this.temperature_low[i] = temperature_low;
	}
	
	public String getForecast_all(int i) {
		return forecast_all[i];
	}
	public void setForecast_all(String forecast_all, int i) {
		this.forecast_all[i] = forecast_all;
	}
	//英文预报
	public String getForecast_name_en() {
		return forecast_name_en;
	}
	public void setForecast_name_en(String forecast_name_en) {
		this.forecast_name_en = forecast_name_en;
	}
	
	public String getForecast_date_en(int i) {
		return forecast_date_en[i];
	}
	public void setForecast_date_en(String forecast_date_en, int i) {
		this.forecast_date_en[i] = forecast_date_en;
	}

	public String getForecast_week_en(int i) {
		return forecast_week_en[i];
	}
	public void setForecast_week_en(String forecast_week_en, int i) {
		this.forecast_week_en[i] = forecast_week_en;
	}
	
	public String getStatus_day_night_en(int i) {
		return status_day_night_en[i];
	}
	public void setStatus_day_night_en(String status_day_night_en, int i) {
		this.status_day_night_en[i] = status_day_night_en;
	}
	
	public String getWind_direction_power_en(int i) {
		return wind_direction_power_en[i];
	}
	public void setWind_direction_power_en(String wind_direction_power_en, int i) {
		this.wind_direction_power_en[i] = wind_direction_power_en;
	}
	
	public String getTemperature_high_en(int i) {
		return temperature_high_en[i];
	}
	public void setTemperature_high_en(String temperature_high_en, int i) {
		this.temperature_high_en[i] = temperature_high_en;
	}
	
	public String getTemperature_low_en(int i) {
		return temperature_low_en[i];
	}
	public void setTemperature_low_en(String temperature_low_en, int i) {
		this.temperature_low_en[i] = temperature_low_en;
	}
	
	public String getForecast_all_en(int i) {
		return forecast_all_en[i];
	}
	public void setForecast_all_en(String forecast_all_en, int i) {
		this.forecast_all_en[i] = forecast_all_en;
	}
	
	//中文指数
	//穿衣
	public String getDressing_index() {
		return dressing;
	}
	public String getDressing_brief() {
		return dressing_brief;
	}
	public void setDressing_brief(String dressing_brief) {
		this.dressing_brief = dressing_brief;
	}
	public String getDressing_details() {
		return dressing_details;
	}
	public void setDressing_details(String dressing_details) {
		this.dressing_details = dressing_details;
	}

	//紫外线
	public String getUltraviolet_index() {
		return ultraviolet;
	}
	public String getUltraviolet_brief() {
		return ultraviolet_brief;
	}
	public void setUltraviolet_brief(String ultraviolet_brief) {
		this.ultraviolet_brief = ultraviolet_brief;
	}
	public String getUltraviolet_details() {
		return ultraviolet_details;
	}
	public void setUltraviolet_details(String ultraviolet_details) {
		this.ultraviolet_details = ultraviolet_details;
	}
	
	//洗车
	public String getCar_washing_index() {
		return car_washing;
	}
	public String getCar_washing_brief() {
		return car_washing_brief;
	}
	public void setCar_washing_brief(String car_washing_brief) {
		this.car_washing_brief = car_washing_brief;
	}
	public String getCar_washing_details() {
		return car_washing_details;
	}
	public void setCar_washing_details(String car_washing_details) {
		this.car_washing_details = car_washing_details;
	}
	
	//旅游
	public String getTravel_index() {
		return travel;
	}
	public String getTravel_brief() {
		return travel_brief;
	}
	public void setTravel_brief(String travel_brief) {
		this.travel_brief = travel_brief;
	}
	public String getTravel_details() {
		return travel_details;
	}
	public void setTravel_details(String travel_details) {
		this.travel_details = travel_details;
	}
	
	//感冒
	public String getFlu_index() {
		return flu;
	}
	public String getFlu_brief() {
		return flu_brief;
	}
	public void setFlu_brief(String flu_brief) {
		this.flu_brief = flu_brief;
	}
	public String getFlu_details() {
		return flu_details;
	}
	public void setFlu_details(String flu_details) {
		this.flu_details = flu_details;
	}

	//运动
	public String getSport_index() {
		return sport;
	}
	public String getSport_brief() {
		return sport_brief;
	}
	public void setSport_brief(String sport_brief) {
		this.sport_brief = sport_brief;
	}
	public String getSport_details() {
		return sport_details;
	}
	public void setSport_details(String sport_details) {
		this.sport_details = sport_details;
	}
	
	public String getIndex_all() {
		return index_all;
	}
	public void setIndex_all(String index_all) {
		this.index_all = index_all;
	}
	//英文指数
	public String getDressing_index_en() {
		return dressing_en;
	}
	public String getDressing_brief_en() {
		return dressing_brief_en;
	}
	public void setDressing_brief_en(String dressing_brief_en) {
		this.dressing_brief_en = dressing_brief_en;
	}
	public String getDressing_details_en() {
		return dressing_details_en;
	}
	public void setDressing_details_en(String dressing_details_en) {
		this.dressing_details_en = dressing_details_en;
	}
	
	public String getUltraviolet_index_en() {
		return ultraviolet_en;
	}
	public String getUltraviolet_brief_en() {
		return ultraviolet_brief_en;
	}
	public void setUltraviolet_brief_en(String ultraviolet_brief_en) {
		this.ultraviolet_brief_en = ultraviolet_brief_en;
	}
	public String getUltraviolet_details_en() {
		return ultraviolet_details_en;
	}
	public void setUltraviolet_details_en(String ultraviolet_details_en) {
		this.ultraviolet_details_en = ultraviolet_details_en;
	}
	
	public String getCar_washing_index_en() {
		return car_washing_en;
	}
	public String getCar_washing_brief_en() {
		return car_washing_brief_en;
	}
	public void setCar_washing_brief_en(String car_washing_brief_en) {
		this.car_washing_brief_en = car_washing_brief_en;
	}
	public String getCar_washing_details_en() {
		return car_washing_details_en;
	}
	public void setCar_washing_details_en(String car_washing_details_en) {
		this.car_washing_details_en = car_washing_details_en;
	}
	
	public String getTravel_index_en() {
		return travel_en;
	}
	public String getTravel_brief_en() {
		return travel_brief_en;
	}
	public void setTravel_brief_en(String travel_brief_en) {
		this.travel_brief_en = travel_brief_en;
	}
	public String getTravel_details_en() {
		return travel_details_en;
	}
	public void setTravel_details_en(String travel_details_en) {
		this.travel_details_en = travel_details_en;
	}
	
	public String getFlu_index_en() {
		return flu_en;
	}
	public String getFlu_brief_en() {
		return flu_brief_en;
	}
	public void setFlu_brief_en(String flu_brief_en) {
		this.flu_brief_en = flu_brief_en;
	}
	public String getFlu_details_en() {
		return flu_details_en;
	}
	public void setFlu_details_en(String flu_details_en) {
		this.flu_details_en = flu_details_en;
	}

	public String getSport_index_en() {
		return sport_en;
	}
	public String getSport_brief_en() {
		return sport_brief_en;
	}
	public void setSport_brief_en(String sport_brief_en) {
		this.sport_brief_en = sport_brief_en;
	}
	public String getSport_details_en() {
		return sport_details_en;
	}
	public void setSport_details_en(String sport_details_en) {
		this.sport_details_en = sport_details_en;
	}
	
	public String getIndex_all_en() {
		return index_all_en;
	}
	public void setIndex_all_en(String index_all_en) {
		this.index_all_en = index_all_en;
	}
	
	public void dumpTableItemInfor(){
		//城市代码
		System.out.println("城市代码："+city_ID);
		//城市名称
		System.out.println("城市名称："+city_name);
		//城市英文名称
		System.out.println("城市英文名称："+city_name_en);
	}
}
