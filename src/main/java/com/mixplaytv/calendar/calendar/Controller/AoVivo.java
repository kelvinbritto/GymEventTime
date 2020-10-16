package com.mixplaytv.calendar.calendar.Controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mixplaytv.calendar.calendar.modelo.Evento;
import com.mixplaytv.calendar.calendar.modelo.Suporte;
import com.mixplaytv.calendar.calendar.repository.EventoRepository;

@RestController
public class AoVivo {
	
	@Autowired
	private EventoRepository eventoRepository;
	
	
	@RequestMapping(method = RequestMethod.GET, value = "aovivo")
	public ResponseEntity<Evento> aoVivo() {
		
		
		Integer hora = LocalDateTime.now().getHour();
		
		if(LocalDateTime.now().getMinute() >= 45) {
			hora = LocalDateTime.now().getHour()+1;
		}
		
		System.out.println(hora);
		
		Evento evento = eventoRepository.findByHora(hora);

		return ResponseEntity.ok(evento);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "proxima")
	public ResponseEntity<Evento> proximaAula() {
		
		Evento evento = eventoRepository.findByHora(LocalDateTime.now().getHour() + 1);
			
		return ResponseEntity.ok(evento);
		
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "proximas")
	public ResponseEntity<List<Evento>> proximas() {
		
		Suporte suporte = new Suporte();
		String diaIngles = LocalDateTime.now().getDayOfWeek().toString();
		
		List<Evento> eventos = eventoRepository.findBydiaSemana(suporte.traduzDia(diaIngles));
		
		List<Evento> evento = suporte.aulasFaltam(eventos);
		
		
		
		return ResponseEntity.ok(evento);
	}
	
	
	
}
