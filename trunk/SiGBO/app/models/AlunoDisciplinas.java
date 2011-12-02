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

	private long idDisciplina;

	private long idAluno;

	private float nota1, nota2, nota3, nota4;

	public AlunoDisciplinas(long idDisciplina, long idAluno) {
		this.idDisciplina = idDisciplina;
		this.idAluno = idAluno;
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

	public String getNomeAluno() throws SQLException {

		Banco banco = new Banco();
		banco.conectar();
		String sql = ("SELECT nome FROM aluno WHERE id = " + idAluno);
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
			id = rs.getLong("turma");
		}
		sql = ("SELECT ano,nivel,sala FROM turma WHERE id = " + id);
		rs = banco.consultar(sql);
		String ano, nivel, sala;
		if (rs.next()) {
			ano = rs.getString("ano");
			nivel = rs.getString("nivel");
			sala = rs.getString("sala");
			String nivelString;
			if (nivel == "2")
				nivelString = "Ensino MÃ©dio";
			else
				nivelString = "Ensino Fundamental";
			turma = ("Ano: " + ano + " NÃ­vel: " + nivelString + " Sala: " + sala);
		}
		banco.desconectar();
		return turma;
	}

	public float getNota1() {
		return nota1;
	}

	public void setNota1(float nota1) {
		this.nota1 = nota1;
	}

	public float getNota2() {
		return nota2;
	}

	public void setNota2(float nota2) {
		this.nota2 = nota2;
	}

	public float getNota3() {
		return nota3;
	}

	public void setNota3(float nota3) {
		this.nota3 = nota3;
	}

	public float getNota4() {
		return nota4;
	}

	public void setNota4(float nota4) {
		this.nota4 = nota4;
	}
}