package org.softcits.cn.serivce;



import org.softcits.cn.pojo.CityPojo;

public interface CityService {
	
	Integer insertCity(CityPojo cityPojo);
	
	Integer insertCityBatch();

}
