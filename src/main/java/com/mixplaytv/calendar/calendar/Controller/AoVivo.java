package com.mixplaytv.calendar.calendar.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
	
	private LocalDateTime hora = LocalDateTime.now();
	
	@RequestMapping(method = RequestMethod.GET, value = "aovivo")
	public ResponseEntity<Evento> aoVivo() {
		
		List<Evento> eventos = new ArrayList<Evento>();
		
		//Suporte suporte = new Suporte();
		
		
		eventos.addAll(eventoRepository.findBydiaSemana("Sexta"));
		//eventos.addAll(eventoRepository.findBydiaSemana(suporte.traduzDia(LocalDateTime.now().getDayOfWeek().toString())));
		
		Integer hora = this.hora.getHour();
		
		Evento evento = null;
		
			
		for (Evento evento2 : eventos) {
			if(evento2.getHora() == 16) {
				evento  = evento2;
			}
		}
		
		if(evento == null) {
			
			if(hora > 21) {
				
				Evento eventoNovo = new Evento();
				eventoNovo.setAula("BOA NOITE!!");
				eventoNovo.setId(99L);
				return ResponseEntity.ok(eventoNovo);
			
			}
			
			Evento eventoNovo = new Evento();
			eventoNovo.setAula("BOM DIA!!!");
			eventoNovo.setId(99L);
			return ResponseEntity.ok(eventoNovo);
		}
		
		evento.status(evento);
				
		return ResponseEntity.ok(evento);
	}
	
	
	
	@RequestMapping(method = RequestMethod.GET, value = "proximas")
	public ResponseEntity<List<Evento>> proximas() {
		
		Suporte suporte = new Suporte();
		
		//String diaIngles = LocalDateTime.now().getDayOfWeek().toString();
		
		List<Evento> eventos = eventoRepository.findBydiaSemana("Sexta");
		
		suporte.aulasFaltam(eventos);
		
		for (Evento evento2 : eventos) {
			evento2.status(evento2);
		}
		
		
		return ResponseEntity.ok(eventos);
	}
	
}
