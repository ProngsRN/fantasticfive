package models;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.Entity;

import controllers.Banco;

import net.sf.oval.constraint.MinSize;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Disciplina extends Model {

	@Required
	private String nome;

	@Required
	private long turma;

	public Disciplina(String nome, long turma) {
		this.setNome(nome);
		this.setTurma(turma);
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setTurma(long turma) {
		this.turma = turma;
	}

	public long getTurma() {
		return turma;
	}

	public String getTurmaDisciplina() throws SQLException {
		Banco banco = new Banco();
		banco.conectar();
		String sql = ("SELECT ano,nivel,sala FROM turma WHERE id = " + turma);
		ResultSet rs = banco.consultar(sql);
		String turma = "";
		String ano, nivel, sala;
		if (rs.next()) {
			ano = rs.getString("ano");
			nivel = rs.getString("nivel");
			sala = rs.getString("sala");
			String nivelString;
			if (nivel == "2") nivelString = "Ensino Médio";
			else nivelString = "Ensino Fundamental";
			turma = ("Ano: " + ano + " Nivel: " + nivelString + " Sala: " + sala);
		}
		banco.desconectar();
		return turma;
	}

}
