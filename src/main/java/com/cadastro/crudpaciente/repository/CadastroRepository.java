package com.cadastro.crudpaciente.repository;

import org.springframework.data.repository.CrudRepository;

import com.cadastro.crudpaciente.model.Cadastro;

public interface CadastroRepository extends CrudRepository<Cadastro, String>{
	Cadastro findById(Long id);

}
