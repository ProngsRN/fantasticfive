package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;
import sun.util.calendar.LocalGregorianCalendar.Date;

import javax.persistence.*;
import java.util.*;


@Entity
public class Aluno extends Model {
    
	@Required
	private String nome;
	
	private long avatar;
	
	public Aluno (String nome) {
		this.setNome(nome);
		this.setAvatar(0);
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
	
	public String getNomeUsuario() {
		
		List<Usuario> usuarios = Usuario.findAll(); 
		String user = "";
		for (Usuario u : usuarios) {
			if (u.getIdUsuarioRef() == this.getId()) {
				user = u.getUsuario();
			}
		}
		return user;
	}
	
	public String getSenhaUsuario() {
		
		List<Usuario> usuarios = Usuario.findAll(); 
		String senha = "";
		for (Usuario u : usuarios) {
			if (u.getIdUsuarioRef() == this.getId()) {
				senha = u.getSenha();
			}
		}
		return senha;
	}

	public void setAvatar(long avatar) {
		this.avatar = avatar;
	}

	public long getAvatar() {
		return avatar;
	}
	
	public List<Aluno> getColegas () {
		
		List<AlunoDisciplinas> alunos = AlunoDisciplinas.findAll();
		List<Aluno> colegas = new ArrayList<Aluno>();
		for (AlunoDisciplinas a : alunos) {
			if (a.getIdAluno() == id) {
				long idDisciplina = a.getIdDisciplina();
				for (AlunoDisciplinas b : alunos) {
					if ( (b.getIdDisciplina() == idDisciplina) && (b.getIdAluno() != id)){
						colegas.add((Aluno) Aluno.findById(b.getIdAluno()));
					}
				}
			}
		}
		return colegas;
	}
	
}
