package com.example.Libreria.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Libreria.entity.Casa_Editrice;
import com.example.Libreria.entity.Libro;

/* 
*@author Sarraj Amine
*/
public interface CasaEditriceRepository extends JpaRepository<Casa_Editrice, Long>{
	Optional<Casa_Editrice> findByNome(String name);
	//Optional<Casa_Editrice> findByLibri(Libro libro);
}
