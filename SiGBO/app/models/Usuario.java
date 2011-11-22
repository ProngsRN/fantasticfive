package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Usuario extends Model {
    
	//1 = Aluno, 2 = Professor.
	private int tipo;
	
	private long idUsuarioRef;
    
    private String usuario;
    
    private String senha;
    
	public Usuario (long idUsuarioRef, String senha, String usuario, int tipo) {
		this.setTipo(tipo);
		this.idUsuarioRef = idUsuarioRef;
		this.senha = senha;
		this.usuario = usuario;
	}

	public void setIdUsuarioRef(long tipo) {
		this.idUsuarioRef = tipo;
	}

	public long getIdUsuarioRef() {
		return idUsuarioRef;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSenha() {
		return senha;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getTipo() {
		return tipo;
	}
}
