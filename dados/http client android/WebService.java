package br.com.agili.issfiscal.webservice;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

/**
 * 
 * Classe responsável por auxiliar a comunicação com o Web Service e a classe
 * HttpClientSingleton.
 * 
 * @author Douglas Costa <douglas.cst90@gmail.com.br>
 * @since 14/06/2013 17:20:49
 * @version 1.0
 */
public class WebService {

	private static final String TAG = "WebService";

	public static WebService instance = null;

	private WebService() {

	}

	public static WebService getInstance() {
		if (instance == null)
			instance = new WebService();
		return instance;
	}

	@SuppressWarnings("unchecked")
	public final String[] get(String url) throws Exception{

		String[] result = new String[2];
		HttpGet httpget = new HttpGet(url);
		HttpResponse response;

		try {

			DefaultHttpClient httpClient = HttpClientSingleton.getInstace()
					.getHttpClient();

			response = httpClient.execute(httpget);

			HttpEntity entity = response.getEntity();

			if (entity != null) {
				result[0] = String.valueOf(response.getStatusLine()
						.getStatusCode());
				InputStream instream = entity.getContent();
				result[1] = toString(instream);
				instream.close();
			}

		} catch (Exception e) {
			Log.e(TAG, "Falha ao acessar Web service", e);
			result[0] = "0";
			result[1] = "Falha na rede!";
			throw e;
		}
		return result;
	}

	public final String[] post(String url, String json) {
		String[] result = new String[2];
		try {

			HttpPost httpPost = new HttpPost(new URI(url));
			httpPost.setHeader("content-type", "application/json");
			StringEntity sEntity = new StringEntity(json, "UTF-8");
			httpPost.setEntity(sEntity);

			HttpResponse response;
			response = HttpClientSingleton.getInstace().getHttpClient()
					.execute(httpPost);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				result[0] = String.valueOf(response.getStatusLine()
						.getStatusCode());
				InputStream instream = entity.getContent();
				result[1] = toString(instream);
				instream.close();
			}

		} catch (Exception e) {
			Log.e(TAG, "Falha ao acessar Web service", e);
			result[0] = "0";
			result[1] = "Falha na rede!";
		}
		return result;
	}

	private String toString(InputStream is) throws IOException {

		byte[] bytes = new byte[1024];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int lidos;
		while ((lidos = is.read(bytes)) > 0) {
			baos.write(bytes, 0, lidos);
		}
		return new String(baos.toByteArray());
	}

}
