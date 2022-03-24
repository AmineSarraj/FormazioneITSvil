package com.example.Libreria.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Libreria.DAO.LibroRepository;
import com.example.Libreria.DAO.UbicazioneRepository;
import com.example.Libreria.DTO.UbicazioneDTO;
import com.example.Libreria.entity.Casa_Editrice;
import com.example.Libreria.entity.Libro;
import com.example.Libreria.entity.Ubicazione;


/* 
*@author Sarraj Amine
*/

@Service
public class UbicazioneServiceImpl implements UbicazioneService {
	@Autowired
    private UbicazioneRepository ubicazioneRepository;
	@Autowired
    private LibroRepository libroRepository;

	/* 
	*@param prende come input un id e 2 stringhe posizione e scompartimento  ;
	*@return una DTO di ubicazione uguale all'ubicazione aggiunta ;
	*/
	
	@Override
	public UbicazioneDTO saveUbicazione(Long id,String posizione,String scompartimento) {
		// TODO Auto-generated method stub
		Libro l=libroRepository.findById(id).get();
		Ubicazione u =new Ubicazione (scompartimento,posizione,l);
		ubicazioneRepository.save(u);
		UbicazioneDTO uDTO =new UbicazioneDTO (u.getId(),scompartimento,posizione,l.getTitolo());
		return uDTO;
		
	}

	/* 
	*@param prende come input 2 stringhe posizione e un scompartimento e  un id ;
	*@return una DTO di ubicazione uguale all'ubicazione modificato ;
	*/
	@Override
	public UbicazioneDTO updateUbicazione(Long id, String posizione, String scompartimento) {
		// TODO Auto-generated method stub
		Ubicazione u=ubicazioneRepository.findById(id).get();
		u.setPosizione(posizione);
		u.setScompartimento(scompartimento);
		ubicazioneRepository.save(u);
		UbicazioneDTO uDTO =new UbicazioneDTO (u.getId(),scompartimento,posizione,u.getLibro().getTitolo());
		return uDTO;

	}

	/* 
	*@param prende come input il id del ubicazione che vorrei eliminare ;
	*/
	@Override
	public void deleteByIdUbicazione(Long id) {
		// TODO Auto-generated method stub
		Ubicazione u=ubicazioneRepository.findById(id).get();
		ubicazioneRepository.delete(u);
		System.out.println("delete fatto dell ubicazione "+id);
	}
}
