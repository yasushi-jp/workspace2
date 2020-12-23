package jp.co.client;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jp.co.bean.ResData;

public class RestClient {

	public static void main(String[] args) {

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
		params.add("page", "1");
		params.add("per_page", "100");

		URI uri = null;
		URI queryURI = null;
		try {
			uri = new URI("https://qiita.com/api/v2/authenticated_user/items");
			queryURI =  UriComponentsBuilder.fromUri(uri).queryParams(params).build().toUri();
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.add("Authorization", "Bearer b5fc4204e777d8a78b57d7c66bf8008595641124");

		RequestEntity<?> req = new RequestEntity<>(headers, HttpMethod.GET, queryURI);

		ResponseEntity<ResData[]> res = restTemplate.exchange(req, ResData[].class);
		System.out.println(res.getStatusCode());
		ResData[] resData = res.getBody();

		int cnt = 0;
		for (ResData data : resData) {
			cnt++;
			System.out.println(cnt + ":" + data.getTitle());
		}

	}

}
