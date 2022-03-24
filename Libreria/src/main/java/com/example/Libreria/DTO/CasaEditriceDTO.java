package com.example.Libreria.DTO;

import java.util.List;
import java.util.Set;

import com.example.Libreria.entity.Libro;

/* 
*@author Sarraj Amine
*/
public class CasaEditriceDTO {
	public Long id;
	public String nome;
	private List <String> libri;
	
	public CasaEditriceDTO(Long id, String nome, List<String> libri) {
		super();
		this.id = id;
		this.nome = nome;
		this.libri = libri;
	}

	public CasaEditriceDTO(String nome, List<String> libri) {
		super();
		this.nome = nome;
		this.libri = libri;
	}

	public CasaEditriceDTO() {
		super();
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

	public List<String> getLibri() {
		return libri;
	}

	public void setLibri(List<String> libri) {
		this.libri = libri;
	}

	public CasaEditriceDTO(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	
	

}



