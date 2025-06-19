package br.com.luciano.BuscadorCEP.controller;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.luciano.BuscadorCEP.client.ViaCepClient;
import br.com.luciano.BuscadorCEP.model.Endereco;

@Controller
@RequestMapping("/buscador")
public class BuscadorCEPController {
	
	/* @RequestParam:
	 * Serve para capturar parâmetros de consulta (query string) — 
	 * aqueles que vêm na URL com ?param=valor.
	 * 
	 * @PathVariable:
	 * Serve para capturar parte do caminho da URL (path) — quando a variável está na própria URL.
	 * */
	
	@GetMapping("")
	public ModelAndView buscadorCep(@RequestParam(required = false) String cep) throws ClientProtocolException, IOException {
		ModelAndView mv = new ModelAndView("buscador");
		
		if (cep != null && !cep.isEmpty()) {
			try {
				Endereco endereco = ViaCepClient.buscarCep(cep);
				if (endereco.getCep() == null) {
					mv.addObject("erro", "CEP inválido ou não encontrado!");
					mv.addObject("endereco", new Endereco());
				} else {
					mv.addObject("endereco", endereco);
				}
			} catch (Exception e) {
				mv.addObject("erro", "Erro ao consultar o CEP!");
				mv.addObject("endereco", new Endereco());
			}
		}else {
			mv.addObject("endereco", new Endereco());
		}
		return mv;
	}
	
}
