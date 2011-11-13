package controllers;

import play.mvc.*;
import java.sql.*;

public class Banco extends Controller {

	 private Connection conexao = null;  
	  String url = "jdbc:postgresql://localhost/sigbo";  
	  String drive ="org.postgresql.Driver";  
	    
	  /* Conecta o Banco de Dados */   
	    public void conectar() {  
	        try{  
	            Class.forName(drive);  
	            conexao = DriverManager.getConnection(url, "usersigbo", "five");  
	        }catch(ClassNotFoundException e){  
	            System.out.println("Erro (Banco): "+ e);  
	        }catch(SQLException e){  
	            System.out.println("Conexão perdida: "+ e);  
	        }  
	    }  
	    /* Desconecta o Banco de Dados */  
	    public void desconectar() {  
	        try {  
	            conexao.close();  
	        }catch(SQLException e){  
	            System.out.println("Erro (Banco): "+ e);  
	        }  
	    }  
	    /* Executa um comando SQL */  
	    public int executar(String sql) {  
	        System.out.println("SQL: "+ sql);  
	          
	        int linhas = 0;  
	        try{  
	            linhas = conexao.createStatement().executeUpdate(sql);  
	        }catch(SQLException e){  
	            System.out.println("Erro (Banco): "+ e);  
	        }  
	        return linhas;  
	    }  
	    /* Executa uma consulta no Banco de Dados */  
	        public ResultSet consultar(String sql) {  
	        ResultSet rs = null;  
	        try{  
	            rs = conexao.createStatement().executeQuery(sql);
	        }catch(SQLException e){  
	            System.out.println("Erro (Banco): "+ e);  
	        }  
	        return rs;  
	    }  
}
