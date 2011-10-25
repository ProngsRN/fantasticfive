package models;

import javax.persistence.Entity;

import net.sf.oval.constraint.MinSize;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Disciplina extends Model {

	@Required
	private String nome;
	
	@Required
	private long turma;
	
	public Disciplina (String nome, long turma) {
		this.setNome(nome);
		this.setTurma(turma);
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setTurma(long turma) {
		this.turma = turma;
	}

	public long getTurma() {
		return turma;
	}
	
	
}
