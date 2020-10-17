package com.mixplaytv.calendar.calendar.modelo;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Evento {

	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String aula;	
	private String professor;
	private String diaSemana;
	private Integer hora;
	private String status = "Não Começou";


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAula() {
		return aula;
	}

	public void setAula(String aula) {
		this.aula = aula;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public Integer getHora() {
		return hora;
	}

	public void setHora(Integer hora) {
		this.hora = hora;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}
	
	
	public void status(Evento evento) {
		
		if(LocalDateTime.now().getHour() > this.hora) {
			this.status = "Terminou";
		}else {
			if(LocalDateTime.now().getHour() < this.hora) {
				if(LocalDateTime.now().getHour()+1 == this.hora) {
					this.status = "Proxima";
				}else {
					this.status = "Não Começou";
				}
			} else {
				this.status = "Ao Vivo";
			}
		}
	}
	
	
	
}