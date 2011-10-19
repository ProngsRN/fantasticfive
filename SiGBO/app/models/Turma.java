package models;

import javax.persistence.Entity;

import net.sf.oval.constraint.MinSize;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Turma extends Model{

	@Required
	private int ano;
	
	@Required
	private int nivel;
	
	@Required
	@MinSize(1)
	private String sala;
	
	public Turma(int ano, int nivel, String sala) {
		this.ano = ano;
		this.nivel = nivel;
		this.sala = sala;
	}
	
	
	public int getAno() {
		return ano;
	}
	
	public int getNivel() {
		return nivel;
	}
	
	public String getSala() {
		return sala;
	}
	
	public void setAno (int ano) {
		this.ano = ano;
	}
	
	public void setNivel (int nivel) {
		this.nivel = nivel;
	}
	
	public void setSala (String sala) {
		this.sala = sala;
	}
}
