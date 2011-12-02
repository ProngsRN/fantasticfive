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
		int reg = 1;
		if (aluno.getAvatar() == 0) {
			reg = 0;
		}
		List<Aluno> colegas = aluno.getColegas();
		List<AlunoDisciplinas> disciplinas = null;
		disciplinas = aluno.getAlunoDisciplinas();
		render(aluno, reg, colegas, disciplinas);
	}

	public static void uploadPicture(Picture picture)
			throws NumberFormatException, SQLException {
		Banco banco = new Banco();
		banco.conectar();
		String sql = "select * from Picture where idAluno = "
				+ session.get("user");
		ResultSet rs = banco.consultar(sql);
		if (!rs.next()) {
			picture.setIdAluno(Long.valueOf(session.get("user")));
			picture.save();
			Aluno aluno = Aluno.findById(Long.valueOf(session.get("user")));
			aluno.setAvatar(picture.getId());
			aluno.save();
		}
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

		int alunosvazio = 1;
		if (alunos.isEmpty())
			alunosvazio = 0;

		render(alunos, alunosvazio);
	}

	public static void gerenciaraluno() {
		List<Aluno> alunos = Aluno.findAll();
		int vazio = 1;
		if (alunos.isEmpty())
			vazio = 0;
		render(alunos, vazio);
	}

	public static void gerenciarprofessor() {
		List<Professor> professores = Professor.findAll();
		int vazio = 1;
		if (professores.isEmpty())
			vazio = 0;
		render(professores, vazio);
	}

	public static void gerenciarturma() {
		List<Turma> turmas = Turma.findAll();
		int vazio = 1;
		if (turmas.isEmpty())
			vazio = 0;
		render(turmas, vazio);
	}

	public static void gerenciardisciplina() {
		List<Disciplina> disciplinas = Disciplina.findAll();
		int vazio = 1;
		if (disciplinas.isEmpty())
			vazio = 0;
		render(disciplinas, vazio);
	}

	public static void admalunodisciplina() {
		List<AlunoDisciplinas> alunodisciplina = AlunoDisciplinas.findAll();
		List<Disciplina> disciplinas = Disciplina.findAll();
		List<Aluno> alunos = Aluno.findAll();

		int vazioaluno = 1;
		if (alunos.isEmpty())
			vazioaluno = 0;

		int vaziodisciplina = 1;
		if (disciplinas.isEmpty())
			vaziodisciplina = 0;

		render(alunodisciplina, alunos, disciplinas, vazioaluno,
				vaziodisciplina);
	}

	public static void admprofessordisciplina() {
		List<ProfessorDisciplinas> professordisciplina = ProfessorDisciplinas
				.findAll();
		List<Professor> professores = Professor.findAll();
		List<Disciplina> disciplinas = Disciplina.findAll();

		int professorvazio = 1;
		if (professores.isEmpty())
			professorvazio = 0;

		int disciplinavazio = 1;
		if (disciplinas.isEmpty())
			disciplinavazio = 0;

		render(professores, disciplinas, professordisciplina, professorvazio,
				disciplinavazio);
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
		
		int alunovazio = 1;
		if (alunodisciplina.isEmpty())
			alunovazio = 0;
		int bim = 0;
		render(alunodisciplina, alunos, disciplinas, alunovazio, bim);
	}

	public static void habilitaracessoaluno() {
		List<Aluno> alunos = Aluno.findAll();
		List<Usuario> usuarios = Usuario.findAll();
		render(alunos, usuarios);
	}

	public static void admprofessores() {
		List<Professor> professores = Professor.findAll();

		int vazio = 1;
		if (professores.isEmpty())
			vazio = 0;
		render(professores, vazio);
	}

	public static void admdisciplinas() {
		List<Disciplina> disciplinas = Disciplina.findAll();
		List<Turma> turmas = Turma.findAll();

		int turmavazia = 1, disciplinavazia = 1;

		if (disciplinas.isEmpty())
			disciplinavazia = 0;
		if (turmas.isEmpty())
			turmavazia = 0;

		render(turmas, disciplinas, disciplinavazia, turmavazia);
	}

	public static void admturmas() {
		List<Turma> turmas = Turma.findAll();

		int vazia = 1;
		if (turmas.isEmpty())
			vazia = 0;
		render(turmas, vazia);
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
