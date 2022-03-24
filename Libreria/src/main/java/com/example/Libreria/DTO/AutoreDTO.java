package com.example.Libreria.DTO;

import java.util.List;

import com.example.Libreria.entity.Libro;
import com.fasterxml.jackson.annotation.JsonProperty;

/* 
*@author Sarraj Amine
*/
public class AutoreDTO {
	//@JsonProperty(value = "identifier")
	private Long id;
	private String nome;
	private String cognome;
	List<String> libri;
	List<Long> libriIds;
	
	public AutoreDTO(Long id, String nome, String cognome) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
	}
	public AutoreDTO(Long id, String nome, String cognome, List<String> libri, List<Long> libriIds) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.libri = libri;
		this.libriIds = libriIds;
	}
	public AutoreDTO(String nome, String cognome) {
		super();
		this.nome = nome;
		this.cognome = cognome;
	}
	public List<Long> getLibriIds() {
		return libriIds;
	}
	public void setLibriIds(List<Long> libriIds) {
		this.libriIds = libriIds;
	}
	public AutoreDTO() {
		super();
	}
	public AutoreDTO(String nome, String cognome, List<String> libri) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.libri = libri;
	}
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
	public List<String> getLibri() {
		return libri;
	}
	public void setLibri(List<String> libri) {
		this.libri = libri;
	}
	
	

}
