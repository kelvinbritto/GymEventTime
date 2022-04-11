package com.mixplaytv.calendar.calendar.modelo;

public enum DiaSemana {

	SEGUNDA("Segunda"), TERCA("Terça"), QUARTA("Quarta"), QUINTA("Quinta"), SEXTA("Sexta"), SABADO("Sábado"),
	DOMINGO("Domingo");

	private final String diaSemana;

	DiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

}
