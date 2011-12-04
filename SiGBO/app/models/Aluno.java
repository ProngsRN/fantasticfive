package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;
import sun.util.calendar.LocalGregorianCalendar.Date;

import javax.persistence.*;

import controllers.Banco;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Entity
public class Aluno extends Model {

	@Required
	private String nome;

	private long avatar;

	public Aluno(String nome) {
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
	
	public void deleteAvatar() {
		Picture picture = Picture.findById(avatar);
		picture.delete();
	}

	public List<Aluno> getColegas() {

		List<AlunoDisciplinas> alunos = AlunoDisciplinas.findAll();
		List<Aluno> colegas = new ArrayList<Aluno>();
		List<Long> listaid = new ArrayList<Long>();
		for (AlunoDisciplinas a : alunos) {
			if (a.getIdAluno() == id) {
				long idDisciplina = a.getIdDisciplina();
				for (AlunoDisciplinas b : alunos) {
					if ((b.getIdDisciplina() == idDisciplina)
							&& (b.getIdAluno() != id) && (!listaid.contains(b.getIdAluno()))) {
						colegas.add((Aluno) Aluno.findById(b.getIdAluno()));
						listaid.add(b.getIdAluno());
					}
				}
			}
		}
		return colegas;
	}

	public List<AlunoDisciplinas> getAlunoDisciplinas() {

		List<AlunoDisciplinas> alunodisciplinas = new ArrayList<AlunoDisciplinas>();
		List<AlunoDisciplinas> lista = AlunoDisciplinas.findAll();
		for (AlunoDisciplinas ad : lista) {
			if (ad.getIdAluno() == id) {
				alunodisciplinas.add(ad);
			}
		}
		return alunodisciplinas;
	}
	
	public List<Recado> getRecados(boolean privado) {

		List<Recado> lista = Recado.find("order by data desc").fetch();
		List<Recado> recados = new ArrayList<Recado>();
		for (Recado r : lista) {
			if ( (r.getIdDestinatario() == id) && (r.isPrivado() == privado)) {
				recados.add(r);
			}
		}
		return recados;
	}

}