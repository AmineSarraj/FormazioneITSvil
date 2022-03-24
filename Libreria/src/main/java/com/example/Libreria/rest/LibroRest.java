package com.example.Libreria.rest;

import java.text.ParseException;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Libreria.DTO.AutoreDTO;
import com.example.Libreria.DTO.CasaEditriceDTO;
import com.example.Libreria.DTO.InsertAutoreWithoutLibriDTO;
import com.example.Libreria.DTO.LibroDTO;
import com.example.Libreria.DTO.RisultatoDTO;
import com.example.Libreria.entity.Autore;
import com.example.Libreria.entity.Casa_Editrice;
import com.example.Libreria.entity.Libro;
import com.example.Libreria.service.AutoreService;
import com.example.Libreria.service.CasaEditriceService;
import com.example.Libreria.service.LibroService;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;


/* 
*@author Sarraj Amine
*/

@RestController
@RequestMapping("Libro")
public class LibroRest {
	@Autowired
    private LibroService libroService;
	@Autowired
    private CasaEditriceService casaEditriceService;
	@Autowired
    private AutoreService autoreService;
	
	/* 
	*@param libroDTO prende un DTO di un libro come requestbody ;
	*@return result chi un DTO di libro che lo restituisce come risultato del libro che si è fatto la get a partire del titolo.
	*/
	
	@CircuitBreaker(name="getLibroByTitolo",fallbackMethod = "fallbackGetLibroByTitolo")
	@TimeLimiter(name = "getLibroByTitolo", fallbackMethod = "fallbackGetLibroByTitolo")
	@Bulkhead(name="getLibroByTitolo", fallbackMethod = "fallbackGetLibroByTitolo",type = Bulkhead.Type.THREADPOOL)
	@GetMapping(value ="/1.0/getLibroByTitolo/{titolo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDTO<LibroDTO>>> getLibroByTitolo(@PathVariable String titolo) {
		
         LibroDTO result= libroService.getByTitolo(titolo) ;
         if(result==null) {
 			RisultatoDTO<LibroDTO> risultatoLibro=new RisultatoDTO<>();
 			risultatoLibro.setData(result);
 			risultatoLibro.setSuccess(true);
 			risultatoLibro.setDescrizione("libro non trovato");
 			risultatoLibro.setCode(200);
 			return CompletableFuture.completedFuture(new ResponseEntity<RisultatoDTO<LibroDTO>>(
 					risultatoLibro, 
 			          HttpStatus.OK));
 			
 		}
         else {
        	 RisultatoDTO<LibroDTO> risultatoLibro=new RisultatoDTO<>();
  			risultatoLibro.setData(result);
  			risultatoLibro.setSuccess(true);
  			risultatoLibro.setDescrizione("libro trovato");
  			risultatoLibro.setCode(200);
  			return CompletableFuture.completedFuture(new ResponseEntity<RisultatoDTO<LibroDTO>>(
  					risultatoLibro, 
  			          HttpStatus.OK));
         }

    }
	public  CompletableFuture<ResponseEntity<RisultatoDTO<LibroDTO>>> fallbackGetLibroByTitolo(  @PathVariable String titolo,Throwable throwable) {
		System.out.println("we are in the fallback");
		
		RisultatoDTO<LibroDTO> message=new RisultatoDTO<>();
		message.setDescrizione("we could not fulfill the get by name request");
		
		
			return CompletableFuture.completedFuture( new ResponseEntity<RisultatoDTO<LibroDTO>>(
					message, 
			          HttpStatus.OK));
			
		}

	
	/* 
	*@param libroDTO prende un DTO di un libro come requestbody ;
	*@return result chi un DTO di libro che lo restituisce come risultato del libro aggiunto
	*/
	
	@CircuitBreaker(name="insertLibro",fallbackMethod = "fallbackInsertLibro")
	@TimeLimiter(name = "insertLibro", fallbackMethod = "fallbackInsertLibro")
	@Bulkhead(name="insertLibro", fallbackMethod = "fallbackInsertLibro",type = Bulkhead.Type.THREADPOOL)
	@PutMapping(value ="/1.0/insertLibro", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDTO<LibroDTO>>> insertLibro( @RequestBody LibroDTO libroDTO) {
		
		//TODO: Check input
		List<String> obblParameters=new ArrayList<>();
		//Boolean success=true;
		
		if(libroDTO.getTitolo()==null||libroDTO.getTitolo().isBlank()) { obblParameters.add("Titolo");}
		if(libroDTO.getCasaEditrice()==null||libroDTO.getCasaEditrice().isBlank()) { obblParameters.add("CasaEditrice");}
		if(libroDTO.getTrama()==null||libroDTO.getTrama().isBlank()) { obblParameters.add("Trama");}
		if(!obblParameters.isEmpty()) {
		RisultatoDTO<LibroDTO> risultatoLibro=new RisultatoDTO<>();
		StringBuilder NullorEmptyOutputAttributs= new StringBuilder();
		NullorEmptyOutputAttributs.append(obblParameters.toString());
		NullorEmptyOutputAttributs.append(" can't be null");
 		risultatoLibro.setData(null);
 		risultatoLibro.setSuccess(true);
 		risultatoLibro.setDescrizione(NullorEmptyOutputAttributs.toString());
 		risultatoLibro.setCode(200);
 		return CompletableFuture.completedFuture(new ResponseEntity<RisultatoDTO<LibroDTO>>(
 					risultatoLibro, 
 			          HttpStatus.OK));}
 			
 		
		
		/*else if(libroDTO.getTrama()==null||libroDTO.getTrama()=="") {
 			RisultatoDTO<LibroDTO> risultatoLibro=new RisultatoDTO<>();
 			risultatoLibro.setData(null);
 			risultatoLibro.setSuccess(true);
 			risultatoLibro.setDescrizione("trama non puo essere null");
 			risultatoLibro.setCode(200);
 			return new ResponseEntity<RisultatoDTO<LibroDTO>>(
 					risultatoLibro, 
 			          HttpStatus.OK);
 			
 		}
		
		else if(libroDTO.getCasaEditrice()==null||libroDTO.getCasaEditrice()=="") {
 			RisultatoDTO<LibroDTO> risultatoLibro=new RisultatoDTO<>();
 			risultatoLibro.setData(null);
 			risultatoLibro.setSuccess(true);
 			risultatoLibro.setDescrizione("libro deve avere una casa editrice");
 			risultatoLibro.setCode(200);
 			return new ResponseEntity<RisultatoDTO<LibroDTO>>(
 					risultatoLibro, 
 			          HttpStatus.OK);
 			
 		}*/
         else {
        	 LibroDTO result = libroService.saveLibro(libroDTO);
        	 RisultatoDTO<LibroDTO> risultatoLibro=new RisultatoDTO<>();
  			risultatoLibro.setData(result);
  			risultatoLibro.setSuccess(true);
  			risultatoLibro.setDescrizione("libro aggiunto ");
  			risultatoLibro.setCode(200);
  			return CompletableFuture.completedFuture(new ResponseEntity<RisultatoDTO<LibroDTO>>(
  					risultatoLibro, 
  			          HttpStatus.OK));
         }
		
		//TODO: gestione output
		
		
		
		
    }
	
	public  CompletableFuture<ResponseEntity<RisultatoDTO<LibroDTO>>> fallbackInsertLibro(  @RequestBody LibroDTO libroDTO,Throwable throwable) {
		System.out.println("we are in the fallback");
		
		RisultatoDTO<LibroDTO> message=new RisultatoDTO<>();
		message.setDescrizione("we could not fulfill the insert request");
		
		
			return CompletableFuture.completedFuture( new ResponseEntity<RisultatoDTO<LibroDTO>>(
					message, 
			          HttpStatus.OK));
			
		}
	
	/* 
	*@param libroDTO prende un DTO di una libro come requestbody ;
	*@return result chi è un DTO del libro su chi è stata fatta il get.
	*/
	
	@CircuitBreaker(name="getLibroById",fallbackMethod = "fallbackGetLibroById")
	@TimeLimiter(name = "getLibroById", fallbackMethod = "fallbackGetLibroById")
	@Bulkhead(name="getLibroById", fallbackMethod = "fallbackGetLibroById",type = Bulkhead.Type.THREADPOOL)
	@GetMapping(value ="/1.0/getLibroById/{idLibro}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  CompletableFuture<ResponseEntity<RisultatoDTO<LibroDTO>>> getLibroById(@PathVariable Long idLibro) {
		System.out.println("siamo nel get");
		
		
        LibroDTO result = libroService.getByIdLibro(idLibro) ;
        if(result==null) {
 			RisultatoDTO<LibroDTO> risultatoLibro=new RisultatoDTO<>();
 			risultatoLibro.setData(result);
 			risultatoLibro.setSuccess(true);
 			risultatoLibro.setDescrizione("libro non trovato");
 			risultatoLibro.setCode(200);
 			return CompletableFuture.completedFuture(new ResponseEntity<RisultatoDTO<LibroDTO>>(
 					risultatoLibro, 
 			          HttpStatus.OK));
 			
 		}
         else {
        	 RisultatoDTO<LibroDTO> risultatoLibro=new RisultatoDTO<>();
  			risultatoLibro.setData(result);
  			risultatoLibro.setSuccess(true);
  			risultatoLibro.setDescrizione("libro trovato");
  			risultatoLibro.setCode(200);
  			return CompletableFuture.completedFuture(new ResponseEntity<RisultatoDTO<LibroDTO>>(
  					risultatoLibro, 
  			          HttpStatus.OK));
         }
        
       
        
    }
	public  CompletableFuture<ResponseEntity<RisultatoDTO<LibroDTO>>> fallbackGetLibroById(  @PathVariable Long id,Throwable throwable) {
		System.out.println("we are in the fallback");
		
		RisultatoDTO<LibroDTO> message=new RisultatoDTO<>();
		message.setDescrizione("we could not fulfill the insert request");
		
		
			return CompletableFuture.completedFuture( new ResponseEntity<RisultatoDTO<LibroDTO>>(
					message, 
			          HttpStatus.OK));
			
		}
	
	/* 
	*@param libroDTO prende un DTO di un libro come requestbody contenendo l'id della Casa_Editrice da eliminare ;
	*/
	
	@CircuitBreaker(name="deleteLibro",fallbackMethod = "fallbackDeleteLibro")
	@TimeLimiter(name = "deleteLibro", fallbackMethod = "fallbackDeleteLibro")
	@Bulkhead(name="deleteLibro", fallbackMethod = "fallbackDeleteLibro",type = Bulkhead.Type.THREADPOOL)
	@DeleteMapping(value ="/1.0/deleteLibro/{idLibro}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  CompletableFuture<ResponseEntity<RisultatoDTO<LibroDTO>>> deleteLibro(@PathVariable Long idLibro) {
		System.out.println("Response gotten");
		
		
		libroService.deleteByIdLibro(idLibro) ;
        System.out.println("Response gotten");
        RisultatoDTO<LibroDTO> message=new RisultatoDTO<>();
		message.setDescrizione("Delete succeded");
		
		
			return CompletableFuture.completedFuture( new ResponseEntity<RisultatoDTO<LibroDTO>>(
					message, 
			          HttpStatus.OK));

        
    }
	public  CompletableFuture<ResponseEntity<RisultatoDTO<LibroDTO>>> fallbackDeleteLibro(@PathVariable Long id,Throwable throwable) {
		System.out.println("we are in the fallback");
		
		RisultatoDTO<LibroDTO> message=new RisultatoDTO<>();
		message.setDescrizione("we could not fulfill the delete request");
	
		
			return CompletableFuture.completedFuture( new ResponseEntity<RisultatoDTO<LibroDTO>>(
					message, 
			          HttpStatus.OK));
			
		}


	
	/* 
	*@param libroDTO prende un DTO di un libro come requestbody ;
	*@return result chi una list di DTO di libro che lo restituisce come risultato del libro che si è fatto la get a partire dalla data di uscita mostrando solo quelli con data di uscita maggiore alla data del DTO passato in input.
	*/
	
	@CircuitBreaker(name="getLibroByDateGT",fallbackMethod = "fallbackGetLibroByDateGT")
	@TimeLimiter(name = "getLibroByDateGT", fallbackMethod = "fallbackGetLibroByDateGT")
	@Bulkhead(name="getLibroByDateGT", fallbackMethod = "fallbackGetLibroByDateGT",type = Bulkhead.Type.THREADPOOL)
	@GetMapping(value ="/1.0/getLibroByDateGT/{condition}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  CompletableFuture<ResponseEntity<RisultatoDTO<LibroDTO>>> getByDate(@PathVariable Date condition) {
		
		
		
        List <LibroDTO> result = libroService.getByDateGreaterThan(condition) ;
        System.out.println("Response gotten");
        if(result==null) {
 			RisultatoDTO<LibroDTO> risultatoLibro=new RisultatoDTO<>();
 			risultatoLibro.setDati(result);
 			risultatoLibro.setSuccess(true);
 			risultatoLibro.setDescrizione("libro non trovato");
 			risultatoLibro.setCode(200);
 			return CompletableFuture.completedFuture(new ResponseEntity<RisultatoDTO<LibroDTO>>(
 					risultatoLibro, 
 			          HttpStatus.OK));
 			
 		}
         else {
        	 RisultatoDTO<LibroDTO> risultatoLibro=new RisultatoDTO<>();
  			risultatoLibro.setDati(result);
  			risultatoLibro.setSuccess(true);
  			risultatoLibro.setDescrizione("libri trovati");
  			risultatoLibro.setCode(200);
  			return CompletableFuture.completedFuture(new ResponseEntity<RisultatoDTO<LibroDTO>>(
  					risultatoLibro, 
  			          HttpStatus.OK));
         }
        
    }
	public  CompletableFuture<ResponseEntity<RisultatoDTO<LibroDTO>>> fallbackGetByDateLibro(@PathVariable Date condition,Throwable throwable) {
		System.out.println("we are in the fallback");
		CompletableFuture<ResponseEntity<RisultatoDTO<AutoreDTO>>> future=new CompletableFuture<>();
		RisultatoDTO<LibroDTO> message=new RisultatoDTO<>();
		message.setDescrizione("we could not fulfill the insert request");
		ResponseEntity<RisultatoDTO<LibroDTO>> response=new ResponseEntity<RisultatoDTO<LibroDTO>>(
				message, 
		          HttpStatus.OK);
		
			return CompletableFuture.completedFuture( new ResponseEntity<RisultatoDTO<LibroDTO>>(
					message, 
			          HttpStatus.OK));
			
		}

	
	/* 
	*@param libroDTO prende un DTO di un libro come requestbody ;
	*@return result chi una list di DTO di libro che lo restituisce come risultato del libro che si è fatto la get a partire dalla data di uscita mostrando solo quelli con il titolo contiene la stringa del titolo del DTO passato in input.
	*/
	
	@CircuitBreaker(name="getLibroByTitoloContaining",fallbackMethod = "fallbackGetLibroByTitoloContaining")
	@TimeLimiter(name = "getLibroByTitoloContaining", fallbackMethod = "fallbackGetLibroByTitoloContaining")
	@Bulkhead(name="getLibroByTitoloContaining", fallbackMethod = "fallbackGetLibroByTitoloContaining",type = Bulkhead.Type.THREADPOOL)
	@GetMapping(value ="/1.0/getLibroByTitoloContaining/{condition}", produces = MediaType.APPLICATION_JSON_VALUE)
    public   CompletableFuture<ResponseEntity<RisultatoDTO<LibroDTO>>> getLibroByTitoloContaining(@PathVariable String condition) {
		
		
		
        List <LibroDTO> result = libroService.getByTitoloContainingIgnoreCase(condition) ;
        
        if(result==null) {
 			RisultatoDTO<LibroDTO> risultatoLibro=new RisultatoDTO<>();
 			risultatoLibro.setDati(result);
 			risultatoLibro.setSuccess(true);
 			risultatoLibro.setDescrizione("libro non trovato");
 			risultatoLibro.setCode(200);
 			return CompletableFuture.completedFuture(new ResponseEntity<RisultatoDTO<LibroDTO>>(
 					risultatoLibro, 
 			          HttpStatus.OK));
 			
 		}
         else {
        	 RisultatoDTO<LibroDTO> risultatoLibro=new RisultatoDTO<>();
  			risultatoLibro.setDati(result);
  			risultatoLibro.setSuccess(true);
  			risultatoLibro.setDescrizione("libri trovati");
  			risultatoLibro.setCode(200);
  			return CompletableFuture.completedFuture(new ResponseEntity<RisultatoDTO<LibroDTO>>(
  					risultatoLibro, 
  			          HttpStatus.OK));
         }
        
    }
	public  CompletableFuture<ResponseEntity<RisultatoDTO<LibroDTO>>> fallbackGetByContainingLibro(@PathVariable String condition,Throwable throwable) {
		System.out.println("we are in the fallback");
		
		RisultatoDTO<LibroDTO> message=new RisultatoDTO<>();
		message.setDescrizione("we could not fulfill the insert request");
		
		
			return CompletableFuture.completedFuture( new ResponseEntity<RisultatoDTO<LibroDTO>>(
					message, 
			          HttpStatus.OK));
			
		}


}
