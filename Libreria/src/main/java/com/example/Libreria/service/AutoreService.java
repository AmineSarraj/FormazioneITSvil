package com.example.Libreria.service;

import java.util.List;
import java.util.Set;

import com.example.Libreria.DTO.AutoreDTO;
import com.example.Libreria.entity.Autore;
import com.example.Libreria.entity.Casa_Editrice;
import com.example.Libreria.entity.Libro;
import com.example.Libreria.entity.Ubicazione;

/* 
*@author Sarraj Amine
*/

public interface AutoreService {
	AutoreDTO saveAutore(String nome,String cognome,List<Long> ids);
	AutoreDTO saveAutoreWithoutBook(String nome,String cognome);
	AutoreDTO updateAutore(String nome,String cognome,Long id);
	AutoreDTO getByIdAutore(Long id);
	AutoreDTO getByNomeAutore(String name);
	void deleteByIdAutore(Long id);
	
}
