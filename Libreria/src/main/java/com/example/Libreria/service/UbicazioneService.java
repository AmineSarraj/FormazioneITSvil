package com.example.Libreria.service;

import com.example.Libreria.DTO.UbicazioneDTO;
import com.example.Libreria.entity.Ubicazione;


/* 
*@author Sarraj Amine
*/

public interface UbicazioneService {
	UbicazioneDTO saveUbicazione(Long id,String posizione,String scompartimento);
	UbicazioneDTO updateUbicazione(Long id,String posizione,String scompartimento);
	void deleteByIdUbicazione(Long id);
}
