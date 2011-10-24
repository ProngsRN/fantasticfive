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
		this.nome = nome;
		this.turma = turma;
	}
	
	
}
