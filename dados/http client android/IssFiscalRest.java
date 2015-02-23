package br.com.agili.issfiscal.webservice;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Pair;
import br.com.agili.issfiscal.exception.WebServiceConnectionException;
import br.com.agili.issfiscal.preferences.ConfiguracaoPreference;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class IssFiscalRest {

	//private static String URI = "http://fiscaliza.agili.com.br/api/";
	private static final String ID = "id";
	private static String URI = "http://192.168.10.189:8080/api/";

	@SuppressWarnings("unused")
	private Context context;
	private static String ipServidor = "";
	private static IssFiscalRest instance;

	private Integer id = null;
	private String path = null;

	private IssFiscalRest() {
	}

	public IssFiscalRest(Context context) {
		this.context = context;
		ConfiguracaoPreference preferencias = new ConfiguracaoPreference(
				this.context);
		HashMap<String, String> configuracoes = preferencias.getPreferences();
		// URI = "http://" + configuracoes.get("servidor") + "/api/";
	}

	public static IssFiscalRest getInstance() {
		if (instance == null)
			instance = new IssFiscalRest();
		return instance;
	}

	public IssFiscalRest setPath(String path) {
		this.path = path;
		return instance;
	}

	public IssFiscalRest setId(Integer id) {
		this.id = id;
		return instance;
	}

	@SuppressWarnings("unchecked")
	public <T> ArrayList<T> get(Class<?> clazz)
			throws WebServiceConnectionException {

		if (id != null){
			path += "/" + id;
			id = null;
		}
		
		ArrayList<T> objects = new ArrayList<T>();

		try {

			String[] json = WebService.getInstance().get(URI + path);

			Gson gson = new Gson();
			JsonParser parser = new JsonParser();
			JsonArray array = parser.parse(json[1]).getAsJsonArray();
			
			for (JsonElement jsonElement : array) {
				objects.add((T) gson.fromJson(jsonElement, clazz));
			}

		} catch (Exception e) {
			throw new WebServiceConnectionException(e);
		}

		return objects;
	}

	public String[] post(Object objeto, String path) {
		Gson gson = new Gson();
		String json = gson.toJson(objeto);
		String[] resposta = WebService.getInstance().post(URI + path, json);

		return resposta;
	}

	public static String getIpServidor(Context context) {
		SharedPreferences mPrefs = context.getSharedPreferences("configuracao",
				Context.MODE_PRIVATE);
		ipServidor = mPrefs.getString("IP_SERVIDOR", "");
		ipServidor += ":" + mPrefs.getString("PORTA_SERVIDOR", "");
		return ipServidor;
	}
}