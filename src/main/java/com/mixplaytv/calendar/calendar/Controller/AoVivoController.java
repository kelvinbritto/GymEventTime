package com.mixplaytv.calendar.calendar.Controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import com.mixplaytv.calendar.calendar.modelo.Aula;
import com.mixplaytv.calendar.calendar.modelo.AulaForm;
import com.mixplaytv.calendar.calendar.modelo.DiaSemana;
import com.mixplaytv.calendar.calendar.service.AulaService;

@RestController
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class AoVivoController {

	@Autowired
	private AulaService aulaService;

	@GetMapping("/teste")
	public ResponseEntity<Aula> teste() {

		Aula aula = new Aula();

		aula.setDiaSemana(DiaSemana.SEGUNDA);
		aula.setHorario(LocalTime.of(12, 0));
		aula.setProfessor("Professor Fulano");
		aula.setModalidade("Power Jump");
		aulaService.salva(aula);

		return ResponseEntity.ok(aula);

	}

	@GetMapping("/aovivo")
	public ResponseEntity<Aula> aoVivo() {

		return ResponseEntity.ok(aulaService.aulaAoVivo());
	}

	@GetMapping("/proximas")
	public ResponseEntity<List<Aula>> proximas() {

		return ResponseEntity.ok(aulaService.proximasAulas());
	}

	@GetMapping("/aulasdia")
	public ResponseEntity<List<Aula>> aulasdia() {

		return ResponseEntity.ok(aulaService.aulasDia());
	}

	@PostMapping("/alteraaula")
	public ResponseEntity<Aula> alteraAula(@RequestBody AulaForm aula) {

		Aula evento = aulaService.alteraAula(aula);

		if (evento == null) {
			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(evento);
	}

}
