package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;

import controllers.Banco;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Entity
public class AlunoDisciplinas extends Model {
    
	private int nota;
	
	private long idDisciplina;
	
	private long idAluno;
	
	public AlunoDisciplinas(long idDisciplina, long idAluno) {
		this.idDisciplina = idDisciplina;
		this.idAluno = idAluno;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public int getNota() {
		return nota;
	}

	public void setIdDisciplina(long idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

	public long getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdAluno(long idAluno) {
		this.idAluno = idAluno;
	}

	public long getIdAluno() {
		return idAluno;
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
			turma = ("Ano: " + ano + " Nível: " + nivelString + " Sala: " + sala);
		}
		banco.desconectar();
		return turma;
	}
}
