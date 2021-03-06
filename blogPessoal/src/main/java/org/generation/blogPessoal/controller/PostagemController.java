package org.generation.blogPessoal.controller;

import org.generation.blogPessoal.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.generation.blogPessoal.model.Postagem;


@RestController // informa para o spring que esta classe se trata de um controle
@RequestMapping ("/postagens")// por qual url esta classe será acessada
@CrossOrigin(origins = "*", allowedHeaders = "*") // a classe aceitará requisições de qualquer origem
public class PostagemController { // classe
	
	@Autowired// responsabilidade de instanciamento de interface para a anotação spring. Será acessado a partir do controller
	private PostagemRepository repositoty; // serviço 
	
	@GetMapping // mostrando que sempre que vir uma requisição externa e se o método for get, ele vai disparar o metodo get mapping
	public ResponseEntity<List<Postagem>> GetAll() { //tipo de método find all, pegar todos os dados
		return ResponseEntity.ok(repositoty.findAll()); ////find all, encontrar todos os dados
	}
	
	// para uma busca por ID;
	
	@GetMapping("/{id}") // método http enviado para a nossa api
	public ResponseEntity<Postagem> GetById(@PathVariable long id) { // método pegar por id //Pathvariabe: caminho  da variável da url
		return repositoty.findById(id) // esse método pode devolver tanto postagem, quanto um not found  caso o objeto não exista ou exista um erro na requisição
				.map(resp -> ResponseEntity.ok(resp)) // caso capture resposta positiva, devolver como recurso na requisição
				.orElse(ResponseEntity.notFound().build()); // caso n tiver dados 
		//assim que for feita alguma requisição do tipo get em "/postagens", e se passar um atributo, no caso o id, vai ser acessado este método que irá capturar qual é a variável que estamos recebendo dentro do @pathvariable, assim, retornando a interface injetada com @autowired
	}
	
	// para uma busca por titulo
	
	@GetMapping("/titulo/{titulo}") // subcaminho e atributo, para não dar ambiguidade de caminho com o acima
	public ResponseEntity<List<Postagem>> GetByTitulo(@PathVariable String titulo){  // método pegar por titulo
		return ResponseEntity.ok(repositoty.findAllByTituloContainingIgnoreCase(titulo)); // find all, encontrar todos os dados referentes a titulo e não discernir por letra maiúscula ou minúscula
	}
	
	@PostMapping ////Anotação para mapear POST solicitações de HTTP em métodos de tratamento específicos.
	public ResponseEntity<Postagem> post (@RequestBody Postagem postagem) { //objeto grande, com todos os atributos inseridos a ele
		return ResponseEntity.status(HttpStatus.CREATED).body(repositoty.save(postagem)); // end point de postagem
	}
	
	@PutMapping //Anotação para mapear PUT solicitações de HTTP em métodos de tratamento específicos.
	public ResponseEntity<Postagem> put (@RequestBody Postagem postagem) { //objeto grande, com todos os atributos inseridos a ele
		return ResponseEntity.status(HttpStatus.OK).body(repositoty.save(postagem)); // end point de postagem
	}
	
	@DeleteMapping("/{id}") //anotação mapeia solicitações HTTP DELETE para métodos de tratamento específicos.
	public void delete(@PathVariable long id) {// retornar status ok, não retorna nada
		repositoty.deleteById(id); // pedindo no repositório o delete pelo ID.
		
	}
}

