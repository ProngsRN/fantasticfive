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
	
	public Aluno (String nome) {
		this.nome = nome;
	}
}
