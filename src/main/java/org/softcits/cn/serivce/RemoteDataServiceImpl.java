package org.softcits.cn.serivce;

import org.softcits.cn.pojo.Response;
import org.softcits.cn.util.JSONObjectConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
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
	@Override
	public Response getResponseFromJSON(String jsonStr) {
		TypeReference<Response> typeReference = new TypeReference<Response>() {
		};
		return JSONObjectConverter.generateObjectFromJSON(jsonStr, typeReference);
	}

}
