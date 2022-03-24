package com.example.Libreria.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Libreria.DTO.AutoreDTO;
import com.example.Libreria.DTO.UbicazioneDTO;
import com.example.Libreria.entity.Casa_Editrice;
import com.example.Libreria.entity.Libro;
import com.example.Libreria.entity.Ubicazione;
import com.example.Libreria.service.UbicazioneService;

/* 
*@author Sarraj Amine
*/
@RestController
@RequestMapping("Ubicazione")
public class UbicazioneRest {
	@Autowired
    private UbicazioneService ubicazioneService;
	
	/* 
	*@param libroDTO prende un DTO di un libro come requestbody ;
	*@return result chi un DTO di libro che lo restituisce come risultato del libro aggiunto
	*/
	@PutMapping(value ="/1.0/insert", produces = "application/json")
    public @ResponseBody UbicazioneDTO SaveUbicazione( @RequestBody UbicazioneDTO ubicazioneDTO) {
		UbicazioneDTO result=ubicazioneService.saveUbicazione( ubicazioneDTO.getId(), ubicazioneDTO.getPosizione(), ubicazioneDTO.getScompartimento());
		
		
		
        return result;    }
	
	/* 
	*@param ubicazioneDTO prende un DTO di una Ubicazione  come requestbody ;
	*@return result chi è un DTO di Ubicazione su chi è stata fatta il update.
	*/
	@PostMapping(value ="/1.0/update", produces = "application/json")
    public  UbicazioneDTO updateUbicazione(@RequestBody UbicazioneDTO ubicazioneDTO) {
		UbicazioneDTO result=ubicazioneService.saveUbicazione( ubicazioneDTO.getId(), ubicazioneDTO.getPosizione(), ubicazioneDTO.getScompartimento());
		return result;
		
		
    }
	
	/* 
	*@param ubicazioneDTO prende un DTO di un ubicazione come requestbody contenendo l'id della Ubicazione da eliminare ;
	*/
	@GetMapping(value ="/1.0/delete", produces = "application/json")
    public  void deleteByIdUbicazione(@RequestBody UbicazioneDTO ubicazioneDTO) {
		
		
		
		ubicazioneService.deleteByIdUbicazione(ubicazioneDTO.getId());
        System.out.println("Response gotten");
        
    }

}
