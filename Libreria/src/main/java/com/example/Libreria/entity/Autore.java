package com.example.Libreria.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/* 
*@author Sarraj Amine
*/
@Entity
@Table(name="Autore")
public class Autore {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
	private String nome;
	private String cognome;
	
	
	
	public Autore(String nome, String cognome) {
		super();
		this.nome = nome;
		this.cognome = cognome;
	}
	@ManyToMany
	@JoinTable(
			  name = "Libro_Autori", 
			  joinColumns = @JoinColumn(name = "autore_id"), 
			  inverseJoinColumns = @JoinColumn(name = "libro_id"))
	List<Libro> libri;
	
	public Autore(String nome, String cognome, List<Libro> libri) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.libri = libri;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public void setLibri(List<Libro> libri) {
		this.libri = libri;
	}
	public Autore() {
		super();
	}
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getCognome() {
		return cognome;
	}
	public List<Libro> getLibri() {
		return libri;
	}
}
