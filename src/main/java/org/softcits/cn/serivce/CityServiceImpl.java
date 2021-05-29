package org.softcits.cn.serivce;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softcits.cn.mapper.CityMapper;
import org.softcits.cn.model.City;
import org.softcits.cn.pojo.CityPojo;
import org.softcits.cn.util.JSONObjectConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
@Service
public class CityServiceImpl implements CityService {
	
	 private Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);

	@Autowired
	private CityMapper cityMapper;
	
	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.NESTED)
	public Integer insertCity(CityPojo cityPojo) {
		
		Integer num = cityMapper.insertCity(cityPojo);
		
		return num;
	}

	/**
	 * 
	 * return -- the #rows inserted
	 */
	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW)
	public Integer insertCityBatch() {
		
		String filePath = "citylist.json";
		TypeReference<List<CityPojo>> typeReference = new TypeReference<List<CityPojo>>() {};
		List<CityPojo> cityList = JSONObjectConverter.generateObjectFromJSONFile(filePath, typeReference);
		Iterator<CityPojo> it = cityList.iterator();
		int total = cityList.size();
		int duplicated = 0;
		CityPojo city = null;
		while(it.hasNext()) {
			city = it.next();
			try {
				this.insertCity(city);
			} catch (Exception e) {
				if(city != null) {
					logger.error("Duplicated record: " + city.getName() + " -> " + city.getId());
				}else {
					logger.error("Unable to get City Infor from JSON");
				}
				duplicated++;
			}
		} 
		
		return total - duplicated;
	}

	@Override
	public List<City> getAllCities() {
		
		return cityMapper.getAllCities();
	}

}
