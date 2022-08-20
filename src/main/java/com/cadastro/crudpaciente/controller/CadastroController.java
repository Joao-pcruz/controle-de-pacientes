package com.cadastro.crudpaciente.controller;

import javax.validation.Valid;
import java.sql.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.spring5.expression.Mvc;

import com.cadastro.crudpaciente.model.Cadastro;
import com.cadastro.crudpaciente.repository.CadastroRepository;

@Controller
public class CadastroController {
	
	@Autowired
	private CadastroRepository cadastroRepository;
	
	
	//Método para retornar um formulário
	@RequestMapping(value="/cadastro", method=RequestMethod.GET)
	public String form() {
		return "Eventos/formCadastro";
	}
	
	//Método que os dados do cadastro no BD
	@RequestMapping(value="/cadastro", method=RequestMethod.POST)
	public String form(@Valid Cadastro cadastro, BindingResult result, RedirectAttributes atributes) {  //método para validação
		if (result.hasErrors()) //Condição que verifica se todos os dados estão corretos.
		return "redirect:/cadastro";
		
	
	cadastroRepository.save(cadastro);
	atributes.addFlashAttribute("flashMessage", "Paciente Cadastrado com Sucesso!");
	atributes.addFlashAttribute("flashType", "success");
	return "redirect:/lista";
	
	}
	@RequestMapping("/lista") //Gerar lista pacientes
	public ModelAndView listaPacientes() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Cadastro> lista = cadastroRepository.findAll();
		mv.addObject("listagem", lista);
		return mv;
	}
	
	@RequestMapping("/deletar") //Método para deletar 
	public String deletarPaciente(long id) {
		Cadastro cadastro = cadastroRepository.findById(id);
		cadastroRepository.delete(cadastro);
		return "redirect:/lista";
	}
	
	}