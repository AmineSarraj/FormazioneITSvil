package com.example.Libreria.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Libreria.entity.Autore;
import com.example.Libreria.entity.Casa_Editrice;
import com.example.Libreria.entity.Libro;
import com.example.Libreria.entity.Ubicazione;

/* 
*@author Sarraj Amine
*/
public interface AutoreRepository extends JpaRepository<Autore, Long>{
	//Autore findByNome(String name);
	Optional <Autore> findByNome(String name);
	List<Autore> findByLibri(Libro libro);

}
