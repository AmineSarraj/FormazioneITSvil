package com.example.Libreria.service;

import java.util.List;

import com.example.Libreria.DTO.CasaEditriceDTO;
import com.example.Libreria.entity.Casa_Editrice;
import com.example.Libreria.entity.Libro;

/* 
*@author Sarraj Amine
*/

public interface CasaEditriceService {
	CasaEditriceDTO saveCasa_Editrice(String name,List<String> titolo);
	CasaEditriceDTO getByIdCasa_Editrice(Long id);
	CasaEditriceDTO getByNomeCasa_Editrice(String name);
	CasaEditriceDTO updateCasa_Editrice(Long id,String name);
	void deleteByIdCasa_Editrice(Long id);
}
