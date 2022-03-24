package com.example.Libreria.entity;



import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
/* 
*@author Sarraj Amine
*/

@Entity
@Table(name="Libro")
public class Libro {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
	private String titolo;
	private String trama;
	private Date datauscita;
	
	
	
	public Libro(String titolo, String trama, Date datauscita, Ubicazione ubicazione, List<Autore> autore,
			Casa_Editrice casaEditrice) {
		super();
		this.titolo = titolo;
		this.trama = trama;
		this.datauscita = datauscita;
		this.ubicazione = ubicazione;
		this.autore = autore;
		this.casaEditrice = casaEditrice;
	}
	public Libro(String titolo, String trama, Date datauscita, List<Autore> autore, Casa_Editrice casaEditrice) {
		super();
		this.titolo = titolo;
		this.trama = trama;
		this.datauscita = datauscita;
		this.autore = autore;
		this.casaEditrice = casaEditrice;
	}
	public Libro(String titolo, String trama, Date data_uscita, Casa_Editrice casaEditrice) {
		super();
		this.titolo = titolo;
		this.trama = trama;
		this.datauscita = data_uscita;
		this.casaEditrice = casaEditrice;
	}
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Ubicazione_id", referencedColumnName = "id")
	private Ubicazione ubicazione;
	@ManyToMany
	@JoinTable(
			  name = "Libro_Autori", 
			  joinColumns = @JoinColumn(name = "libro_id"), 
			  inverseJoinColumns = @JoinColumn(name = "autore_id"))
    List<Autore> autore;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "libro_id", nullable = false)
	@JsonBackReference
    private Casa_Editrice casaEditrice;
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
	public Date getData_uscita() {
		return datauscita;
	}
	public void setData_uscita(Date data_uscita) {
		this.datauscita = data_uscita;
	}
	public Ubicazione getUbicazione() {
		return ubicazione;
	}
	public void setUbicazione(Ubicazione ubicazione) {
		this.ubicazione = ubicazione;
	}
	public List<Autore> getAutore() {
		return autore;
	}
	public void setAutore(List<Autore> autore) {
		this.autore = autore;
	}
	public Casa_Editrice getCasaEditrice() {
		return casaEditrice;
	}
	public void setCasaEditrice(Casa_Editrice casaEditrice) {
		this.casaEditrice = casaEditrice;
	}
	public Libro() {
		super();
	}
	public Libro(Long id, String titolo, String trama, Date data_uscita, Ubicazione ubicazione, List<Autore> autore,
			Casa_Editrice casaEditrice) {
		super();
		this.id = id;
		this.titolo = titolo;
		this.trama = trama;
		this.datauscita = data_uscita;
		this.ubicazione = ubicazione;
		this.autore = autore;
		this.casaEditrice = casaEditrice;
	}
	public Libro(Long id, String titolo, String trama) {
		super();
		this.id = id;
		this.titolo = titolo;
		this.trama = trama;
	}
	public Libro(String titolo, String trama, Date data_uscita) {
		super();
		this.titolo = titolo;
		this.trama = trama;
		this.datauscita = data_uscita;
	}
	public Libro(String titolo, String trama) {
		super();
		this.titolo = titolo;
		this.trama = trama;
	}
	public Libro(String titolo, String trama, Casa_Editrice casaEditrice) {
		super();
		this.titolo = titolo;
		this.trama = trama;
		this.casaEditrice = casaEditrice;
	}
	
}
