package org.generation.blogPessoal.repository;

import java.util.List;

import org.generation.blogPessoal.model.Tema;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TemaRepository extends JpaRepository<Tema, Long> {  //extendendo  comandos
	public List<Tema> findAllByDescricaoContainingIgnoreCase(String descricao); // pegando de acordo com a digitação do cliente, ele vai pegar tudo que contém a palavra (ignorando maiúsuculo e minúsculo) e ele vai utilizar como parâmetro o atributo de descrição

}
