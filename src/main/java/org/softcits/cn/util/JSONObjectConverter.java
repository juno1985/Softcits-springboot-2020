package org.softcits.cn.util;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

public class JSONObjectConverter {

	private JSONObjectConverter() {
	}

	/**
	 * 
	 * @param <T>
	 * @param filePath -- json file path
	 * @param typeReference -- expected object type
	 * @return Object
	 */
	public static <T> T generateObjectFromJSONFile(String filePath, TypeReference<T> typeReference) {
		Resource resource = new ClassPathResource(filePath); 
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			// obtain inputstream from file
			InputStream inputStream = resource.getInputStream();
			/**
			 *  file should be in json format
			 *  convert json to object and return
			 */
			return objectMapper.readValue(inputStream, typeReference);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	

}
