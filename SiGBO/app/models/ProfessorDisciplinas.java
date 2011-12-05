package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;

import controllers.Banco;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Entity
public class ProfessorDisciplinas extends Model {

	private long idProfessor;
	private long idDisciplina;

	public ProfessorDisciplinas(long idProfessor, long idDisciplina) {
		this.setIdProfessor(idProfessor);
		this.setIdDisciplina(idDisciplina);
	}

	public void setIdDisciplina(long idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

	public long getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdProfessor(long idProfessor) {
		this.idProfessor = idProfessor;
	}

	public long getIdProfessor() {
		return idProfessor;
	}
	
	public String getNomeDisciplina() throws SQLException {

		Banco banco = new Banco();
		banco.conectar();
		String sql = ("SELECT nome FROM disciplina WHERE id = " + idDisciplina);
		ResultSet rs = banco.consultar(sql);
		String nome = "";
		if (rs.next()) {
			nome = rs.getString("nome");
		}
		banco.desconectar();
		return nome;
	}
	
	public int getNumeroDisciplinas() throws SQLException {
		
		Banco banco = new Banco();
		banco.conectar();
		String sql = ("SELECT all FROM professordisciplina WHERE idprofessor = " + idProfessor);
		int linhas = 1;
		ResultSet rs = banco.consultar(sql);
		while(rs.next()) {
			linhas++;
		}
		return linhas;
	}
	
	public String getTurmaDisciplina() throws SQLException {
		
		Banco banco = new Banco();
		banco.conectar();
		String sql = ("SELECT turma FROM disciplina WHERE id = " + idDisciplina);
		ResultSet rs = banco.consultar(sql);
		String turma = "";
		long id = 0;
		if (rs.next()) {
			id = rs.getInt("turma");
		}
		sql = ("SELECT ano,nivel,sala FROM turma WHERE id = " + id);
		rs = banco.consultar(sql);
		String ano, nivel, sala;
		if (rs.next()) {
			ano = rs.getString("ano");
			nivel = rs.getString("nivel");
			sala = rs.getString("sala");
			String nivelString;
			if (nivel == "2") nivelString = "Ensino Médio";
			else nivelString = "Ensino Fundamental";
			turma = (ano + "º Ano \"" + sala + "\" " + nivelString);
		}
		banco.desconectar();
		return turma;
	}
	
}
