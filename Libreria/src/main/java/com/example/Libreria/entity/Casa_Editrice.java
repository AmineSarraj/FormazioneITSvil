package com.example.Libreria.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/* 
*@author Sarraj Amine
*/

@Entity
@Table(name="CasaEditrice")

public class Casa_Editrice {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long id;
	public Casa_Editrice(Long id, String nome, List<Libro> libri) {
		super();
		this.id = id;
		this.nome = nome;
		this.libri = libri;
	}
	public String nome;
	
	public Casa_Editrice(String nome) {
		super();
		this.nome = nome;
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
	public List<Libro> getLibri() {
		return libri;
	}
	public void setLibri(List<Libro> libri) {
		this.libri = libri;
	}
	public Casa_Editrice() {
		super();
	}
	
	public Casa_Editrice(String nome, List<Libro> libri) {
		super();
		this.nome = nome;
		this.libri = libri;
	}
	
	@OneToMany(mappedBy = "casaEditrice", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Libro> libri;

}
