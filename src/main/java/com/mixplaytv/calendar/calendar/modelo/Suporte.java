package com.mixplaytv.calendar.calendar.modelo;

import java.time.LocalDateTime;
import java.util.List;

public class Suporte {

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
		} else {
			horaInicio = 7;
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

	public void AlteraStatus(Evento evento) {

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
	}

	public Evento BomDiaeBoaNoite() {

		int hora = LocalDateTime.now().getHour();

		if (hora > 21) {
			Evento eventoNovo = new Evento();
			eventoNovo.setAula("BOA NOITE!!");
			eventoNovo.setId(99L);
			return eventoNovo;
		}

		Evento eventoNovo = new Evento();
		eventoNovo.setAula("BOM DIA!!!");
		eventoNovo.setId(99L);
		return eventoNovo;
	}

}