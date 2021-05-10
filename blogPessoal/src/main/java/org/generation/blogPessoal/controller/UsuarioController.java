package org.generation.blogPessoal.controller;

import java.util.Optional;

import org.generation.blogPessoal.model.UserLogin;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios") // acessar através do caminho de usuários
@CrossOrigin(origins = "*", allowedHeaders = "*") //aceitação de caminho de orientação
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService; // injetando o UsuarioService, onde contém as nossas regras de negócio para Logar ou cadastrar um usuário
	
	@PostMapping("/logar") // end point que será feita através de uma requisição post
	public ResponseEntity<UserLogin> Autentication(@RequestBody Optional<UserLogin> user) { // estamos recebendo um optional<UsuarioLogin>Classe que criamos apenas para o Request e o Response no Login
		return usuarioService.Logar(user).map(resp -> ResponseEntity.ok(resp)) //EndPointLogar, repare que agora chamamos um Service ao invés de um Repository
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@PostMapping("/cadastrar") // end point que será feita através de uma requisição post
	public ResponseEntity<Usuario> Post(@RequestBody Usuario usuario) {
		return ResponseEntity.status(HttpStatus.CREATED) //chamaremos o service ao invés do Repository para aplicarmos as nossas regras de negócio
				.body(usuarioService.CadastrarUsuario(usuario));
	}

}
