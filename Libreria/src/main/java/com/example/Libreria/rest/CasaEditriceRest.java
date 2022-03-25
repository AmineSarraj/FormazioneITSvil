package com.example.Libreria.rest;

import java.util.concurrent.CompletableFuture;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import com.example.Libreria.DTO.CasaEditriceDTO;
import com.example.Libreria.DTO.InsertAutoreWithoutLibriDTO;
import com.example.Libreria.DTO.InsertCasaEditriceInputDTO;
import com.example.Libreria.DTO.LibroDTO;
import com.example.Libreria.DTO.RisultatoDTO;
import com.example.Libreria.DTO.UpdateCasaEditriceInput;
import com.example.Libreria.entity.Casa_Editrice;
import com.example.Libreria.entity.Libro;
import com.example.Libreria.service.CasaEditriceService;
/* 
*@author Sarraj Amine
*/

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@RestController
@RequestMapping("CasaEdit")
public class CasaEditriceRest {
	@Autowired
    private CasaEditriceService casaEditriceService;
	/* 
	*@param casa_EditriceDTO prende un DTO di una Casa_Editrice come requestbody ;
	*@return result chi un DTO di Casa_Editrice che lo restituisce come risultato della Casa_Editrice aggiunto
	*/
	
	@CircuitBreaker(name="insertCasaEditrice",fallbackMethod = "fallbackInsertCasaEditrice")
	@TimeLimiter(name = "insertCasaEditrice", fallbackMethod = "fallbackInsertCasaEditrice")
	@Bulkhead(name="insertCasaEditrice", fallbackMethod = "fallbackInsertCasaEditrice",type = Bulkhead.Type.THREADPOOL)
	@PutMapping(value ="/1.0/insertCasaEditrice", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody CompletableFuture<ResponseEntity<RisultatoDTO<CasaEditriceDTO>>> insertCasaEditrice(@RequestBody  InsertCasaEditriceInputDTO casa_EditriceDTO) {
		
		CasaEditriceDTO casa_editriceDTO;
		
		
		if (casa_EditriceDTO.getNome()==null || StringUtils.isBlank(casa_EditriceDTO.getNome())) {
			RisultatoDTO<CasaEditriceDTO>  risultatoCasaEditrice=new RisultatoDTO<>();
			risultatoCasaEditrice.setData(null);
			risultatoCasaEditrice.setSuccess(true);
			risultatoCasaEditrice.setDescrizione("nome non deve essere null o empty");
			risultatoCasaEditrice.setCode(200);
		return CompletableFuture.completedFuture(new ResponseEntity<RisultatoDTO<CasaEditriceDTO>>(
				risultatoCasaEditrice, 
		          HttpStatus.OK));}
		
		
		else {
		
		
		RisultatoDTO<CasaEditriceDTO>  risultatoCasaEditrice=new RisultatoDTO<>();
		casa_editriceDTO= casaEditriceService.saveCasa_Editrice(casa_EditriceDTO.getNome(),casa_EditriceDTO.getLibri());
		
		risultatoCasaEditrice.setData(casa_editriceDTO);
		risultatoCasaEditrice.setSuccess(true);
		risultatoCasaEditrice.setDescrizione("casa editrice aggiunta con successo");
		risultatoCasaEditrice.setCode(200);
		
		return CompletableFuture.completedFuture(new ResponseEntity<RisultatoDTO<CasaEditriceDTO>>(
				risultatoCasaEditrice, 
		          HttpStatus.CREATED));
		}
		
    }
	public  CompletableFuture<ResponseEntity<RisultatoDTO<CasaEditriceDTO>>> fallbackInsertCasaEditrice(  @RequestBody  InsertCasaEditriceInputDTO casa_EditriceDTO,Throwable throwable) {
		System.out.println("we are in the fallback");
		
		RisultatoDTO<CasaEditriceDTO> message=new RisultatoDTO<>();
		message.setDescrizione("we could not fulfill the insert request");
		
		
			return CompletableFuture.completedFuture( new ResponseEntity<RisultatoDTO<CasaEditriceDTO>>(
					message, 
			          HttpStatus.OK));
			
		}

	
	/* 
	*@param casa_EditriceDTO prende un DTO di una Casa_Editrice come requestbody ;
	*@return result chi è un DTO di Casa_Editrice su chi è stata fatta il get.
	*/
	
	
	@CircuitBreaker(name="getCasaEditriceById",fallbackMethod = "fallbackGetCasaEditriceById")
	@TimeLimiter(name = "getCasaEditriceById", fallbackMethod = "fallbackGetCasaEditriceById")
	@Bulkhead(name="getCasaEditriceById", fallbackMethod = "fallbackGetCasaEditriceById",type = Bulkhead.Type.THREADPOOL)
	@GetMapping(value ="/1.0/getCasaEditriceById/{idCasaEditrice}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  CompletableFuture<ResponseEntity<RisultatoDTO<CasaEditriceDTO>>> getByIdCasaEditrice(@PathVariable Long idCasaEditrice) {
		
		
		
        CasaEditriceDTO result = casaEditriceService.getByIdCasa_Editrice(idCasaEditrice) ;
        if(result==null) {
			RisultatoDTO<CasaEditriceDTO> risultatoCasaEditrice=new RisultatoDTO<>();
			risultatoCasaEditrice.setData(result);
			risultatoCasaEditrice.setSuccess(true);
			risultatoCasaEditrice.setDescrizione("casa editrice non trovato");
			risultatoCasaEditrice.setCode(200);
			return CompletableFuture.completedFuture(new ResponseEntity<RisultatoDTO<CasaEditriceDTO>>(
					risultatoCasaEditrice, 
			          HttpStatus.OK));
			
		}
		
		else {
        System.out.println("Response gotten");
        RisultatoDTO<CasaEditriceDTO> risultatoCasaEditrice=new RisultatoDTO<>();
        risultatoCasaEditrice.setData(result);
        risultatoCasaEditrice.setSuccess(true);
		risultatoCasaEditrice.setDescrizione("casa editrice trovata nella database");
		risultatoCasaEditrice.setCode(200);

        return CompletableFuture.completedFuture(new ResponseEntity<>(
        		risultatoCasaEditrice, 
		          HttpStatus.FOUND));}
        
        
    }
	public  CompletableFuture<ResponseEntity<RisultatoDTO<CasaEditriceDTO>>> fallbackGetCasaEditriceById(  @PathVariable Long id,Throwable throwable) {
		System.out.println("we are in the fallback");
		
		RisultatoDTO<CasaEditriceDTO> message=new RisultatoDTO<>();
		message.setDescrizione("we could not fulfill the get by id request");
		
		
			return CompletableFuture.completedFuture( new ResponseEntity<RisultatoDTO<CasaEditriceDTO>>(
					message, 
			          HttpStatus.OK));
			
		}

	
	/* 
	*@param casa_EditriceDTO prende un DTO di una Casa_Editrice come requestbody ;
	*@return result chi è un DTO di Casa_Editrice su chi è stata fatta il get a partire dal nome.
	*/
	
	@CircuitBreaker(name="getCasaEditriceByName",fallbackMethod = "fallbackGetCasaEditriceByName")
	@TimeLimiter(name = "getCasaEditriceByName", fallbackMethod = "fallbackGetCasaEditriceByName")
	@Bulkhead(name="getCasaEditriceByName", fallbackMethod = "fallbackGetCasaEditriceByName",type = Bulkhead.Type.THREADPOOL)
	@GetMapping(value ="/1.0/getCasaEditriceByName/{nameCasaEditrice}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  CompletableFuture<ResponseEntity<RisultatoDTO<CasaEditriceDTO>>> getByNameCasaEditrice(@PathVariable String nameCasaEditrice) {
		
		
		
        CasaEditriceDTO result = casaEditriceService.getByNomeCasa_Editrice(nameCasaEditrice) ;
        if(result==null) {
			RisultatoDTO<CasaEditriceDTO> risultatoCasaEditrice=new RisultatoDTO<>();
			risultatoCasaEditrice.setData(result);
			risultatoCasaEditrice.setSuccess(true);
			risultatoCasaEditrice.setDescrizione("nessuna casa editrice ha questo errore");
			risultatoCasaEditrice.setCode(200);
			return CompletableFuture.completedFuture(new ResponseEntity<RisultatoDTO<CasaEditriceDTO>>(
					risultatoCasaEditrice, 
			          HttpStatus.OK));
			
		}
		
		else {
        System.out.println("Response gotten");
        RisultatoDTO<CasaEditriceDTO> risultatoCasaEditrice=new RisultatoDTO<>();
        risultatoCasaEditrice.setData(result);
        risultatoCasaEditrice.setSuccess(true);
		risultatoCasaEditrice.setDescrizione("casa editrice trovata nella database");
		risultatoCasaEditrice.setCode(200);

        return CompletableFuture.completedFuture(new ResponseEntity<>(
        		risultatoCasaEditrice, 
		          HttpStatus.FOUND));}
        
        
    }
	public  CompletableFuture<ResponseEntity<RisultatoDTO<CasaEditriceDTO>>> fallbackGetCasaEditriceByName(@PathVariable String name,Throwable throwable) {
		System.out.println("we are in the fallback");
		
		RisultatoDTO<CasaEditriceDTO> message=new RisultatoDTO<>();
		message.setDescrizione("we could not fulfill the get by name request");
		
		
			return CompletableFuture.completedFuture( new ResponseEntity<RisultatoDTO<CasaEditriceDTO>>(
					message, 
			          HttpStatus.OK));
			
		}

	
	/* 
	*@param casa_EditriceDTO prende un DTO di una Casa_Editrice  come requestbody ;
	*@return result chi è un DTO di Casa_Editrice su chi è stata fatta il update.
	*/
	
	@CircuitBreaker(name="updateCasaEditrice",fallbackMethod = "fallbackUpdateCasaEditrice")
	@TimeLimiter(name = "updateCasaEditrice", fallbackMethod = "fallbackUpdateCasaEditrice")
	@Bulkhead(name="updateCasaEditrice", fallbackMethod = "fallbackUpdateCasaEditrice",type = Bulkhead.Type.THREADPOOL)
	@PostMapping(value ="/1.0/updateCasaEditrice", produces = MediaType.APPLICATION_JSON_VALUE)
    public  CompletableFuture<ResponseEntity<RisultatoDTO<CasaEditriceDTO>>> updateCasaEditrice(@RequestBody  UpdateCasaEditriceInput casa_EditriceDTO) {
		
		
		
        
        
		 if (casa_EditriceDTO.getNome()==null || StringUtils.isBlank(casa_EditriceDTO.getNome())) 
			{
			 RisultatoDTO<CasaEditriceDTO> risultatoCasaEditrice=new RisultatoDTO<>();
				risultatoCasaEditrice.setData(null);
				risultatoCasaEditrice.setSuccess(true);
				risultatoCasaEditrice.setDescrizione("nome non deve essere null o empty");
				risultatoCasaEditrice.setCode(200);
				return CompletableFuture.completedFuture(new ResponseEntity<RisultatoDTO<CasaEditriceDTO>>(
						risultatoCasaEditrice, 
				          HttpStatus.OK));}
		else if (casa_EditriceDTO.getId()==null ) 
		{
			RisultatoDTO<CasaEditriceDTO> risultatoCasaEditrice=new RisultatoDTO<>();
			risultatoCasaEditrice.setData(null);
			risultatoCasaEditrice.setSuccess(true);
			risultatoCasaEditrice.setDescrizione("casa editrice non trovato");
			risultatoCasaEditrice.setCode(200);
			return CompletableFuture.completedFuture(new ResponseEntity<RisultatoDTO<CasaEditriceDTO>>(
					risultatoCasaEditrice, 
			          HttpStatus.OK));}
		 CasaEditriceDTO testCasaEditrice = casaEditriceService.getByIdCasa_Editrice(casa_EditriceDTO.getId()) ;
		 if (testCasaEditrice==null) 
			{
				RisultatoDTO<CasaEditriceDTO> risultatoCasaEditrice=new RisultatoDTO<>();
				risultatoCasaEditrice.setData(null);
				risultatoCasaEditrice.setSuccess(true);
				risultatoCasaEditrice.setDescrizione("nessun id corrisponde a quel data editrice");
				risultatoCasaEditrice.setCode(200);
				return CompletableFuture.completedFuture(new ResponseEntity<RisultatoDTO<CasaEditriceDTO>>(
						risultatoCasaEditrice, 
				          HttpStatus.OK));}
		
		else {
		CasaEditriceDTO result = casaEditriceService.updateCasa_Editrice(casa_EditriceDTO.getId(),casa_EditriceDTO.getNome()) ;
		RisultatoDTO<CasaEditriceDTO> risultatoCasaEditrice=new RisultatoDTO<>();
        risultatoCasaEditrice.setData(result);
        risultatoCasaEditrice.setSuccess(true);
		risultatoCasaEditrice.setDescrizione("casa editrice updated");
		risultatoCasaEditrice.setCode(200);

        return CompletableFuture.completedFuture(new ResponseEntity<>(
        		risultatoCasaEditrice, 
		          HttpStatus.FOUND));}
        
    }
	public  CompletableFuture<ResponseEntity<RisultatoDTO<CasaEditriceDTO>>> fallbackUpdateCasaEditrice(@RequestBody  UpdateCasaEditriceInput casa_EditriceDTO,Throwable throwable) {
		System.out.println("we are in the fallback");
		
		RisultatoDTO<CasaEditriceDTO> message=new RisultatoDTO<>();
		message.setDescrizione("we could not fulfill the update request");
		
		
			return CompletableFuture.completedFuture( new ResponseEntity<RisultatoDTO<CasaEditriceDTO>>(
					message, 
			          HttpStatus.OK));
			
		}

	
	/* 
	*@param casa_EditriceDTO prende un DTO di una Casa_Editrice come requestbody contenendo l'id della Casa_Editrice da eliminare ;
	*/
	
	@CircuitBreaker(name="DeleteCasaEditrice",fallbackMethod = "fallbackDeleteCasaEditrice")
	@TimeLimiter(name = "DeleteCasaEditrice", fallbackMethod = "fallbackDeleteCasaEditrice")
	@Bulkhead(name="DeleteCasaEditrice", fallbackMethod = "fallbackDeleteCasaEditrice",type = Bulkhead.Type.THREADPOOL)
	@DeleteMapping(value ="/1.0/deleteCasaEditrice/{idCasaEditrice}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  CompletableFuture<ResponseEntity<RisultatoDTO<CasaEditriceDTO>>> deleteCasaEditrice(@PathVariable Long idCasaEditrice) {
		
		System.out.println("Response gotten");
		
         casaEditriceService.deleteByIdCasa_Editrice(idCasaEditrice) ;
        System.out.println("Response gotten");
        RisultatoDTO<CasaEditriceDTO> message=new RisultatoDTO<>();
		message.setDescrizione("the delete request succeded");
		return CompletableFuture.completedFuture( new ResponseEntity<RisultatoDTO<CasaEditriceDTO>>(
				message, 
		          HttpStatus.OK));

        
    }
	
	public  CompletableFuture<ResponseEntity<RisultatoDTO<CasaEditriceDTO>>> fallbackDeleteCasaEditrice(@PathVariable Long id,Throwable throwable) {
		System.out.println("we are in the fallback");
		
		RisultatoDTO<CasaEditriceDTO> message=new RisultatoDTO<>();
		message.setDescrizione("we could not fulfill the delete request");
		
		
			return CompletableFuture.completedFuture( new ResponseEntity<RisultatoDTO<CasaEditriceDTO>>(
					message, 
			          HttpStatus.OK));
			
		}


}
