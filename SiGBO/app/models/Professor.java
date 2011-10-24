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
	private int disciplina;

	public Professor (String nome, int disciplina) {
		this.nome = nome;
		this.disciplina = disciplina;
	}
		
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
   
}
