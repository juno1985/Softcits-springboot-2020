package org.softcits.cn.serivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class RemoteDataServiceImpl implements RemoteDataService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public String getRemoteData(String url) {
		
		ResponseEntity<String> respEntity = restTemplate.getForEntity(url, String.class);
		String resp = null;
		if (respEntity.getStatusCodeValue() == 200) {
			resp = respEntity.getBody();
		}
		
		return resp;
	}

}
