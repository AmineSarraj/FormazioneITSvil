package com.example.Libreria.DTO;

import java.util.List;

public class InsertCasaEditriceInputDTO {
	
	public String nome;
	private List <String> libri;

	public InsertCasaEditriceInputDTO() {
		super();
	}

	public InsertCasaEditriceInputDTO(String nome, List<String> libri) {
		super();
		this.nome = nome;
		this.libri = libri;
	}

	

	public InsertCasaEditriceInputDTO(String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<String> getLibri() {
		return libri;
	}

	public void setLibri(List<String> libri) {
		this.libri = libri;
	}
	
	

}
