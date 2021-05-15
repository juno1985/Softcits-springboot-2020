package org.softcits.cn.serivce;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;
public class HttpURLConnectionTest {
	
	public String httpGetRequest(String cityId) {
		
		final String prefix_url = "http://wthrcdn.etouch.cn/weather_mini?citykey=";
		
		String full_url = prefix_url + cityId;
		
		try {
			URL url = new URL(full_url);
			
			HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.setDoInput(true);
			urlConnection.setDoOutput(false);
			urlConnection.setRequestProperty("Accept-Charset", "utf-8");
			//urlConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=UTF-8");
			urlConnection.setRequestProperty("Charset", "utf-8");
			urlConnection.connect();
			
			if(HttpURLConnection.HTTP_OK == urlConnection.getResponseCode()) {
				
				InputStream inputStream = urlConnection.getInputStream();
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				
				String line = null;
				String content = "";
				while((line = bufferedReader.readLine()) != null) {
					System.out.println(line);
					content += line;
				}
				
				return content;
			}
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	@Test
	public void getTest() throws UnsupportedEncodingException {
		String cityId = "101010100";
		String result = this.httpGetRequest(cityId);
		System.out.println(new String(result.getBytes(),"utf-8"));
	}

}
