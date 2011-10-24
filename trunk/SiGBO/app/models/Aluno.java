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
	private int turma;
	
	@Required
	private int disciplina;
	
	public Aluno (String nome, int turma, int disciplina) {
		this.nome = nome;
		this.turma = turma;
		this.disciplina = disciplina;
	}
}
