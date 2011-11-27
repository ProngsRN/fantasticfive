package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Picture extends Model {
    
	private Long idAluno;
    
	public Blob image;

	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
	}

	public Long getIdAluno() {
		return idAluno;
	}
}
