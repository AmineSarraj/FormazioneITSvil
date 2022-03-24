package com.example.Libreria.DTO;

import com.example.Libreria.entity.Libro;

/* 
*@author Sarraj Amine
*/
public class UbicazioneDTO {
	private Long id;
	private String posizione;
	private String scompartimento;
	private String libro;
	
	
	
	public UbicazioneDTO(Long id, String posizione, String scompartimento, String libro) {
		super();
		this.id = id;
		this.posizione = posizione;
		this.scompartimento = scompartimento;
		this.libro = libro;
	}
	public UbicazioneDTO(Long id, String posizione, String scompartimento) {
		super();
		this.id = id;
		this.posizione = posizione;
		this.scompartimento = scompartimento;
	}
	public UbicazioneDTO() {
		super();
	}
	public UbicazioneDTO(String posizione, String scompartimento, String libro) {
		super();
		this.posizione = posizione;
		this.scompartimento = scompartimento;
		this.libro = libro;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getLibro() {
		return libro;
	}
	public void setLibro(String libro) {
		this.libro = libro;
	}

}
