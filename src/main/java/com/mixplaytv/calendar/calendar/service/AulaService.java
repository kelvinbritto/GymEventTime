package com.mixplaytv.calendar.calendar.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mixplaytv.calendar.calendar.modelo.Aula;
import com.mixplaytv.calendar.calendar.modelo.AulaForm;
import com.mixplaytv.calendar.calendar.modelo.DiaSemana;
import com.mixplaytv.calendar.calendar.repository.AulaRepository;

@Service
public class AulaService {

	@Autowired
	private AulaRepository aulaRepository;

	public void salva(Aula aula) {
		aulaRepository.save(aula);
	}

	public Aula aulaAoVivo() {
		List<Aula> eventos = new ArrayList<Aula>();
		Aula eventoAoVivo = null;

		eventos.addAll(aulaRepository.findByDiaSemana(DiaSemana.SEGUNDA));
		
		System.out.println(eventos.size());

		Integer hora = LocalDateTime.now().getHour();
		Integer min = LocalDateTime.now().getMinute();

		if (min >= 50) {
			hora++;
		}

		for (Aula evento : eventos) {
			if (evento.getHorario().getHour() == hora) {
				eventoAoVivo = evento;
			}
		}

		if (eventoAoVivo == null) {
			return bomDiaeBoaNoite();
		}

		alteraStatus(eventoAoVivo);

		return eventoAoVivo;
	}

	public List<Aula> proximasAulas() {

		var diaPortugues = traduzDia(LocalDateTime.now().getDayOfWeek().toString());

		List<Aula> eventos = aulaRepository.findByDiaSemana(diaPortugues);

		aulasFaltam(eventos);

		for (Aula evento2 : eventos) {
			alteraStatus(evento2);
		}

		return eventos;
	}

	public List<Aula> aulasDia() {

		var diaPortugues = traduzDia(LocalDateTime.now().getDayOfWeek().toString());
		List<Aula> eventos = aulaRepository.findByDiaSemana(diaPortugues);

		for (Aula evento2 : eventos) {
			alteraStatus(evento2);
		}

		return eventos;
	}

	public Aula alteraAula(AulaForm aula) {

		List<Aula> eventosDia = aulaRepository.findByDiaSemana(DiaSemana.valueOf(aula.getDiaSemana()));
		Aula evento = null;

		for (Aula evento1 : eventosDia) {
			if (evento1.getHorario().getHour() == aula.getHora()) {
				evento = evento1;
			}
		}

		if (evento == null) {
			return null;
		}

		evento.setModalidade(aula.getAula());
		evento.setProfessor(aula.getProfessor());

		if (aula.getUrlLogo() != null || aula.getUrlLogo() != "") {
			evento.setUrlLogo(aula.getUrlLogo());
		}

		aulaRepository.save(evento);

		return evento;
	}

	public DiaSemana traduzDia(String diaIngles) {

		switch (diaIngles) {

		case "MONDAY":
			return DiaSemana.SEGUNDA;

		case "TUESDAY":
			return DiaSemana.TERCA;

		case "WEDNESDAY":
			return DiaSemana.QUARTA;

		case "THURSDAY":
			return DiaSemana.QUINTA;

		case "FRIDAY":
			return DiaSemana.SEXTA;

		case "SATURDAY":
			return DiaSemana.SABADO;

		case "SUNDAY":
			return DiaSemana.DOMINGO;

		default:
			return null;
		}

	}

	public void aulasFaltam(List<Aula> aulasDia) {

		int horaAtual = LocalDateTime.now().getHour();
		int horaInicio = 7;

		if (horaAtual == 7 || horaAtual == 6) {
			horaInicio = 6;
		}

		int i1 = horaAtual - horaInicio;
		int i2 = 0;

		if (horaAtual == horaInicio) {
			i2 = 0;
		} else {
			i2 = 1;
		}

		while (i1 != i2) {
			aulasDia.remove(aulasDia.iterator().next());
			i2++;
		}

	}

	public void alteraStatus(Aula aula) {

		int horaAtual = LocalDateTime.now().getHour();

		if (horaAtual == aula.getHorario().getHour() && LocalDateTime.now().getMinute() >= 50
				|| horaAtual > aula.getHorario().getHour()) {
			aula.setStatus("Terminou");
		} else {
			if (horaAtual < aula.getHorario().getHour()) {
				if (horaAtual + 1 == aula.getHorario().getHour()) {
					aula.setStatus("A Seguir");
				} else {
					aula.setStatus("");
				}
			} else {
				aula.setStatus("Ao Vivo");
			}
		}

		aulaRepository.save(aula);
	}

	public Aula bomDiaeBoaNoite() {

		int hora = LocalDateTime.now().getHour();

		if (hora > 19) {
			Aula eventoNovo = new Aula();
			eventoNovo.setModalidade("BOA NOITE!!");
			eventoNovo.setId(99L);
			eventoNovo.setUrlLogo("https://i.ibb.co/LkMPjMv/Mixplaytv-Logo.png");
			return eventoNovo;
		}

		Aula eventoNovo = new Aula();
		eventoNovo.setModalidade("BOM DIA!!!");
		eventoNovo.setId(99L);
		eventoNovo.setUrlLogo("https://i.ibb.co/LkMPjMv/Mixplaytv-Logo.png");
		return eventoNovo;
	}

}