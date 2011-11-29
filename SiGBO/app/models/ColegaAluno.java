package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class ColegaAluno extends Model {
	
	private long idAluno;
	private long idColega;
	
	public ColegaAluno(long idAluno, long idColega) {
		this.idAluno = idAluno;
		this.idColega = idColega;
	}
	
	public void setIdAluno(long idAluno) {
		this.idAluno = idAluno;
	}
	public long getIdAluno() {
		return idAluno;
	}
	public void setIdColega(long idColega) {
		this.idColega = idColega;
	}
	public long getIdColega() {
		return idColega;
	}
}
