package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Professor extends Model {
	
	@Required
	private String nome;
	
	@Required
	private long disciplina;

	public Professor (long disciplina, String nome) {
		this.nome = nome;
		this.setDisciplina(disciplina);
	}
		
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setDisciplina(long disciplina) {
		this.disciplina = disciplina;
	}

	public long getDisciplina() {
		return disciplina;
	}
   
}
