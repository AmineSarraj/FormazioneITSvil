package com.example.Libreria.service;

import java.sql.Date;
import java.util.List;

import com.example.Libreria.DTO.LibroDTO;
import com.example.Libreria.entity.Casa_Editrice;
import com.example.Libreria.entity.Libro;

/* 
*@author Sarraj Amine
*/

public interface LibroService  {
	LibroDTO getByTitolo(String TitoloLibro);
	LibroDTO getByIdLibro(Long id);
	LibroDTO saveLibro(LibroDTO l);
	void deleteByIdLibro(Long id);
	List<LibroDTO> getByDateGreaterThan(Date dataDiRiferimento);
	List<LibroDTO> getByTitoloContainingIgnoreCase(String condition);
}
