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

	public static void index() {
		render();
	}

	public static void login(@Required String usuario, @Required String senha) {
		int valid = teste(usuario, senha);
		if (validation.hasErrors()) {
			flash.error("Digite um nome de usuário e senha.");
			index();
		}
		if (valid == 0) {
			administrador();
		} else if (valid == 1) {
			user();
		} else {
			flash.error("Usuário ou Senha incorretos.");
			index();
		}
	}

	public static void user() {
		render();
	}

	public static void administrador() {
		render();
	}

	public static void admalunos() {
		List<Aluno> alunos = Aluno.findAll();
		
		int alunosvazio = 1;
		if (alunos.isEmpty()) alunosvazio = 0;
		
		render(alunos, alunosvazio);
	}
	
	public static void gerenciaraluno() {
		List<Aluno> alunos = Aluno.findAll();
		int vazio = 1;
		if (alunos.isEmpty()) vazio = 0;
		render(alunos,vazio);
	}
	
	public static void gerenciarprofessor() {
		List<Professor> professores = Professor.findAll();
		int vazio = 1;
		if (professores.isEmpty()) vazio = 0;
		render(professores,vazio);
	}

	public static void gerenciarturma() {
		List<Turma> turmas = Turma.findAll();
		int vazio = 1;
		if (turmas.isEmpty()) vazio = 0;
		render(turmas,vazio);
	}
	
	public static void gerenciardisciplina() {
		List<Disciplina> disciplinas = Disciplina.findAll();
		int vazio = 1;
		if (disciplinas.isEmpty()) vazio = 0;
		render(disciplinas, vazio);
	}
	
	public static void admalunodisciplina() {
		List<AlunoDisciplinas> alunodisciplina = AlunoDisciplinas.findAll();
		List<Disciplina> disciplinas = Disciplina.findAll();
		List<Aluno> alunos = Aluno.findAll();
		
		int vazioaluno = 1;
		if (alunos.isEmpty()) vazioaluno = 0;
		
		int vaziodisciplina = 1;
		if (disciplinas.isEmpty()) vaziodisciplina = 0;
		
		render(alunodisciplina, alunos, disciplinas, vazioaluno, vaziodisciplina);
	}

	public static void admprofessordisciplina() {
		List<ProfessorDisciplinas> professordisciplina = ProfessorDisciplinas.findAll();
		List<Professor> professores = Professor.findAll();
		List<Disciplina> disciplinas = Disciplina.findAll();
		
		int professorvazio = 1;
		if (professores.isEmpty()) professorvazio = 0;
		
		int disciplinavazio = 1;
		if (disciplinas.isEmpty()) disciplinavazio = 0;
		
				
		render(professores, disciplinas, professordisciplina, professorvazio, disciplinavazio);
	}

	public static void admboletins() {
		render();
	}

	public static void admnotas() {
		List<AlunoDisciplinas> alunodisciplina = AlunoDisciplinas.findAll();
		
		int alunovazio = 1;
		if (alunodisciplina.isEmpty()) alunovazio = 0;
		
		render(alunodisciplina, alunovazio);
	}

	public static void habilitaracessoaluno() {
		List<Aluno> alunos = Aluno.findAll();
		List<UsuarioAluno> usuarios = UsuarioAluno.findAll();
		render(alunos, usuarios);
	}

	public static void admprofessores() {
		List<Professor> professores = Professor.findAll();
		
		int vazio = 1;
		if (professores.isEmpty()) vazio = 0;
		render(professores, vazio);
	}

	public static void admdisciplinas() {
		List<Disciplina> disciplinas = Disciplina.findAll();
		List<Turma> turmas = Turma.findAll();
		
		int turmavazia = 1, disciplinavazia = 1;
		
		if (disciplinas.isEmpty()) disciplinavazia = 0;
		if (turmas.isEmpty()) turmavazia = 0;
		
		render(turmas, disciplinas, disciplinavazia, turmavazia);
	}

	public static void habilitarAcessoAluno(@Required String nome,
			@Required String senha, @Required String senha2, String email) {

		validation.email(email);
		validation.equals(senha, senha2);

		if (validation.hasErrors()) {
			flash
					.error("Um ou mais campos não foram preenchidos corretamente.");
		} else {
			UsuarioAluno aluno = new UsuarioAluno(nome, senha, email);
			aluno.save();
		}

	}

	public static void admturmas() {
		List<Turma> turmas = Turma.findAll();
		
		int vazia = 1;
		if (turmas.isEmpty()) vazia = 0;
		render(turmas, vazia);
	}

	public static int teste(String usuario, String senha) {
		int retorno = -1; // Invalid
		String[] usuarios = new String[5];
		String[] senhas = new String[5];

		usuarios[0] = "duarte";
		usuarios[1] = "tiago";
		usuarios[2] = "lock";
		usuarios[3] = "tadeus";
		usuarios[4] = "prongs";

		senhas[0] = "bomba";
		senhas[1] = "jared";
		senhas[2] = "macaco";
		senhas[3] = "ronaldo";
		senhas[4] = "pontas";

		if (usuario.equals("admin") && senha.equals("123"))
			retorno = 0; // Admin
		else {
			int i = 0;
			while (i < 5) {
				if ((usuarios[i].equals(usuario)) && (senhas[i].equals(senha))) {
					retorno = 1; // User
					i = 5;
				} else
					i++;
			}
		}
		return retorno;
	}

}
