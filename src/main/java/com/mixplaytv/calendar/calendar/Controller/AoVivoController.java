package com.mixplaytv.calendar.calendar.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mixplaytv.calendar.calendar.modelo.AulaForm;
import com.mixplaytv.calendar.calendar.modelo.Evento;
import com.mixplaytv.calendar.calendar.modelo.Suporte;
import com.mixplaytv.calendar.calendar.repository.EventoRepository;

@RestController
public class AoVivoController {

	@Autowired
	private EventoRepository eventoRepository;

	@RequestMapping(method = RequestMethod.GET, value = "aovivo")
	public ResponseEntity<Evento> aoVivo() {

		List<Evento> eventos = new ArrayList<Evento>();

		Suporte suporte = new Suporte();

		eventos.addAll(
				eventoRepository.findBydiaSemana(suporte.traduzDia(LocalDateTime.now().getDayOfWeek().toString())));

		Integer hora = LocalDateTime.now().getHour();
		Integer min = LocalDateTime.now().getMinute();

		Evento evento = null;

		if (min >= 50) {
			hora++;
		}

		for (Evento evento2 : eventos) {
			if (evento2.getHora() == hora) {
				evento = evento2;
			}
		}

		if (evento == null) {

			if (hora > 21) {

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

		suporte.AlteraStatus(evento);

		return ResponseEntity.ok(evento);
	}

	@RequestMapping(method = RequestMethod.GET, value = "proximas")
	public ResponseEntity<List<Evento>> proximas() {

		Suporte suporte = new Suporte();

		String diaPortugues = suporte.traduzDia(LocalDateTime.now().getDayOfWeek().toString());

		List<Evento> eventos = eventoRepository.findBydiaSemana(diaPortugues);

		suporte.aulasFaltam(eventos);

		for (Evento evento2 : eventos) {
			suporte.AlteraStatus(evento2);
		}

		return ResponseEntity.ok(eventos);
	}

	@RequestMapping(method = RequestMethod.GET, value = "aulasdia")
	public ResponseEntity<List<Evento>> aulasdia() {

		Suporte suporte = new Suporte();

		String diaPortugues = suporte.traduzDia(LocalDateTime.now().getDayOfWeek().toString());

		List<Evento> eventos = eventoRepository.findBydiaSemana(diaPortugues);

		for (Evento evento2 : eventos) {
			suporte.AlteraStatus(evento2);
		}

		return ResponseEntity.ok(eventos);
	}

	@RequestMapping(method = RequestMethod.POST, value = "alteraaula")
	public ResponseEntity<Evento> alteraAula(@RequestBody AulaForm aula) {
		
		
		List<Evento> eventosDia = eventoRepository.findBydiaSemana(aula.getDiaSemana());
		Evento evento = null;
		
		for (Evento evento1 : eventosDia) {
			if (evento1.getHora() == aula.getHora()) {
				evento = evento1;
			}	
		}
		
		if (evento == null) {
			return ResponseEntity.badRequest().build();
		}
		
		evento.setAula(aula.getAula());
		evento.setProfessor(aula.getProfessor());
		
		eventoRepository.save(evento);
		
		return ResponseEntity.ok(evento);
		
	}

}
