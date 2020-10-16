package com.mixplaytv.calendar.calendar.Controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mixplaytv.calendar.calendar.modelo.Evento;
import com.mixplaytv.calendar.calendar.repository.EventoRepository;

@RestController
@RequestMapping("/aovivo")
public class AoVivo {
	
	@Autowired
	private EventoRepository eventoRepository;
	
	
	
	@GetMapping
	public ResponseEntity<Evento> aoVivo() {
		
		
		Evento evento = eventoRepository.findByHora(LocalDateTime.now().getHour());

		return ResponseEntity.ok(evento);
		
	}
	
}
