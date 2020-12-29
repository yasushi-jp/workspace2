package jp.co.client;

import java.net.URI;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestClient2 {

	public static void main(String[] args) {

		String strUri = "https://qiita.com/api/v2/{user}/items";

		URI uri = UriComponentsBuilder.fromUriString(strUri)
				.queryParam("page", "1")
				.queryParam("per_page", "100")
				.buildAndExpand("authenticated_user")
				.toUri();

		RequestEntity<?> req = RequestEntity.get(uri)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.header("Authorization", "Bearer xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx")
				.build();

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> res = restTemplate.exchange(req, String.class);

		System.out.println(res.getStatusCode());

		String json = res.getBody();
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object>[] maps = null;
		try {
			maps = objectMapper.readValue(json, new TypeReference<Map<String, Object>[]>() {});
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		for (Map<String, Object> map : maps) {
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				System.out.println(entry.getKey());
				System.out.println(entry.getValue());
			}
		}


	}

}
