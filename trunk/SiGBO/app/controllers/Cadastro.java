package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

import models.*;
import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.mvc.*;
import play.mvc.results.RenderText;

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
			if ((nivel == 2) && (ano > 3)) {
				anoAtt = 3;
			}
			
			String sql = ("SELECT * FROM turma WHERE ano = "
					+ anoAtt + " AND nivel = " + nivel + " AND sala = '" + sala + "'");
			ResultSet rs = banco.consultar(sql);
			
			if (!rs.next()) {
				Turma turma;
				turma = new Turma(anoAtt, nivel, sala);
				turma.save();
			} else
				flash.error("Turma já cadastrada.");
		}
		banco.desconectar();
		admturmas();
	}

	public static void addDisciplina(@Required String nome, @Required long turma)
			throws SQLException {
		Banco banco = new Banco();
		banco.conectar();
		if (validation.hasErrors()) {
			flash
					.error("Um ou mais campos não foram preenchidos corretamente.");
		} else {
			
			String sql = ("SELECT * FROM disciplina WHERE nome = '"
					+ nome + "' AND turma = " + turma);
			ResultSet rs = banco.consultar(sql);
			
			if (!rs.next()) {
				Disciplina disciplina = new Disciplina(nome, turma);
				disciplina.save();
			} else
				flash.error("Professor já cadastrado.");
		}
		banco.desconectar();
		admdisciplinas();
	}

	public static void addProfessor(@Required String nome) throws SQLException {
		Banco banco = new Banco();
		banco.conectar();

		if (validation.hasErrors()) {
			flash
					.error("Um ou mais campos não foram preenchidos corretamente.");
		} else {
			
			String sql = ("SELECT * FROM professor WHERE nome = '"
					+ nome + "'");
			ResultSet rs = banco.consultar(sql);
			
			if (!rs.next()) {
				Professor professor = new Professor(nome);
				professor.save();
			} else
				flash.error("Professor já cadastrado.");

		}
		banco.desconectar();
		admprofessores();
	}

	public static void addAluno(String nome) throws SQLException {
		Banco banco = new Banco();
		banco.conectar();

		if (validation.hasErrors()) {
			flash
					.error("Um ou mais campos não foram preenchidos corretamente.");
		} else {


			String sql = ("SELECT * FROM aluno WHERE nome = '"
					+ nome + "'");
			ResultSet rs = banco.consultar(sql);
			
			if (!rs.next()) {
				Aluno aluno = new Aluno(nome);
				aluno.save();
			} else
				flash.error("Aluno já cadastrado.");
		}

		admalunos();
	}

	public static void addDisciplinaToProfessor(@Required long idProfessor,
			@Required long idDisciplina) throws SQLException {

		if (validation.hasErrors()) {
			flash
					.error("Um ou mais campos não foram preenchidos corretamente.");
		} else {
			Banco banco = new Banco();
			banco.conectar();
			String sql = ("SELECT idprofessor, iddisciplina FROM professordisciplinas WHERE idprofessor = "
					+ idProfessor + " AND iddisciplina = " + idDisciplina);
			ResultSet rs = banco.consultar(sql);
			if (!rs.next()) {
				ProfessorDisciplinas professordisciplina = new ProfessorDisciplinas(
						idProfessor, idDisciplina);
				professordisciplina.save();
			} else
				flash.error("Professor já possui essa disciplina.");
			banco.desconectar();
		}
		admprofessordisciplina();
	}
	
	public static void teste () {
		renderText("teste");
	}

	public static void addDisciplinaToAluno(@Required long idAluno,
			@Required long idDisciplina) throws SQLException {

		if (validation.hasErrors()) {
			flash
					.error("Um ou mais campos não foram preenchidos corretamente.");
		} else {
			Banco banco = new Banco();
			banco.conectar();
			String sql = ("SELECT idaluno, iddisciplina FROM alunodisciplinas WHERE idaluno = "
					+ idAluno + " AND iddisciplina = " + idDisciplina);
			ResultSet rs = banco.consultar(sql);
			if (!rs.next()) {
				AlunoDisciplinas alunodisciplina = new AlunoDisciplinas(
						idDisciplina, idAluno);
				alunodisciplina.save();
			} else
				flash.error("Aluno já possui essa disciplina.");
			banco.desconectar();
		}
		admalunodisciplina();
	}

}
