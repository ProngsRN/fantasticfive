package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.oval.constraint.MinSize;

import models.*;
import play.data.validation.Error;
import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.data.validation.Validation;
import play.data.validation.Validation.ValidationResult;
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

			String sql = ("SELECT * FROM turma WHERE ano = " + anoAtt
					+ " AND nivel = " + nivel + " AND sala = '" + sala + "'");
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

			String sql = ("SELECT * FROM disciplina WHERE nome = '" + nome
					+ "' AND turma = " + turma);
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

	public static void addNotaToAluno(@Required long idAluno,
			@Required long idDisciplina, @Required String bim,
			@Required String nota) throws SQLException {

		int notaValor = -1;
		if (!nota.isEmpty()) 
			notaValor = Integer.valueOf(nota);
		
		validation.range(notaValor, 0, 10);
		if (validation.hasErrors()) {
			flash
					.error("Um ou mais campos não foram preenchidos corretamente.");
		}
		else {
			String sql = ("UPDATE alunodisciplinas SET " + bim + " = " + notaValor
					+ " WHERE idaluno = " + idAluno + " AND iddisciplina = " + idDisciplina);
			Banco banco = new Banco();
			banco.conectar();
			ResultSet rs = banco.consultar(sql);
			if (rs != null) {
				flash.error("Não foi possível atualizar as notas do aluno.");
			}
			banco.desconectar();
		}
		admnotas();
	}

	public static void addProfessor(@Required String nome, String usuario,
			String senha, String senha2) throws SQLException {
		Banco banco = new Banco();
		banco.conectar();

		int tamSenha = senha.length();

		validation.range(tamSenha, 6, 14);
		validation.equals(senha2, senha);

		if (validation.hasErrors()) {
			for (Error error : validation.errors())
				flash.error(error.message());
		} else {

			String sql = ("SELECT * FROM professor WHERE nome = '" + nome + "'");
			ResultSet rs = banco.consultar(sql);
			sql = ("SELECT * FROM usuario WHERE usuario = '" + usuario + "'");
			ResultSet rs2 = banco.consultar(sql);

			if (!rs.next() && !rs2.next()) {
				Professor professor = new Professor(nome);
				professor.save();
				long id = professor.getId();
				Usuario novoUsuario = new Usuario(id, senha, usuario, 2);
				novoUsuario.save();
			} else {
				if (rs.next())
					flash.error("Professor já cadastrado.");
				else
					flash.error("Nome de usuário já existe.");
			}

		}
		banco.desconectar();
		admprofessores();
	}

	public static void addAluno(String nome, String usuario, String senha,
			String senha2) throws SQLException {

		Banco banco = new Banco();
		banco.conectar();

		int tamSenha = senha.length();

		validation.range(tamSenha, 6, 14);
		validation.equals(senha2, senha);

		if (validation.hasErrors()) {
			for (Error error : validation.errors())
				flash.error(error.message());
		} else {

			String sql = ("SELECT * FROM aluno WHERE nome = '" + nome + "'");
			ResultSet rs = banco.consultar(sql);
			sql = ("SELECT * FROM usuario WHERE usuario = '" + usuario + "'");
			ResultSet rs2 = banco.consultar(sql);

			if (!rs.next() && !rs2.next()) {
				Aluno aluno = new Aluno(nome);
				aluno.save();
				long id = aluno.getId();
				Usuario novoUsuario = new Usuario(id, senha, usuario, 1);
				novoUsuario.save();
			} else {
				if (rs.next())
					flash.error("Aluno já cadastrado.");
				else
					flash.error("Nome de usuário já existe.");
			}

		}
		banco.desconectar();
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

	public static void teste() {
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
	
	public static void criarAmizade (long idColega) {
		Long idAluno = Long.valueOf(session.get("user"));
		ColegaAluno amizade = new ColegaAluno(idAluno, idColega);
		amizade.save();
	}

}
