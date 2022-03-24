package com.example.Libreria.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
/* 
*@author Sarraj Amine
*/

@Entity
@Table(name = "Ubicazione")
public class Ubicazione {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
	private String posizione;
	private String scompartimento;

	
	public Ubicazione(String posizione, String scompartimento, Libro libro) {
		super();
		this.posizione = posizione;
		this.scompartimento = scompartimento;
		this.libro = libro;
	}

	public String getPosizione() {
		return posizione;
	}

	public void setPosizione(String posizione) {
		this.posizione = posizione;
	}

	public String getScompartimento() {
		return scompartimento;
	}

	public void setScompartimento(String scompartimento) {
		this.scompartimento = scompartimento;
	}

	@OneToOne(mappedBy = "ubicazione")
    private Libro libro;

	public Ubicazione() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public Ubicazione(Libro libro) {
		super();
		this.libro = libro;
	}

	public Ubicazione(Long id, Libro libro) {
		super();
		this.id = id;
		this.libro = libro;
	}
	
	

}
