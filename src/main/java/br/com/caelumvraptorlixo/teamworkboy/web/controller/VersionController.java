package br.com.caelumvraptorlixo.teamworkboy.web.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Result;

@Controller
public class VersionController {
	
	@Inject
	private Result result;
	
	public void get() {
	
		result.include("version", "Smiggle");
	}
}
