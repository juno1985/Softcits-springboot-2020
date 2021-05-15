package org.softcits.cn.config;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfiguration {
	
	@Autowired
	private RestTemplateBuilder builder;

	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = builder.build();
		List<HttpMessageConverter<?>> list = restTemplate.getMessageConverters();
	    for (HttpMessageConverter<?> httpMessageConverter : list) {
	        if(httpMessageConverter instanceof StringHttpMessageConverter) {
	            ((StringHttpMessageConverter) httpMessageConverter).setDefaultCharset(StandardCharsets.UTF_8);
	            break;
	        }
	    }
	    return restTemplate;
	}
	
}