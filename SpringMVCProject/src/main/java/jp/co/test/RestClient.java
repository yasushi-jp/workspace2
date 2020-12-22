package jp.co.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.bean.ResData;

public class RestClient {

	public static void main(String[] args) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");

		HttpEntity<String> entity = new HttpEntity<String>(headers);

		Map<String, String> params = new HashMap<String, String>();
		params.put("page", "1");
		params.put("per_page", "100");
//		System.setProperty("jdk.tls.client.protocols", "TLSv2");
		ResponseEntity<String> response = restTemplate.exchange(
				"https://qiita.com/api/v2/authenticated_user/items",
				HttpMethod.GET,
				entity,
				String.class,
				params);
		System.out.println(response.getStatusCode());
		String json = response.getBody();
	       ObjectMapper mapper = new ObjectMapper();
	        ResData[] resData = null;
	        try {
	            resData = mapper.readValue(json, ResData[].class);
	        } catch (JsonParseException e) {
	            // TODO 自動生成された catch ブロック
	            e.printStackTrace();
	        } catch (JsonMappingException e) {
	            // TODO 自動生成された catch ブロック
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO 自動生成された catch ブロック
	            e.printStackTrace();
	        }
	        for (ResData data : resData) {
	            System.out.println("title=" + data.getTitle());
	        }

	}

}
