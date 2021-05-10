package org.generation.blogPessoal.repository;

import java.util.Optional;

import org.generation.blogPessoal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	public Optional<Usuario> findByUsuario(String usuario); // resgatar usuário, encontrar por usuário(findBy pelo atributo usuário)
	//  o optional é utilizado por conta de que o atributo pode vir nulo, então optional prepara o find para caso isso acontecer, sendo opicional se acontecer, e se não, retornar nulo
}
