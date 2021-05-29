package org.softcits.cn.serivce;



import java.util.List;

import org.softcits.cn.model.City;
import org.softcits.cn.pojo.CityPojo;

public interface CityService {
	
	Integer insertCity(CityPojo cityPojo);
	
	Integer insertCityBatch();
	
	List<City> getAllCities();

}
