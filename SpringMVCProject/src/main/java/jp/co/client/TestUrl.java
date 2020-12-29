package jp.co.client;

import java.net.URI;

import org.springframework.web.util.UriComponentsBuilder;

public class TestUrl {

	public static void main(String[] args) {

		String strUri = "https://qiita.com/api/v2/{name}/items";
		URI uri = UriComponentsBuilder.fromUriString(strUri)
				.queryParam("param1", "data1")
				.queryParam("param2", "data2")
				.buildAndExpand("yasushi-jp")
				.toUri();

		System.out.println(uri.toString());

	}
}
