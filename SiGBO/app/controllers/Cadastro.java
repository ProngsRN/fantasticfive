package controllers;

import java.sql.SQLException;

import models.*;
import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.mvc.*;

public class Cadastro extends Application {

	public static void addTurma(@Required int ano, @Required int nivel,
			@Required @MaxSize(1) String sala) throws SQLException {

		Banco banco = new Banco();
		banco.conectar();
		int anoAtt = ano;

		if (validation.hasErrors()) {
			flash
					.error("Um ou mais campos não foram preenchidos corretamente.");
		} else {
			Turma turma;
			if ((nivel == 2) && (ano > 3)) {
				anoAtt = 3;
			}
			turma = new Turma(anoAtt, nivel, sala);
			
			// String sql = ("INSERT into turma (ano, nivel, sala) VALUES ('" +
			// anoAtt + "', '" + nivel + "', '" + sala + "');");
			// banco.executar(sql);
			
			turma.save();
		}

		admturmas();
	}

	public static void addDisciplina(@Required String nome,
			@Required long turma) throws SQLException {
		Banco banco = new Banco();
		banco.conectar();
		if (validation.hasErrors()) {
			flash
					.error("Um ou mais campos não foram preenchidos corretamente.");
		} else {
			Disciplina disciplina = new Disciplina (nome, turma);
			disciplina.save();
			
			// String sql = ("INSERT into disciplina (nome, turma)" +
			// " VALUES ('" + nome + "', '" + res + "');");

		}
		admdisciplinas();
	}

	public static void addProfessor(@Required String nome, @Required long disciplina) {
		Banco banco = new Banco();
		banco.conectar();

		if (validation.hasErrors()) {
			flash
					.error("Um ou mais campos não foram preenchidos corretamente.");
		} else {
			
					Professor professor = new Professor(disciplina, nome);
					professor.save();
		}
			
			// String sql =
			// ("INSERT into professor (disciplina, nome) VALUES ('" +
			// disciplina + "', '" + nome + "');");
			// banco.executar(sql);
		

		admprofessores();
	}

	public static void addAluno(String nome, long disciplina)
			throws SQLException {
		Banco banco = new Banco();
		banco.conectar();

		if (validation.hasErrors()) {
			flash
					.error("Um ou mais campos não foram preenchidos corretamente.");
		} else {
			
			Aluno aluno = new Aluno (nome, disciplina);
			aluno.save();
			// String sql =
			// ("INSERT into aluno (disciplina, nome, turma) VALUES ('" +
			// disciplina + "', '" + "', '" + nome + "');");
			// banco.executar(sql);
		}

		admalunos();
	}

}
