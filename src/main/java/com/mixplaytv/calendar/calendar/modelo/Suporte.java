package com.mixplaytv.calendar.calendar.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.mixplaytv.calendar.calendar.repository.EventoRepository;

public class Suporte {


	public Evento aulaAoVivo(EventoRepository eventoRepository) {
		List<Evento> eventos = new ArrayList<Evento>();
		Evento eventoAoVivo = null;
		
		String diaPortugues = traduzDia(LocalDateTime.now().getDayOfWeek().toString());
		eventos.addAll(eventoRepository.findBydiaSemana(diaPortugues));
		
		Integer hora = LocalDateTime.now().getHour();
		Integer min = LocalDateTime.now().getMinute();
		
		if (min >= 50) {
			hora++;
		}

		for (Evento evento : eventos) {
			if (evento.getHora() == hora) {
				eventoAoVivo = evento;
			}
		}
		

		if (eventoAoVivo == null) {
			return BomDiaeBoaNoite();
		}
		
		alteraStatus(eventoAoVivo, eventoRepository);
		
		return eventoAoVivo;
	}
	
	
	public List<Evento> proximasAulas(EventoRepository eventoRepository) {
		
		String diaPortugues = traduzDia(LocalDateTime.now().getDayOfWeek().toString());

		List<Evento> eventos = eventoRepository.findBydiaSemana(diaPortugues);

		aulasFaltam(eventos);

		for (Evento evento2 : eventos) {
			alteraStatus(evento2, eventoRepository);
		}
		
		return eventos;
	}

	public List<Evento> aulasDia(EventoRepository eventoRepository){
		
		String diaPortugues = traduzDia(LocalDateTime.now().getDayOfWeek().toString());
		List<Evento> eventos = eventoRepository.findBydiaSemana(diaPortugues);

		for (Evento evento2 : eventos) {
			alteraStatus(evento2, eventoRepository);
		}
		
		return eventos;
	}
	
	public Evento alteraAula(AulaForm aula, EventoRepository eventoRepository) {
		
		List<Evento> eventosDia = eventoRepository.findBydiaSemana(aula.getDiaSemana());
		Evento evento = null;
		
		for (Evento evento1 : eventosDia) {
			if (evento1.getHora() == aula.getHora()) {
				evento = evento1;
			}	
		}
		
		if (evento == null) {
			return null;
		}
		
		evento.setAula(aula.getAula());
		evento.setProfessor(aula.getProfessor());
		
		
		if(aula.getUrlLogo() != null || aula.getUrlLogo() != "") {
			evento.setUrlLogo(aula.getUrlLogo());
		}

		eventoRepository.save(evento);
		
		return evento;
	}
	
	public String traduzDia(String diaIngles) {

		switch (diaIngles) {

		case "MONDAY":
			return "Segunda";

		case "TUESDAY":
			return "Terça";

		case "WEDNESDAY":
			return "Quarta";

		case "THURSDAY":
			return "Quinta";

		case "FRIDAY":
			return "Sexta";

		case "SATURDAY":
			return "Sabado";

		case "SUNDAY":
			return "Domingo";

		}

		return null;
	}

	public void aulasFaltam(List<Evento> eventosDia) {

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
			eventosDia.remove(eventosDia.iterator().next());
			i2++;
		}

	}

	public void alteraStatus(Evento evento, EventoRepository eventoRepository) {

		int horaAtual = LocalDateTime.now().getHour();

		if (horaAtual == evento.getHora() && LocalDateTime.now().getMinute() >= 50 || horaAtual > evento.getHora()) {
			evento.setStatus("Terminou");
		} else {
			if (horaAtual < evento.getHora()) {
				if (horaAtual + 1 == evento.getHora()) {
					evento.setStatus("A Seguir");
				} else {
					evento.setStatus("");
				}
			} else {
				evento.setStatus("Ao Vivo");
			}
		}
		
		eventoRepository.save(evento);
	}

	public Evento BomDiaeBoaNoite() {

		int hora = LocalDateTime.now().getHour();

		if (hora > 19) {
			Evento eventoNovo = new Evento();
			eventoNovo.setAula("BOA NOITE!!");
			eventoNovo.setId(99L);
			eventoNovo.setUrlLogo("https://i.ibb.co/LkMPjMv/Mixplaytv-Logo.png");
			return eventoNovo;
		}

		Evento eventoNovo = new Evento();
		eventoNovo.setAula("BOM DIA!!!");
		eventoNovo.setId(99L);
		eventoNovo.setUrlLogo("https://i.ibb.co/LkMPjMv/Mixplaytv-Logo.png");
		return eventoNovo;
	}

}