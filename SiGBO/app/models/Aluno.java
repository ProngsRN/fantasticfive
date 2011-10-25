package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Aluno extends Model {
    
	@Required
	private String nome;

	@Required
	private long disciplina;
	
	public Aluno (String nome, long disciplina) {
		this.nome = nome;
		this.disciplina = disciplina;
	}
}
