package br.com.luciano.BuscadorCEP.client;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;

import com.google.gson.Gson;

import br.com.luciano.BuscadorCEP.model.Endereco;

public class ViaCepClient {
	
	public static Endereco buscarCep(String cep) throws ClientProtocolException, IOException {
		
		String url = "https://viacep.com.br/ws/"+cep+"/json/";
		
		String jsonResponse = Request.Get(url)
				.connectTimeout(1000)
				.socketTimeout(1000)
				.execute()
				.returnContent()// obtém contéudo da resposta HTTP do servidor
				.asString();
		
		Gson gson = new Gson();
		return gson.fromJson(jsonResponse, Endereco.class);
	}
	
	
}
