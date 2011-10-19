package controllers;

import play.*;

import play.mvc.*;
import play.data.validation.*;
import play.db.jpa.JPA;

import java.util.*;

import models.*;
import play.mvc.*;

import java.util.*;

import javax.persistence.EntityManager;

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
		render();
	}

	public static void admboletins() {
		render();
	}

	public static void admnotas() {
		render();
	}

	public static void admprofessores() {
		render();
	}

	public static void admdisciplinas() {
		List<Disciplina> disciplinas = Disciplina.findAll();
		List<Turma> turmas = Turma.findAll();
		render(turmas, disciplinas);
	}

	public static void addTurma(
			@Required int ano, 
			@Required int nivel, 
			@Required @MaxSize(1) String sala) {
		if (validation.hasErrors()) {
			flash.error("Um ou mais campos não foram preenchidos corretamente.");
		} else {
			Turma turma;
			if ((nivel == 2) && (ano > 3)) {
				turma = new Turma(3, nivel, sala);
			} else
				turma = new Turma(ano, nivel, sala);
	
			EntityManager em = JPA.em();
			em.persist(turma);
		}

		admturmas();
	}

	public static void addDisciplina(
			@Required String nome,
			@Required Turma turma) {
		if (validation.hasErrors()) {
			flash.error("Um ou mais campos não foram preenchidos corretamente.");
		} else {
			Disciplina disciplina = new Disciplina(nome, turma);
			EntityManager em = JPA.em();
			em.persist(disciplina);	
		}
		admdisciplinas();
	}

	public static void admturmas() {
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
