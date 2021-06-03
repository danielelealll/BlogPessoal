package org.generation.blogPessoal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

@Entity // anotação responsável pela compreensão do jpa de que isso será uma taabela e realizará suas atribuições;
@Table(name = "tb_usuario") // anotação para que a nossa tabela gerada e criada tenha um nome em nossa base de dados;
public class Usuario {
	
	@Id // responsável pela anotação de ID
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull // anotação para não receber nenhum tipo de nome nulo
	@Size(min = 2, max = 100) // anotação para definição de tamanho nome
	private String nome;
	
	@NotNull
	@Size(min = 5, max = 100) // anotação para definição de tamanho de usuario
	private String usuario;
	@NotNull
	@Size(min = 5, max = 100) // anotação para definição de tamanho de senhha
	private String senha;
	
	private String foto;
	
	private String tipo;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE) //1 para-muitos; mapeando o atributo "tema","Cascade".Este Cascadetype mostra que se algo for alterado e apagado, todas as postagens sofrerão alterações. Assim como se deletar, as postagens serão apagadas
	@JsonIgnoreProperties("usuario")
	private List<Postagem> postagem;
	
	// gerado por getters and setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public List<Postagem> getPostagem() {
		return postagem;
	}
	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}
	
	
}
