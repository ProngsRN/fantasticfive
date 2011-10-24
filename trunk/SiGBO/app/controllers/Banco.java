package controllers;

import play.mvc.*;
import java.sql.*;

public class Banco extends Controller {

	 private Connection conexao = null;  
	  String url = "jdbc:postgresql://localhost/sigbo";  
	  String drive ="org.postgresql.Driver";  
	    
	  /** Efetua a conexão**/   
	    public void conectar() {  
	        try{  
	            Class.forName(drive);  
	            conexao = DriverManager.getConnection(url, "usersigbo", "five");  
	        }catch(ClassNotFoundException e){  
	            System.out.println("Erro (Banco): "+ e);  
	        }catch(SQLException e){  
	            System.out.println("Não foi possivel conectar: "+ e);  
	        }  
	    }  
	    /** Desconecta o banco de dados**/  
	    public void desconectar() {  
	        try {  
	            conexao.close();  
	        }catch(SQLException e){  
	            System.out.println("Erro (Banco): "+ e);  
	        }  
	    }  
	    /** Executa uma opção do SQL**/  
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
	    /**Efetua uma consulta no banco de dados**/  
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
