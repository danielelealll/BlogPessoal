package org.generation.blogPessoal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.sun.istack.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// inserção de anotações: anotações servem como parâmetros que colocamos em cimade classes ou propriedades que definem certos tipos de comportamentos para elas

@Entity // anotação indica classe que será entidade do jpa hebernate
@Table(name = "postagem") // anotação que diz que a entidade virará uma tabela dentro do banco de dados e o nome da tabela será de "postagem"
public class Postagem {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) // esse ID irá se comportar na base de dados como um valor gerado. Atributo virará primary key no banco de dados
	private long id; //ID da postagem
	
	@NotNull // não poderá vir título vazio na postagem
	@Size(min = 5, max = 100)  // quantidade de caractere que o cliente conseguirá enviar como título. Valores mínimos e máximos na anotação
	private String titulo;
	
	@NotNull // não poderá vir título vazio na postagem
	@Size(min = 10, max = 500) // 500 caracteres por se tratar de texto
	private String texto;
	
	@Temporal(TemporalType.TIMESTAMP) // indicando ao jpa hibernate que estamos trabalhando com tempo e indicando o tipo de tempo
	private Date date = new java.sql.Date(System.currentTimeMillis()); //assim que passar algum dado por essa classe, irá capturar a data, hora, min, seg e o milésimo de seg que o dado passou por essa classe
	
	@ManyToOne //Anotação de muitos eventos para uma categoria.
	@JsonIgnoreProperties("postagem") // ignora as propriedades lógicas especificadas na serialização e desserialização JSON. É anotado no nível da classe.
	private Tema tema;
	// gerado por getters and setters
	
	@ManyToOne //Anotação de muitos eventos para uma categoria.
	@JsonIgnoreProperties("postagem") // ignora as propriedades lógicas especificadas na serialização e desserialização JSON. É anotado no nível da classe.
	private Usuario usuario;
	

	
	public long getId() { // gerado por getters and setters
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
