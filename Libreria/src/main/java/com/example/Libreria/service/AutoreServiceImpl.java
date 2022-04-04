package com.example.Libreria.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Libreria.DAO.AutoreRepository;
import com.example.Libreria.DAO.LibroRepository;
import com.example.Libreria.DTO.AutoreDTO;
import com.example.Libreria.entity.Autore;
import com.example.Libreria.entity.Libro;

/* 
 *@author Sarraj Amine
 */

@Service
public class AutoreServiceImpl implements AutoreService {
	@Autowired
    private LibroRepository libroRepository;
	@Autowired
    private AutoreRepository autoreRepository;
	
	/* 
	*@param prende come input 2 stringhe nome e cognome e una lista di ids ;
	*@return una DTO di autore uguale all'autore aggiunto a solo differencia che prende gli ids e i titoli dei libri al posto dell'oggetto libro
	*/
	@Override
	public AutoreDTO saveAutore(String nome, String cognome, List<Long> ids) {
		// TODO Auto-generated method stub
		List<Libro> libri = new ArrayList<Libro>();
		List<String> libriTitolo = new ArrayList<String>();
		Autore a ;
		if(ids!=null) {
			for (Long id: ids) {
				Libro l = libroRepository.findById(id).get();
				libri.add(l);
				libriTitolo.add(l.getTitolo());
				
			}}
		
			a=new Autore(nome,cognome,libri);
		
		autoreRepository.save(a);
		AutoreDTO aDTO=new AutoreDTO(a.getId(),a.getNome(),a.getCognome(),libriTitolo,ids);
		return aDTO;
	}
	
	/* 
	*@param prende come imput 2 stringhe nome e cognome e un id ;
	*@return una DTO di autore uguale all'autore modificato a solo differencia che prende gli ids e i titoli dei libri al posto dell'oggetto libro
	*/
	@Override
	public AutoreDTO updateAutore(String nome, String cognome,Long id) {
		// TODO Auto-generated method stub
		Autore a=autoreRepository.findById(id).get();
		a.setNome(nome);
		a.setCognome(cognome);
		autoreRepository.save(a);
		List<Long> libriIds = new ArrayList<Long>();
		List<String> libriTitolo = new ArrayList<String>();
		
		for (Libro l: a.getLibri()) {
			libriIds.add(l.getId());
			libriTitolo.add(l.getTitolo());
			
		}
		AutoreDTO aDTO=new AutoreDTO(a.getId(),a.getNome(),a.getCognome(),libriTitolo,libriIds);
		return aDTO;


	}

	/* 
	*@param prende come input l'id dell'autore che vorrei restituire ;
	*@return una DTO di autore uguale all'autore chi ha questo id ;
	*/
	@Override
	public AutoreDTO getByIdAutore(Long id) {
		// TODO Auto-generated method stub
		Optional<Autore> aCheck=autoreRepository.findById(id);
		if(aCheck.isPresent()) {
			Autore a=aCheck.get();
			List<Libro> libri = libroRepository.findByAutore(a).get();
			List<Long> libriIds = new ArrayList<Long>();
			List<String> libriTitolo = new ArrayList<String>();
			
			for (Libro l: libri) {
				libriIds.add(l.getId());
				libriTitolo.add(l.getTitolo());
				
			}
			
			AutoreDTO aDTO=new AutoreDTO(a.getId(),a.getNome(),a.getCognome(),libriTitolo,libriIds);
			return aDTO;}
		else 
			return null;

	}

	/* 
	*@param prende come input il nome dell'autore che vorrei restituire ;
	*@return una DTO di autore uguale all'autore chi ha questo nome;
	*/
	@Override
	public AutoreDTO getByNomeAutore(String name) {
		// TODO Auto-generated method stub
		List<Libro> libri=new ArrayList<>();
		List<Long> libriIds = new ArrayList<Long>();
		List<String> libriTitolo = new ArrayList<String>();

		Optional <Autore> aCheck=autoreRepository.findByNome(name);
		if(aCheck.isPresent()) {
			Autore a=aCheck.get();
			
			Optional<List<Libro>> Checklibri = libroRepository.findByAutore(a);
			System.out.println(Checklibri);
			if(!Checklibri.isPresent()) { libri=null;  libriIds=null;   libriTitolo=null;   }
			else {
			libri = Checklibri.get();
			
			
			for (Libro l: libri) {
				libriIds.add(l.getId());
				libriTitolo.add(l.getTitolo());
				
			}}
			AutoreDTO aDTO=new AutoreDTO(a.getId(),a.getNome(),a.getCognome(),libriTitolo,libriIds);
			return aDTO;}
		
		else return null;
		
	}
	/* 
	*@param prende come input il id dell'autore che vorrei eliminare ;
	*/
	@Override
	public void deleteByIdAutore(Long id) {
		// TODO Auto-generated method stub
		Optional<Autore> aCheck=autoreRepository.findById(id);
		if(aCheck.isPresent()) {
		Autore a=aCheck.get();
		autoreRepository.delete(a);
		}
		
	}

	/*@Override
	public AutoreDTO saveAutoreWithoutBook(String nome, String cognome) {
		// TODO Auto-generated method stub
			Autore a ;
		
			a=new Autore(nome,cognome);
		
		autoreRepository.save(a);
		AutoreDTO aDTO=new AutoreDTO(a.getId(),a.getNome(),a.getCognome());
		return aDTO;
	}*/

	
	

}
