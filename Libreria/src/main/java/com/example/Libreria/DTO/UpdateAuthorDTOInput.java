package com.example.Libreria.DTO;

public class UpdateAuthorDTOInput {
	private Long id;
	private String nome;
	private String cognome;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public UpdateAuthorDTOInput(Long id, String nome, String cognome) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
	}
	public UpdateAuthorDTOInput() {
		super();
	}
	
	

}
