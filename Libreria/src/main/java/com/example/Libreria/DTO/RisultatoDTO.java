package com.example.Libreria.DTO;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;

@JsonInclude(Include.NON_ABSENT)
public class RisultatoDTO<T> implements Serializable{

	private static final long serialVersionUID = -2329082239482963382L;
	
	@ApiModelProperty(required=true, notes="", position=1)
	protected boolean success;
	
	@ApiModelProperty(required=true, notes="Codifica dell'esito dell'operatività", position=2)
	protected int code;
	
	@ApiModelProperty(required=true, notes="Descrizione testuale dell'operatività", position=3)
	protected String descrizione;
	
	@ApiModelProperty(required=false, notes="Eventuale payload di risposta", position=4)
	protected T data;
	
	@ApiModelProperty(required=false, notes="Eventuale payload di risposta", position=5)
	protected List<T> dati;
	
	public List<T> getDati() {
		return dati;
	}

	public void setDati(List<T> dati) {
		this.dati = dati;
	}

	public RisultatoDTO(){
		clear();
	}
	
	public void clear(){
		this.success = false;
		this.code = 0;
		this.data = null;
		this.descrizione = null;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public RisultatoDTO<T> success(int code) {
		this.success = true;
		this.code= code;
		return this;
	}
	
	public RisultatoDTO<T> error(String errore,int code) {
		this.success = false;
		this.descrizione = errore;
		this.code = code;
		this.data = null;
		return this;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public RisultatoDTO<T> setData(T data) {
		this.data = data;
		return this;
	}
	

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
}

