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
import java.util.zip.GZIPInputStream;

import org.junit.jupiter.api.Test;
public class HttpURLConnectionTest {
	
	public String httpGetRequest(String cityId) {
		StringBuffer stringBuffer = new StringBuffer();
		final String prefix_url = "http://wthrcdn.etouch.cn/weather_mini?citykey=";
		
		String full_url = prefix_url + cityId;
		
		try {
			URL url = new URL(full_url);
			
			HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.setDoInput(true);
			urlConnection.setDoOutput(false);
			//urlConnection.setRequestProperty("Accept-Charset", "utf-8");
			//urlConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=UTF-8");
			//urlConnection.setRequestProperty("Charset", "utf-8");
			urlConnection.setRequestProperty("Accept", "text/plain, application/json, application/*+json, */*");
			urlConnection.setRequestProperty("Accept-Encoding", "gzip,deflate");
			urlConnection.connect();
			
			/*
			 * if(HttpURLConnection.HTTP_OK == urlConnection.getResponseCode())
			 * {
			 * 
			 * InputStream inputStream = urlConnection.getInputStream();
			 * InputStreamReader inputStreamReader = new
			 * InputStreamReader(inputStream, StandardCharsets.UTF_8);
			 * BufferedReader bufferedReader = new
			 * BufferedReader(inputStreamReader);
			 * 
			 * String line = null; String content = ""; while((line =
			 * bufferedReader.readLine()) != null) { System.out.println(line);
			 * content += line; }
			 * 
			 * return content; }
			 */
			
			GZIPInputStream gZIPInputStream = null;
	        String encoding = urlConnection.getContentEncoding();
	       
	        if(encoding.equals("gzip")){
	            gZIPInputStream = new GZIPInputStream(urlConnection.getInputStream());
	            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(gZIPInputStream));
	            String line = null;
	            while ((line = bufferedReader.readLine()) != null) {
	                //转化为UTF-8的编码格式
	                line = new String(line.getBytes("UTF-8"));
	                stringBuffer.append(line);
	            }
	            bufferedReader.close();
	        }else{
	            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
	            String line = null;
	            while ((line = bufferedReader.readLine()) != null) {
	                //转化为UTF-8的编码格式
	                line = new String(line.getBytes("UTF-8"));
	                stringBuffer.append(line);
	            }
	            bufferedReader.close();
	        }
	        //返回打开连接读取的输入流，输入流转化为StringBuffer类型，这一套流程要记住，常用
	        urlConnection.disconnect();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return stringBuffer.toString();
	}
	@Test
	public void getTest() throws UnsupportedEncodingException {
		String cityId = "101010100";
		String result = this.httpGetRequest(cityId);
		System.out.println(result);
	}

}
