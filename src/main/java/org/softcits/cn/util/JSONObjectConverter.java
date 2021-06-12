package org.softcits.cn.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

public class JSONObjectConverter {
	
	private static final Logger log = LoggerFactory.getLogger(JSONObjectConverter.class);

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
			log.error(e.getMessage());
		}
		
		return null;
	}
	/**
	 * 
	 * @param <T> object to convert to
	 * @param jsonStr	json string
	 * @param typeReference
	 * @return
	 */
	public static<T> T generateObjectFromJSON(String jsonStr, TypeReference<T> typeReference) {

		ObjectMapper objectMapper = new ObjectMapper();

		try {
			return objectMapper.readValue(jsonStr, typeReference);
		} catch (JsonMappingException e) {
			log.error("JsonMappingException: converting JSON to Object");
			log.error(e.getMessage());
		} catch (JsonProcessingException e) {
			log.error("JsonProcessingException: converting JSON to Object");
			log.error(e.getMessage());
		} catch (Exception e) {
			log.error("Exception: converting JSON to Object");
			log.error(e.getMessage());
		}
		return null;
	}

	public static String generateJSONFromObject(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}
}
