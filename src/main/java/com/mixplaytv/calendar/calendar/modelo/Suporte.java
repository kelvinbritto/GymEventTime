package com.mixplaytv.calendar.calendar.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Suporte {

	public String traduzDia(String diaIngles) {

		switch (diaIngles) {

		case "MONDAY":
			return "Segunda";

		case "TUESDAY":
			return "Terça";

		case "FOURTH":
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

	public List<Evento> aulasFaltam(List<Evento> eventosDia) {

		List<Evento> lista = new ArrayList<Evento>();
		int horaAtual = LocalDateTime.now().getHour();
		int horaInicio = 7;
		
		
		
		int i1 = horaAtual - horaInicio;
		int i2 = 0;
		
		if(horaAtual == horaInicio) {
			 i2 = 0;			
		}else {
			 i2 = 1;			
		}
		
		while (i1 != i2) {
			
			eventosDia.remove(eventosDia.iterator().next());
			i2++;
		
		}
		
		lista.addAll(eventosDia);		

		return lista;

	}

}