package org.generation.blogPessoal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.sun.istack.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "tb_tema") // criação de tabela com nome tb_tema
public class Tema { // MODEL
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // aplicação de gerar valor
	private long id;
	
	@NotNull
	private String descricao;
	
	@OneToMany(mappedBy = "tema", cascade = CascadeType.ALL) //1 para-muitos; mapeando o atributo "tema","Cascade".Este Cascadetype mostra que se algo for alterado e apagado, todas as postagens sofrerão alterações. Assim como se deletar, as postagens serão apagadas
	@JsonIgnoreProperties("tema")
	private List<Postagem> postagem;
	
	// gerado por getters and setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Postagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}
	
	
}
