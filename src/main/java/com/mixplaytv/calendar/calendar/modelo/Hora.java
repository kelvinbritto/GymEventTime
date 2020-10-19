package com.mixplaytv.calendar.calendar.modelo;

import java.time.LocalDateTime;

public class Hora {

	private int hora = LocalDateTime.now().getHour();
	private int minutos = LocalDateTime.now().getMinute();
	private int segundos = LocalDateTime.now().getSecond();
	
	
	public int getHora() {
		return hora;
	}

	
	public int getMinutos() {
		return minutos;
	}

	
	public int getSegundos() {
		return segundos;
	}
	
	
}
