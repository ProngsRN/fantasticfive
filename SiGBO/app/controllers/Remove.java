package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

import models.*;
import play.data.validation.Required;
import play.mvc.*;

public class Remove extends Application {
	
	public static void removeAluno (@Required long idAluno) {
		
		if (validation.hasErrors()) {
			flash
					.error("Um ou mais campos não foram preenchidos corretamente.");
		} else {
			Banco banco = new Banco();
			banco.conectar();
			String sql = ("DELETE FROM aluno WHERE id = " + idAluno);
			banco.executar(sql);
			sql = ("DELETE FROM alunodisciplinas WHERE idaluno = " + idAluno);
			banco.executar(sql);
			banco.desconectar();
		}
		gerenciaraluno();
	}
	
	public static void removeProfessor (@Required long idProfessor) {
		
		if (validation.hasErrors()) {
			flash
					.error("Um ou mais campos não foram preenchidos corretamente.");
		} else {
			Banco banco = new Banco();
			banco.conectar();
			String sql = ("DELETE FROM professor WHERE id = " + idProfessor);
			banco.executar(sql);
			sql = ("DELETE FROM professordisciplinas WHERE idProfessor = " + idProfessor);
			banco.executar(sql);
			banco.desconectar();
		}
		gerenciarprofessor();
	}
	
	public static void removeDisciplina (@Required long idDisciplina) {
		
		if (validation.hasErrors()) {
			flash
					.error("Um ou mais campos não foram preenchidos corretamente.");
		} else {
			Banco banco = new Banco();
			banco.conectar();
			String sql = ("DELETE FROM alunodisciplinas WHERE iddisciplina = " + idDisciplina);
			banco.executar(sql);
			sql = ("DELETE FROM professordisciplinas WHERE iddisciplina = " + idDisciplina);
			banco.executar(sql);
			sql = ("DELETE FROM disciplina WHERE id = " + idDisciplina);
			banco.executar(sql);
			banco.desconectar();
		}
		gerenciardisciplina();
	}
	
	public static void removeTurma (@Required long idTurma) throws SQLException {
		
		if (validation.hasErrors()) {
			flash
					.error("Um ou mais campos não foram preenchidos corretamente.");
		} else {
			Banco banco = new Banco();
			banco.conectar();
			String sql = ("DELETE FROM turma WHERE id = " + idTurma);
			banco.executar(sql);
			sql = ("SELECT id FROM disciplina WHERE turma = " + idTurma);
			ResultSet rs = banco.consultar(sql);
			long idDisciplina = 0;
			if (rs.next()) {
				idDisciplina = rs.getLong("id");
			}
			sql = ("DELETE FROM disciplina WHERE turma = " + idTurma);
			banco.executar(sql);
			sql = ("DELETE FROM professordisciplinas WHERE iddisciplina = " + idDisciplina);
			banco.executar(sql);
			sql = ("DELETE FROM alunodisciplinas WHERE iddisciplina = " + idDisciplina);
			banco.executar(sql);
			banco.desconectar();
		}
		gerenciarturma();
	}

}
