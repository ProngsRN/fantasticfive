package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;

import java.text.SimpleDateFormat;
import java.util.*;

@Entity
public class Recado extends Model {
    
	private long idRemetente;
	private long idDestinatario;
	private Date data;
	private boolean privado;
    
    private String texto;

	public Recado(long idAluno, String texto, long idDestinatario, boolean privado) {
		this.idRemetente = idAluno;
		this.texto = texto;
		this.setIdDestinatario(idDestinatario);
		this.setData(new Date(System.currentTimeMillis()));
		this.setPrivado(privado);
	}

	public void setIdRemetente(long idRemetente) {
		this.idRemetente = idRemetente;
	}

	public long getIdRemetente() {
		return idRemetente;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getTexto() {
		return texto;
	}

	public void setIdDestinatario(long idDestinatario) {
		this.idDestinatario = idDestinatario;
	}

	public long getIdDestinatario() {
		return idDestinatario;
	}
	
	public Aluno getRemetente() {
		return Aluno.findById(idRemetente);
	}
	
	public Aluno getDestinatario() {
		return Aluno.findById(idRemetente);
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getData() {
		return data;
	}
	
	public String getDataFormatada() {
		SimpleDateFormat formatador = new SimpleDateFormat("hh:mm - dd/MM/yyyy");
		if (data != null) {
			return formatador.format(data);
		}
		else return "";
	}

	public void setPrivado(boolean privado) {
		this.privado = privado;
	}

	public boolean isPrivado() {
		return privado;
	}
}
