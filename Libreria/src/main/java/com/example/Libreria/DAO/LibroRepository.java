package com.example.Libreria.DAO;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Libreria.DTO.LibroDTO;
import com.example.Libreria.entity.Autore;
import com.example.Libreria.entity.Libro;


/* 
*@author Sarraj Amine
*/
@Repository
public interface LibroRepository extends JpaRepository<Libro, Long>  {
	Optional<Libro> findByTitolo(String TitoloLibro);
	Optional<List<Libro>> findByDatauscitaGreaterThan(Date dataDiRiferimento);
	Optional<List<Libro>> findByTitoloContainingIgnoreCase(String condition);
	Optional<List<Libro>> findByAutore(Autore autore);

}
