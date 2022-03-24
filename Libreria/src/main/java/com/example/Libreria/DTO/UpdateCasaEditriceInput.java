package com.example.Libreria.DTO;

public class UpdateCasaEditriceInput {
	public String nome;
	public Long id;

	public UpdateCasaEditriceInput(String nome, Long id) {
		super();
		this.nome = nome;
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UpdateCasaEditriceInput(String nome) {
		super();
		this.nome = nome;
	}

	public UpdateCasaEditriceInput() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
