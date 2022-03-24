package com.example.Libreria.DTO;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class InsertAutoreWithoutLibriDTO {
	//@NotNull(message = "nome may not be null")
	//@NotEmpty
	private String nome;
	//@NotNull(message = "cognome may not be null")
	//@NotEmpty
	private String cognome;
	List<Long> libriIds;
	
	public List<Long> getLibriIds() {
		return libriIds;
	}
	public void setLibriIds(List<Long> libriIds) {
		this.libriIds = libriIds;
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
	public InsertAutoreWithoutLibriDTO(String nome, String cognome) {
		super();
		this.nome = nome;
		this.cognome = cognome;
	}
	public InsertAutoreWithoutLibriDTO() {
		super();
	}


}
