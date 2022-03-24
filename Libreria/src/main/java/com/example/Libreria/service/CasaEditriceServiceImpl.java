package com.example.Libreria.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Libreria.DAO.CasaEditriceRepository;
import com.example.Libreria.DAO.LibroRepository;
import com.example.Libreria.DTO.CasaEditriceDTO;
import com.example.Libreria.entity.Autore;
import com.example.Libreria.entity.Casa_Editrice;
import com.example.Libreria.entity.Libro;

/* 
*@author Sarraj Amine
*/

@Service
public class CasaEditriceServiceImpl implements CasaEditriceService {
	@Autowired
    private CasaEditriceRepository casaEditriceRepository;
	@Autowired
    private LibroRepository libroRepository;
	
	/* 
	*@param prende come input una stringa nome  ;
	*@return una DTO di casa editrice uguale alla casa editrice aggiunta ;
	*/
	@Override
    public CasaEditriceDTO saveCasa_Editrice(String name,List<String> titoli) {
		List<String> libriTitolo = new ArrayList<String>();
		List<Libro> libri = new ArrayList<Libro>();
		Casa_Editrice c;
		for (String titolo: titoli) {
			Libro l = libroRepository.findByTitolo(titolo).get();
			libri.add(l);
			libriTitolo.add(l.getTitolo());
			
		}
		
		if(titoli!=null)
			c =new Casa_Editrice (name,libri);
		else 
			c =new Casa_Editrice (name);	
		casaEditriceRepository.save(c);
		CasaEditriceDTO casa_editriceDTO=new CasaEditriceDTO(c.getId(),c.getNome(),libriTitolo);
        return casa_editriceDTO ;
    }
	/* 
	*@param prende come input l'id della casa editrice che vorrei restituire ;
	*@return una DTO di una casa editrice uguale all'autore chi ha questo id;
	*/
	@Override
    public CasaEditriceDTO getByIdCasa_Editrice(Long id) {
		Optional<Casa_Editrice> cCheck=casaEditriceRepository.findById(id);

		if(cCheck.isPresent()) {
		
		Casa_Editrice c=cCheck.get();

		CasaEditriceDTO casa_editriceDTO=new CasaEditriceDTO(c.getId(),c.getNome());
        return casa_editriceDTO ;}
		else return null;

    }
	/* 
	*@param prende come input il nome della casa editrice che vorrei restituire ;
	*@return una DTO di casa editrice uguale all'autore chi ha questo nome ;
	*/
	@Override
	public CasaEditriceDTO getByNomeCasa_Editrice(String name) {
		// TODO Auto-generated method stub
		Optional<Casa_Editrice> cCheck=casaEditriceRepository.findByNome(name);
		if(cCheck.isPresent()) {
		Casa_Editrice c=cCheck.get();
		CasaEditriceDTO casa_editriceDTO=new CasaEditriceDTO(c.getId(),c.getNome());
        return casa_editriceDTO ;}
		else return null;
	}
	/* 
	*@param prende come input una stringa nome e  un id ;
	*@return una DTO di autore uguale alla casa editrice modificato ;
	*/
	@Override
	public CasaEditriceDTO updateCasa_Editrice(Long id,String name) {
		// TODO Auto-generated method stub
		Casa_Editrice c=casaEditriceRepository.findById(id).get();
		c.setNome(name);
		 casaEditriceRepository.save(c);
		 CasaEditriceDTO casa_editriceDTO=new CasaEditriceDTO(c.getId(),c.getNome());
	     return casa_editriceDTO ;

		
	}
	/* 
	*@param prende come input il id della casa editrice che vorrei eliminare ;
	*/
	@Override
	public void deleteByIdCasa_Editrice(Long id) {
		// TODO Auto-generated method stub
		Optional<Casa_Editrice> cCheck=casaEditriceRepository.findById(id);
		if(cCheck.isPresent()) {
		Casa_Editrice c=cCheck.get();
		casaEditriceRepository.delete(c);}
		
	}

	
}
