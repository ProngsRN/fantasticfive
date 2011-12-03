package controllers;

import java.sql.*;

import play.*;

import play.mvc.*;
import play.data.validation.*;
import play.db.jpa.JPA;

import java.util.*;

import models.*;
import play.mvc.*;

import java.util.*;

import javax.persistence.EntityManager;

import org.bouncycastle.asn1.ocsp.ServiceLocator;

import models.*;
import net.sf.oval.constraint.MinSize;

public class Application extends Controller {

	@Before(only = { "administrador" })
	static void checkAdmin() {
		if (!session.contains("admin"))
			index();
	}

	@Before(only = { "user" })
	static void checkUser() {
		if (!session.contains("user"))
			index();
	}

	public static void index() {
		render();
	}

	public static void login(String usuario, String senha) throws SQLException {

		Banco banco = new Banco();
		banco.conectar();
		if (usuario.equals("admin") && senha.equals("123")) {
			session.put("admin", "yes");
			administrador();
		}
		String sql = ("SELECT * FROM usuario WHERE usuario = '" + usuario
				+ "' AND senha = '" + senha + "'");
		ResultSet rs = banco.consultar(sql);
		if (rs.next()) {
			int tipo = rs.getInt("tipo");
			if (tipo == 1) {
				session.put("user", rs.getLong("idusuarioref"));
				session.put("user-nome", rs.getString("usuario"));
				user();
			} else
				flash.error("Pagina do Professor em construçao.");
		} else
			flash.error("Usuario e Senha nao conferem");
		index();
	}

	public static void logout() {
		session.clear();
		index();
	}

	public static void user() throws SQLException {

		long id = Long.valueOf(session.get("user"));
		Aluno aluno = Aluno.findById(id);
		
		List<Aluno> colegas = aluno.getColegas();
		List<AlunoDisciplinas> disciplinas = null;
		disciplinas = aluno.getAlunoDisciplinas();
		render(aluno, colegas, disciplinas);
	}

	public static void uploadPicture(Picture picture)
			throws NumberFormatException, SQLException {
		
		Long id = Long.valueOf(session.get("user"));
		Aluno aluno = Aluno.findById(id);
		if (aluno.getAvatar() != 0) {
			aluno.deleteAvatar();
		}
		picture.setIdAluno(id);
		picture.save();
		aluno.setAvatar(picture.getId());
		aluno.save();

		user();
	}

	public static void getPicture(long idAvatar) {

		Picture picture = Picture.findById(idAvatar);
		if (picture != null) {
			response.setContentTypeIfNotSet(picture.image.type());
			renderBinary(picture.image.get());
		}
	}

	public static void administrador() {
		render();
	}

	public static void admalunos() {
		List<Aluno> alunos = Aluno.findAll();
		render(alunos);
	}

	public static void gerenciaraluno() {
		List<Aluno> alunos = Aluno.findAll();
		render(alunos);
	}

	public static void gerenciarprofessor() {
		List<Professor> professores = Professor.findAll();
		render(professores);
	}

	public static void gerenciarturma() {
		List<Turma> turmas = Turma.findAll();
		render(turmas);
	}

	public static void gerenciardisciplina() {
		List<Disciplina> disciplinas = Disciplina.findAll();
		render(disciplinas);
	}

	public static void admalunodisciplina() {
		List<AlunoDisciplinas> alunodisciplina = AlunoDisciplinas.findAll();
		List<Disciplina> disciplinas = Disciplina.findAll();
		List<Aluno> alunos = Aluno.findAll();

		render(alunodisciplina, alunos, disciplinas);
	}

	public static void admprofessordisciplina() {
		List<ProfessorDisciplinas> professordisciplina = ProfessorDisciplinas
				.findAll();
		List<Professor> professores = Professor.findAll();
		List<Disciplina> disciplinas = Disciplina.findAll();
	
		render(professores, disciplinas, professordisciplina);
	}

	public static void admboletins() {
		render();
	}

	public static void admnotas() {
		List<AlunoDisciplinas> alunodisciplina = AlunoDisciplinas.findAll();
		List<AlunoDisciplinas> alunos = new ArrayList<AlunoDisciplinas>();
		List<AlunoDisciplinas> disciplinas = new ArrayList<AlunoDisciplinas>();
		List<Long> listaid = new ArrayList<Long>();
		
		for (AlunoDisciplinas a : alunodisciplina) {
			if (!listaid.contains(a.getIdAluno())) {
				alunos.add(a);
				listaid.add(a.getIdAluno());
			}
		}
		
		listaid.clear();
		for (AlunoDisciplinas d : alunodisciplina) {
			if (!listaid.contains(d.getIdDisciplina())) {
				disciplinas.add(d);
				listaid.add(d.getIdDisciplina());
			}
		}
		
		int bim = 0;
		render(alunodisciplina, alunos, disciplinas, bim);
	}

	public static void admprofessores() {
		List<Professor> professores = Professor.findAll();
		render(professores);
	}

	public static void admdisciplinas() {
		List<Disciplina> disciplinas = Disciplina.findAll();
		List<Turma> turmas = Turma.findAll();
		render(turmas, disciplinas);
	}

	public static void admturmas() {
		List<Turma> turmas = Turma.findAll();
		render(turmas);
	}

	public static void aluno(long idAluno) {

		Aluno aluno = Aluno.findById(idAluno);
		List<Aluno> colegas = null;
		if (aluno != null) {
			colegas = aluno.getColegas();
		}
		if ( (colegas != null) )
			render(aluno, colegas);
	}

}