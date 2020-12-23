package jp.co.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class RestClient1 {

	public static void main(String[] args) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer b5fc4204e777d8a78b57d7c66bf8008595641124");

		HttpEntity<String> entity = new HttpEntity<String>(headers);

		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("page", "1");
		params.add("per_page", "100");

		String url = "https://qiita.com/api/v2/authenticated_user/items";
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("page", 1)
				.queryParam("per_page", 100);

		System.out.println(uriBuilder.build().encode().toUri());
		ResponseEntity<String> response = restTemplate.exchange(
				uriBuilder.build().encode().toUri(),
				HttpMethod.GET,
				entity,
				String.class);

		System.out.println(response.getStatusCode());
		String json = response.getBody();
//		ObjectMapper mapper = new ObjectMapper();
//		ResData[] resData = null;
//		try {
//			resData = mapper.readValue(json, ResData[].class);
//		} catch (JsonParseException e) {
//			// TODO 自動生成された catch ブロック
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			// TODO 自動生成された catch ブロック
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO 自動生成された catch ブロック
//			e.printStackTrace();
//		}
//		int cnt = 0;
//		for (ResData data : resData) {
//			cnt++;
//			System.out.println(cnt + ", title=" + data.getTitle());
//		}

	}

}
