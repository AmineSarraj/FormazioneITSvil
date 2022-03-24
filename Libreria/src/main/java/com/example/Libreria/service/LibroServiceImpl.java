package com.example.Libreria.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.Libreria.DAO.AutoreRepository;
import com.example.Libreria.DAO.CasaEditriceRepository;
import com.example.Libreria.DAO.LibroRepository;
import com.example.Libreria.DTO.LibroDTO;
import com.example.Libreria.entity.Autore;
import com.example.Libreria.entity.Casa_Editrice;
import com.example.Libreria.entity.Libro;

/* 
 *@author Sarraj Amine
 */

@Service
public class LibroServiceImpl implements LibroService {

	@Autowired
	private LibroRepository libroRepository;
	@Autowired
	private CasaEditriceRepository casaEditriceRepository;
	@Autowired
	private AutoreRepository autoreRepository;

	/* 
	 *@param prende come input il nome del libro che vorrei restituire ;
	 *@return una DTO del libro uguale al libro chi ha questo nome ;
	 */
	@Override
	public LibroDTO getByTitolo(String TitoloLibro) {
		Optional<Libro> lCheck = libroRepository.findByTitolo(TitoloLibro);
		Casa_Editrice c = null;
		if(lCheck.isPresent()) {
			Libro l = lCheck.get();
			Optional<Casa_Editrice> cCheck= casaEditriceRepository.findById(l.getCasaEditrice().getId());
			if(cCheck.isPresent()) 
				c= cCheck.get();

			List<Autore> autori=autoreRepository.findByLibri(l);

			List<String> autoriNomi = new ArrayList<String>();

			for (Autore autoreName: autori) {
				Autore a =autoreRepository.findByNome(autoreName.getNome()).get() ;
				autoriNomi.add(a.getCognome());
			}

			LibroDTO result=new LibroDTO(l.getId(),l.getTitolo(),l.getTrama(),l.getData_uscita(),autoriNomi,c.getNome());
			return result ;}
		else return null;
	}

	/* 
	 *@param prende come input un DTO di libro che vogliamo aggiungere  ;
	 *@return una DTO di libro uguale al libro aggiunto ;
	 */

	@Override
	public LibroDTO saveLibro(LibroDTO lInput) {
		Optional<Casa_Editrice> cCheck= casaEditriceRepository.findByNome(lInput.getCasaEditrice());
		Casa_Editrice c = null;
		if(cCheck.isPresent()) {
			c= cCheck.get();
		}
		List<Autore> autori = new ArrayList<Autore>();
		List<String> autoriNomi = new ArrayList<String>();

		for (String autoreName: lInput.getAutore()) {
			Autore a =autoreRepository.findByNome(autoreName).get() ;
			autori.add(a);
			autoriNomi.add(a.getCognome());
		}

		Libro l=new Libro(lInput.getTitolo(),lInput.getTrama(),lInput.getDatauscita(),autori,c);
		libroRepository.save(l);
		LibroDTO result=new LibroDTO(l.getId(),l.getTitolo(),l.getTrama(),l.getData_uscita(),autoriNomi,c.getNome());
		return result ;


	}
	/* 
	 *@param prende come input l'id del libro che vorrei restituire ;
	 *@return una DTO di autore uguale al libro chi ha questo id ;
	 */
	@Override
	public LibroDTO getByIdLibro(Long id) {
		// TODO Auto-generated method stub
		Optional <Libro> lCheck=libroRepository.findById(id);
		System.out.println("siamo nel Service");

		if(lCheck.isPresent()) {
			Libro l = lCheck.get();
			System.out.println("libro "+l.getTitolo());
			//Long idCasaEditrice = l.getCasaEditrice().getId();
			
			String nomeCasaEditrice = casaEditriceRepository.findById(l.getCasaEditrice().getId()).get().getNome();
			System.out.println("siamo nel Service dopo casa editrice "+nomeCasaEditrice);
			List<Autore> listaAutori = autoreRepository.findByLibri(l);
//			Casa_Editrice c= casa_EditriceRepository.findByNome(l.getCasaEditrice().getNome()).get();
//			List<Autore> autori=autoreRepository.findByLibri(l);
			List<String> autoriNomi = new ArrayList<String>();
			System.out.println("siamo nel Service");
			for (Autore autore: listaAutori) {
//				Autore a =autoreRepository.findByNome(autoreName.getNome()).get() ;
				autoriNomi.add(autore.getCognome());
			}
//
			LibroDTO result=new LibroDTO(l.getId(),l.getTitolo(),l.getTrama(),l.getData_uscita(),autoriNomi,nomeCasaEditrice);
			return result ;
			}
		else return null;
	}

	/* 
	 *@param prende come input il id del libro che vorrei eliminare ;
	 */
	@Override
	public void deleteByIdLibro(Long id) {
		// TODO Auto-generated method stub
		Optional<Libro> lCheck=libroRepository.findById(id);
		if(lCheck.isPresent()) {
		Libro l=lCheck.get();
		libroRepository.delete(l);}

	}

	/* 
	 *@param libroDTO prende una data che presenterà la condizione ;
	 *@return result chi una list di DTO di libro che lo restituisce come risultato del libro che si è fatto la get a partire dalla data di uscita mostrando solo quelli con data di uscita maggiore alla data del DTO passato in input.
	 */
	@Override
	public List<LibroDTO> getByDateGreaterThan(Date dataDiRiferimento) {
		// TODO Auto-generated method stub
		Optional<List<Libro>> libriDataCheck=libroRepository.findByDatauscitaGreaterThan(dataDiRiferimento);
		if(libriDataCheck.isPresent()) {
			List<Libro> libriData=libriDataCheck.get();
			List<LibroDTO> libriDTO=new ArrayList<LibroDTO>();
			for(Libro l:libriData) {
				List<String> autoriNomi = new ArrayList<String>();
				String nomeCasaEditrice = casaEditriceRepository.findById(l.getCasaEditrice().getId()).get().getNome();
				List<Autore> autori=autoreRepository.findByLibri(l);
				System.out.println("siamo nel Service dopo casa editrice "+autori);

				for (Autore autoreName: autori) {
					System.out.println("siamo nel Service dopo casa editrice "+nomeCasaEditrice);
					Autore a =autoreRepository.findByNome(autoreName.getNome()).get() ;
					autoriNomi.add(a.getCognome());
				}
				
				LibroDTO lDTO=new LibroDTO(l.getId(),l.getTitolo(),l.getTrama(),l.getData_uscita(),autoriNomi,nomeCasaEditrice);
				libriDTO.add(lDTO);
			}
			return libriDTO ;}
		else return null;
	}

	/* 
	 *@param libroDTO prende una stringa che rappresenta che vogliamo controllare che esiste nel titolo del libro ;
	 *@return result chi una list di DTO di libro che lo restituisce come risultato del libro che si è fatto la get a partire dalla data di uscita mostrando solo quelli con il titolo contiene la stringa del titolo del DTO passato in input.
	 */

	@Override
	public List<LibroDTO> getByTitoloContainingIgnoreCase(String condition) {
		// TODO Auto-generated method stub
		Optional<List<Libro>> libriCheck=libroRepository.findByTitoloContainingIgnoreCase(condition);
		System.out.println("siamo nel Service dopo find ");
		if(libriCheck.isPresent()) {
			List<Libro> libri=libriCheck.get();
			List<LibroDTO> libriDTO=new ArrayList<LibroDTO>();
			for(Libro l:libri) {
				List<String> autoriNomi = new ArrayList<String>();
				String nomeCasaEditrice = casaEditriceRepository.findById(l.getCasaEditrice().getId()).get().getNome();
				System.out.println("siamo nel Service dopo find casa editrice " + nomeCasaEditrice );
				List<Autore> autori=autoreRepository.findByLibri(l);
				for (Autore autoreName: autori) {
					Autore a =autoreRepository.findByNome(autoreName.getNome()).get() ;
					autoriNomi.add(a.getCognome());
				}
				LibroDTO lDTO=new LibroDTO(l.getId(),l.getTitolo(),l.getTrama(),l.getData_uscita(),autoriNomi,nomeCasaEditrice);
				libriDTO.add(lDTO);
			}
			return libriDTO ;}
		return null;

	}

}
