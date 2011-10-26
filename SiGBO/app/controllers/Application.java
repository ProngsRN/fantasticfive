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
		List<Turma> turmas = Turma.findAll();
		List<Disciplina> disciplinas = Disciplina.findAll();
		render(alunos, turmas, disciplinas);
	}

	public static void admboletins() {
		render();
	}

	public static void admnotas() {
		render();
	}

	public static void habilitaracessoaluno() {
		List<Aluno> alunos = Aluno.findAll();
		List<UsuarioAluno> usuarios = UsuarioAluno.findAll();
		render(alunos,usuarios);
	}

	public static void admprofessores() {
		List<Disciplina> disciplinas = Disciplina.findAll();
		List<Professor> professores = Professor.findAll();
		render(professores, disciplinas);
	}

	public static void admdisciplinas() {
		List<Disciplina> disciplinas = Disciplina.findAll();
		List<Turma> turmas = Turma.findAll();
		render(turmas, disciplinas);
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
		Banco banco = new Banco();
		banco.conectar();
		List<Turma> lista = Turma.findAll();
		render(lista);
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
