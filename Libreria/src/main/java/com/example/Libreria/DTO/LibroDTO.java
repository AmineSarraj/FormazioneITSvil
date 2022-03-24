package com.example.Libreria.DTO;

import java.sql.Date;
import java.util.List;

import com.example.Libreria.entity.Autore;
import com.example.Libreria.entity.Casa_Editrice;
import com.example.Libreria.entity.Ubicazione;

/* 
*@author Sarraj Amine
*/

public class LibroDTO {
	private Long id;
	private String titolo;
	private String trama;
	private Date datauscita;
	private String ubicazione;
	List<String> autore;
	private String casaEditrice;
	
	
	public LibroDTO(String titolo, String trama, Date datauscita, String ubicazione, List<String> autore,
			String casaEditrice) {
		super();
		this.titolo = titolo;
		this.trama = trama;
		this.datauscita = datauscita;
		this.ubicazione = ubicazione;
		this.autore = autore;
		this.casaEditrice = casaEditrice;
	}


	public LibroDTO() {
		super();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitolo() {
		return titolo;
	}


	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}


	public String getTrama() {
		return trama;
	}


	public void setTrama(String trama) {
		this.trama = trama;
	}


	public Date getDatauscita() {
		return datauscita;
	}


	public void setDatauscita(Date datauscita) {
		this.datauscita = datauscita;
	}


	public String getUbicazione() {
		return ubicazione;
	}


	public void setUbicazione(String ubicazione) {
		this.ubicazione = ubicazione;
	}


	public List<String> getAutore() {
		return autore;
	}


	public void setAutore(List<String> autore) {
		this.autore = autore;
	}


	public String getCasaEditrice() {
		return casaEditrice;
	}


	public void setCasaEditrice(String casaEditrice) {
		this.casaEditrice = casaEditrice;
	}


	public LibroDTO(String titolo, String trama, Date datauscita, List<String> autore, String casaEditrice) {
		super();
		this.titolo = titolo;
		this.trama = trama;
		this.datauscita = datauscita;
		this.autore = autore;
		this.casaEditrice = casaEditrice;
	}


	public LibroDTO(Long id, String titolo, String trama, Date datauscita, List<String> autore, String casaEditrice) {
		super();
		this.id = id;
		this.titolo = titolo;
		this.trama = trama;
		this.datauscita = datauscita;
		this.autore = autore;
		this.casaEditrice = casaEditrice;
	}
	
	
	
	

}
