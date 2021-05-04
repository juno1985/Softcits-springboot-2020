package org.softcits.cn.pojo;

import java.util.List;

public class Data {
	
	private YesterdayPojo yesterday;
	private String city;
	private List<ForecastPojo> forecast;
	private String ganmao;
	private String wendu;
	public YesterdayPojo getYesterday() {
		return yesterday;
	}
	public void setYesterday(YesterdayPojo yesterday) {
		this.yesterday = yesterday;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public List<ForecastPojo> getForecast() {
		return forecast;
	}
	public void setForecast(List<ForecastPojo> forecast) {
		this.forecast = forecast;
	}
	public String getGanmao() {
		return ganmao;
	}
	public void setGanmao(String ganmao) {
		this.ganmao = ganmao;
	}
	public String getWendu() {
		return wendu;
	}
	public void setWendu(String wendu) {
		this.wendu = wendu;
	}
	
	

}
