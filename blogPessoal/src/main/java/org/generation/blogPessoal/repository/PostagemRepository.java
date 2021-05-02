package org.generation.blogPessoal.repository;

import java.util.List;

import org.generation.blogPessoal.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository // anotação que indica ao spring que a classe se trata de uma classe de repositório
public interface PostagemRepository extends JpaRepository<Postagem, Long> { // tipo de interface a se estar trabalhando
	public List<Postagem> findAllByTituloContainingIgnoreCase (String titulo);// métodos que usam de conceitos para realizar consultas de auto construção 
							// neste caso, foi a busca pelo título, não sendo ele exato, contendo as informações sem levar em conta maiúscula e minúscula
}
