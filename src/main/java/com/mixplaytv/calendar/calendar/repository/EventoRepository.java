package com.mixplaytv.calendar.calendar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mixplaytv.calendar.calendar.modelo.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {
	
	Evento findByAula(String aula);

	Evento findByHora(Integer hora);
	
	List<Evento> findBydiaSemana(String dia);
	
}
