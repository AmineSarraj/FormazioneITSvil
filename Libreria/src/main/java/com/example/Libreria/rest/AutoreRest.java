package com.example.Libreria.rest;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;


import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Libreria.DTO.AutoreDTO;
import com.example.Libreria.DTO.InsertAutoreWithoutLibriDTO;
import com.example.Libreria.DTO.LibroDTO;
import com.example.Libreria.DTO.RisultatoDTO;
import com.example.Libreria.DTO.UpdateAuthorDTOInput;
import com.example.Libreria.entity.Autore;
import com.example.Libreria.entity.Casa_Editrice;
import com.example.Libreria.entity.Ubicazione;
import com.example.Libreria.service.AutoreService;


import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;


/* 
*@author Sarraj Amine
*/


@RestController
@RequestMapping("Autore")
public class AutoreRest {
	
	@Autowired
    private AutoreService autoreService;
	
	Logger logger =LogManager.getLogger (AutoreRest.class);

	
	/* 
	*@param inputAutoreDTO prende un DTO di autore come requestbody ;
	*@return result chi un DTO di autore che lo restituisce come risultato dell'autore aggiunto
	*/
	
	@CircuitBreaker(name="insertAutore",fallbackMethod = "fallbackInsertAutore")
	@TimeLimiter(name = "insertAutore", fallbackMethod = "fallbackInsertAutore")
	@Bulkhead(name="insertAutore", fallbackMethod = "fallbackInsertAutore",type = Bulkhead.Type.THREADPOOL)
	@PutMapping(value ="/1.0/insertAutore", produces =MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody   CompletableFuture<ResponseEntity<RisultatoDTO<AutoreDTO>>> insertAutore(  @RequestBody InsertAutoreWithoutLibriDTO inputAutoreDTO) {
		RisultatoDTO<AutoreDTO>  risultatoAutore=new RisultatoDTO<>();
		if (inputAutoreDTO.getCognome()==null ||StringUtils.isBlank( inputAutoreDTO.getCognome())||inputAutoreDTO.getNome()==null || StringUtils.isBlank( inputAutoreDTO.getNome()) ) {
			
			risultatoAutore.setData(null);
			risultatoAutore.setSuccess(true);
			risultatoAutore.setDescrizione("Impossibile di inserire un nuovo autore");
			risultatoAutore.setCode(404);
			logger.error("Impossibile di inserire un nuovo autore");
		return CompletableFuture.completedFuture( new ResponseEntity<RisultatoDTO<AutoreDTO>>(
				risultatoAutore, 
		          HttpStatus.BAD_REQUEST));}
		/*else if (inputAutoreDTO.getNome()==null || StringUtils.isBlank( inputAutoreDTO.getNome())) 
			{
				
				risultatoAutore.setData(null);
				risultatoAutore.setSuccess(true);
				risultatoAutore.setDescrizione("nome non deve essere null");
				risultatoAutore.setCode(200);
			return CompletableFuture.completedFuture(new ResponseEntity<RisultatoDTO<AutoreDTO>>(
					risultatoAutore, 
			          HttpStatus.OK));}*/
		
		else {
		
		AutoreDTO result ;
		
		
		
		result= autoreService.saveAutore(inputAutoreDTO.getNome(), inputAutoreDTO.getCognome(), inputAutoreDTO.getLibriIds());
			
		risultatoAutore.setData(result);
		risultatoAutore.setSuccess(true);
		risultatoAutore.setDescrizione("autore aggiunto con successo");
		logger.info("autore aggiunto con successo");
		risultatoAutore.setCode(200);
		
		return CompletableFuture.completedFuture(new ResponseEntity<RisultatoDTO<AutoreDTO>>(
				risultatoAutore, 
		          HttpStatus.CREATED));
		}
		    }
	public  CompletableFuture<ResponseEntity<RisultatoDTO<AutoreDTO>>> fallbackInsertAutore(  @RequestBody InsertAutoreWithoutLibriDTO inputAutoreDTO,Throwable throwable) {
		System.out.println("we are in the fallback");
		RisultatoDTO<AutoreDTO> message=new RisultatoDTO<>();
		message.setDescrizione("we could not fulfill the insert request");
		logger.info("autore aggiunto con successo");
		
		
			return CompletableFuture.completedFuture( new ResponseEntity<RisultatoDTO<AutoreDTO>>(
					message, 
			          HttpStatus.OK));
			
		}
	
	
	
	/* 
	*@param inputAutoreDTO prende un DTO di autore come requestbody ;
	*@return result chi un DTO di autore che lo restituisce come risultato dell'autore modificato
	*/
	
	@CircuitBreaker(name="updateAutore",fallbackMethod = "fallbackUpdateAutore")
	@TimeLimiter(name = "updateAutore", fallbackMethod = "fallbackUpdateAutore")
	@Bulkhead(name="updateAutore", fallbackMethod = "fallbackUpdateAutore",type = Bulkhead.Type.SEMAPHORE)
	@PostMapping(value ="/1.0/updateAutore", produces =MediaType.APPLICATION_JSON_VALUE)
    public  CompletableFuture<ResponseEntity<RisultatoDTO<AutoreDTO>>> updateAutore(@RequestBody UpdateAuthorDTOInput inputAutoreDTO) {
		RisultatoDTO<AutoreDTO>  risultatoAutore=new RisultatoDTO<>();
		risultatoAutore.setData(null);
		risultatoAutore.setSuccess(false);
		if (inputAutoreDTO.getNome()==null || StringUtils.isBlank( inputAutoreDTO.getNome())||inputAutoreDTO.getCognome()==null || StringUtils.isBlank( inputAutoreDTO.getCognome())||inputAutoreDTO.getId()==null)  {
			
			risultatoAutore.setDescrizione("Bad Request, la update non puo essere fatta");
			logger.info("Bad Request, la update non puo essere fatta");

			risultatoAutore.setCode(404);
		return CompletableFuture.completedFuture(new ResponseEntity<RisultatoDTO<AutoreDTO>>(
				risultatoAutore, 
		          HttpStatus.BAD_REQUEST));}
		/*else if (inputAutoreDTO.getNome()==null || StringUtils.isBlank( inputAutoreDTO.getNome())||inputAutoreDTO.getCognome()==null || StringUtils.isBlank( inputAutoreDTO.getCognome())) 
			{
				RisultatoDTO<AutoreDTO>  risultatoAutore=new RisultatoDTO<>();
				risultatoAutore.setData(null);
				risultatoAutore.setSuccess(true);
				risultatoAutore.setDescrizione("nome non deve essere null");
				risultatoAutore.setCode(200);
			return CompletableFuture.completedFuture(new ResponseEntity<RisultatoDTO<AutoreDTO>>(
					risultatoAutore, 
			          HttpStatus.OK));}
		else if (inputAutoreDTO.getId()==null ) 
		{
			RisultatoDTO<AutoreDTO>  risultatoAutore=new RisultatoDTO<>();
			risultatoAutore.setData(null);
			risultatoAutore.setSuccess(true);
			risultatoAutore.setDescrizione("id non deve essere null ");
			risultatoAutore.setCode(200);
		return CompletableFuture.completedFuture(new ResponseEntity<RisultatoDTO<AutoreDTO>>(
				risultatoAutore, 
		          HttpStatus.OK));}*/
		AutoreDTO testAutore =autoreService.getByIdAutore(inputAutoreDTO.getId())  ;
		 if (testAutore==null ) 
		{
			
			risultatoAutore.setDescrizione("nessun id corrisponde a quello cercato");
			logger.info("nessun id corrisponde a quello cercato");
			risultatoAutore.setCode(404);
		return CompletableFuture.completedFuture(new ResponseEntity<RisultatoDTO<AutoreDTO>>(
				risultatoAutore, 
		          HttpStatus.BAD_REQUEST));} 
		
		
		else {
		AutoreDTO result=autoreService.updateAutore(inputAutoreDTO.getNome(), inputAutoreDTO.getCognome(), inputAutoreDTO.getId());
        System.out.println("Response gotten");
        
		risultatoAutore.setData(result);
		risultatoAutore.setSuccess(true);
		risultatoAutore.setDescrizione("l'autore che ha un id "+ inputAutoreDTO.getId()+ " è stato modificato");
		risultatoAutore.setCode(200);
		logger.info("Autore è stato modificato");
	return CompletableFuture.completedFuture(new ResponseEntity<RisultatoDTO<AutoreDTO>>(
			risultatoAutore, 
	          HttpStatus.OK));}
		
		}
	public  CompletableFuture<ResponseEntity<RisultatoDTO<AutoreDTO>>> fallbackUpdateAutore(@RequestBody UpdateAuthorDTOInput inputAutoreDTO,Throwable throwable) {
		System.out.println("we are in the fallback");
		logger.info("Fallback, problema nel servizio");
		
		RisultatoDTO<AutoreDTO> message=new RisultatoDTO<>();
		message.setDescrizione("we could not fulfill the update request");
		
		
			return CompletableFuture.completedFuture( new ResponseEntity<RisultatoDTO<AutoreDTO>>(
					message, 
			          HttpStatus.OK));
			
		}
        
    
	/* 
	*@param inputAutoreDTO prende un DTO di autore come requestbody ;
	*@return result chi un DTO di autore che lo restituisce come risultato dell'autore che abbiamo restituito attraverso il suo id
	*/
	
	@CircuitBreaker(name="getAutoreById",fallbackMethod = "fallbackGetAutoreById")
	@TimeLimiter(name = "getAutoreById", fallbackMethod = "fallbackGetAutoreById")
	@Bulkhead(name="getAutoreById", fallbackMethod = "fallbackGetAutoreById",type=Bulkhead.Type.THREADPOOL)
	@GetMapping(value ="/1.0/getAutoreById/{idAutore}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  CompletableFuture<ResponseEntity<RisultatoDTO<AutoreDTO>>> getByIdAutore(@PathVariable Long idAutore) {
		
		
		RisultatoDTO<AutoreDTO> risultatoAutore=new RisultatoDTO<>();
		
		AutoreDTO result =autoreService.getByIdAutore(idAutore)  ;
		risultatoAutore.setData(result);
		risultatoAutore.setSuccess(true);
		if(result==null) {
			
			
			risultatoAutore.setDescrizione("Autore non trovato");
			logger.info("Autore non trovato");
			risultatoAutore.setCode(404);
			return CompletableFuture.completedFuture(new ResponseEntity<RisultatoDTO<AutoreDTO>>(
					risultatoAutore, 
			          HttpStatus.BAD_REQUEST));
			
		}
		
		else {
        System.out.println("Response gotten");
        
		
		risultatoAutore.setDescrizione("Autore trovato nella database");
		logger.info("Autore trovato nella database");
		risultatoAutore.setCode(200);

        return CompletableFuture.completedFuture(new ResponseEntity<>(
        		risultatoAutore, 
		          HttpStatus.FOUND));}
    }
	
	public  CompletableFuture<ResponseEntity<RisultatoDTO<AutoreDTO>>> fallbackGetAutoreById(@PathVariable Long idAutore,Throwable throwable) {
		System.out.println("Response gotten");
		logger.info("Fallback, problema nel servizio");

		
		RisultatoDTO<AutoreDTO> message=new RisultatoDTO<>();
		message.setDescrizione("we could not fulfill the request find by id");
		
		
			return CompletableFuture.completedFuture( new ResponseEntity<RisultatoDTO<AutoreDTO>>(
					message, 
			          HttpStatus.OK));
			
		}

	/* 
	*@param inputAutoreDTO prende un DTO di autore come requestbody ;
	*@return result chi un DTO di autore che lo restituisce come risultato dell'autore a chi corrisponde il nome
	*/
	
	
	@CircuitBreaker(name="getAutoreByName",fallbackMethod = "fallbackGetAutoreByName")
	@TimeLimiter(name = "getAutoreByName", fallbackMethod = "fallbackGetAutoreByName")
	@Bulkhead(name="getAutoreByName", fallbackMethod = "fallbackGetAutoreByName",type=Bulkhead.Type.THREADPOOL)
	@GetMapping(value ="/1.0/getAutoreByName/{nameAutore}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  CompletableFuture<ResponseEntity<RisultatoDTO<AutoreDTO>>> getByNameAutore(@PathVariable String nameAutore) {
		
		
		AutoreDTO result =autoreService.getByNomeAutore(nameAutore)  ;
		RisultatoDTO<AutoreDTO> risultatoAutore=new RisultatoDTO<>();
		risultatoAutore.setData(result);
		risultatoAutore.setSuccess(true);
        System.out.println("Response gotten");
        if(result==null) {
        	
			risultatoAutore.setDescrizione("Autore non trovato");
			logger.info("Autore non trovato");

			risultatoAutore.setCode(404);
			
			return CompletableFuture.completedFuture(new ResponseEntity<RisultatoDTO<AutoreDTO>>(
					risultatoAutore, 
			          HttpStatus.OK));
			
		}
			
		
        else {
            System.out.println("Response gotten");
            
    		risultatoAutore.setDescrizione("Autore trovato nella database");
    		risultatoAutore.setCode(200);
    		ResponseEntity<RisultatoDTO<AutoreDTO>> response=new ResponseEntity<RisultatoDTO<AutoreDTO>>(
					risultatoAutore, 
			          HttpStatus.FOUND);
    		return CompletableFuture.completedFuture( new ResponseEntity<RisultatoDTO<AutoreDTO>>(
					risultatoAutore, 
			          HttpStatus.OK));

    }
		
		}
	
	public  CompletableFuture<ResponseEntity<RisultatoDTO<AutoreDTO>>> fallbackGetAutoreByName(@PathVariable String name,Throwable throwable) {
		System.out.println("Response gotten");
		RisultatoDTO<AutoreDTO> message=new RisultatoDTO<>();
		logger.info("Fallback, problema nel servizio");
		throwable.printStackTrace();
		message.setDescrizione("we could not fulfill the request");
		
		
			return CompletableFuture.completedFuture( new ResponseEntity<RisultatoDTO<AutoreDTO>>(
					message, 
			          HttpStatus.OK));
			
		}
	/* 
	*@param inputAutoreDTO prende un DTO di autore come requestbody contendo l'id dell'autore da eliminare;
	
	*/
	
	@CircuitBreaker(name="DeleteAutore",fallbackMethod = "fallbackDeleteAutore")
	@TimeLimiter(name = "DeleteAutore", fallbackMethod = "fallbackDeleteAutore")
	@Bulkhead(name="DeleteAutore", fallbackMethod = "fallbackDeleteAutore",type=Bulkhead.Type.THREADPOOL)
	@DeleteMapping(value ="/1.0/delete/{idAutore}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  CompletableFuture<ResponseEntity<RisultatoDTO<AutoreDTO>>> deleteByIdAutore(@PathVariable Long idAutore) {
		RisultatoDTO<AutoreDTO> message=new RisultatoDTO<>();

		System.out.println("Response gotten");
		message.setDescrizione("the delete request succeded");
		autoreService.deleteByIdAutore(idAutore) ;
		logger.info("Autore deleted");
        System.out.println("Response gotten");
        return CompletableFuture.completedFuture( new ResponseEntity<RisultatoDTO<AutoreDTO>>(
				message, 
		          HttpStatus.OK));
    }
	public  CompletableFuture<ResponseEntity<RisultatoDTO<AutoreDTO>>> fallbackDeleteAutore(@PathVariable Long id,Throwable throwable) {
		System.out.println("Response gotten from fallback");
		RisultatoDTO<AutoreDTO> message=new RisultatoDTO<>();
		message.setDescrizione("we could not fulfill the delete request");
		logger.info("Fallback, problema nel servizio ");
		
		
			return CompletableFuture.completedFuture( new ResponseEntity<RisultatoDTO<AutoreDTO>>(
					message, 
			          HttpStatus.OK));
			
		}

	
	
	@CircuitBreaker(name="GetAutoreByLibroService",fallbackMethod = "fallbackGetAutoreByLibro")
	@TimeLimiter(name = "GetAutoreByLibroService", fallbackMethod = "fallbackGetAutoreByLibro")
	@Bulkhead(name="GetAutoreByLibroService", fallbackMethod = "fallbackGetAutoreByLibro",type=Bulkhead.Type.THREADPOOL)
	@GetMapping(value ="/1.0/getByLibro/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  CompletableFuture<ResponseEntity<RisultatoDTO<AutoreDTO>>> getByLibroAutore(@PathVariable Long id) {
		
		
		
		AutoreDTO result =autoreService.getByIdAutore(id)  ;
		RisultatoDTO<AutoreDTO> risultatoAutore=new RisultatoDTO<>();
		risultatoAutore.setData(result);
		risultatoAutore.setSuccess(true);
		if(result==null) {
			risultatoAutore.setDescrizione("Autore non trovato");
			logger.info("Autore non trovato.");

			risultatoAutore.setCode(404);
			return CompletableFuture.completedFuture( new ResponseEntity<RisultatoDTO<AutoreDTO>>(
					risultatoAutore, 
			          HttpStatus.OK));
			
		}
			
		
        else {
            System.out.println("Response gotten");
    		risultatoAutore.setDescrizione("Autore trovato nella database");
    		logger.info("Autore trovato nella database.");
    		risultatoAutore.setCode(200);

            return CompletableFuture.completedFuture(new ResponseEntity<>(
            		risultatoAutore, 
    		          HttpStatus.FOUND));}
    }
	public  CompletableFuture<ResponseEntity<RisultatoDTO<AutoreDTO>>> fallbackGetAutoreByLibro(@PathVariable Long id,Throwable throwable) {
		System.out.println("Response gotten from fallback");
		logger.info("FallBack, problema al servizio.");
		RisultatoDTO<AutoreDTO> message=new RisultatoDTO<>();
		message.setDescrizione("we could not fulfill the get libri by id request");
		
		
			return CompletableFuture.completedFuture( new ResponseEntity<RisultatoDTO<AutoreDTO>>(
					message, 
			          HttpStatus.OK));
			
		}



}

