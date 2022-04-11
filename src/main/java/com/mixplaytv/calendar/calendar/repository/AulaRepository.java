package com.mixplaytv.calendar.calendar.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mixplaytv.calendar.calendar.modelo.Aula;
import com.mixplaytv.calendar.calendar.modelo.DiaSemana;

public interface AulaRepository extends JpaRepository<Aula, Long> {

	Optional<Aula> findByModalidade(String aula);

	// Aula findByHora(Integer hora);

	List<Aula> findByDiaSemana(DiaSemana diaSemana);

}
