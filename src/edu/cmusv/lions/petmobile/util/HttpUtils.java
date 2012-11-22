package edu.cmusv.lions.petmobile.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class HttpUtils {
	
	public static String makeHttpGetRequest(String url) {
		HttpGet httpGetRequest = new HttpGet(url);
		return makeHttpRequest(httpGetRequest);
	}
	
	private static String makeHttpRequest(HttpUriRequest request) {
		InputStream inputStream = null;
		String resultString = "";
		// Making HTTP request
		try {
			// defaultHttpClient
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpResponse httpResponse = httpClient.execute(request);
			HttpEntity httpEntity = httpResponse.getEntity();
			inputStream = httpEntity.getContent();

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			inputStream.close();
			resultString = sb.toString();
		} catch (Exception e) {
			Log.e("Buffer Error", "Error converting result " + e.toString());
		}
		
		return resultString;
	}
	
}
