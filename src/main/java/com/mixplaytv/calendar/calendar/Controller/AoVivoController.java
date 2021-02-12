package com.mixplaytv.calendar.calendar.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import com.mixplaytv.calendar.calendar.modelo.AulaForm;
import com.mixplaytv.calendar.calendar.modelo.Evento;
import com.mixplaytv.calendar.calendar.modelo.Suporte;
import com.mixplaytv.calendar.calendar.repository.EventoRepository;

@RestController
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class AoVivoController {	
	
	Suporte suporte = new Suporte();
	
	@Autowired
	private EventoRepository eventoRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "aovivo")
	public ResponseEntity<Evento> aoVivo() {
		
		return ResponseEntity.ok(suporte.aulaAoVivo(eventoRepository));
	}

	@RequestMapping(method = RequestMethod.GET, value = "proximas")
	public ResponseEntity<List<Evento>> proximas() {
		
		return ResponseEntity.ok(suporte.proximasAulas(eventoRepository));
	}

	@RequestMapping(method = RequestMethod.GET, value = "aulasdia")
	public ResponseEntity<List<Evento>> aulasdia() {

		return ResponseEntity.ok(suporte.aulasDia(eventoRepository));
	}

	@RequestMapping(method = RequestMethod.POST, value = "alteraaula")
	public ResponseEntity<Evento> alteraAula(@RequestBody AulaForm aula) {
		
		Evento evento = suporte.alteraAula(aula, eventoRepository);
		
		if (evento == null) {
			return ResponseEntity.badRequest().build();
		}	
		
		return ResponseEntity.ok(evento);
	}

}
