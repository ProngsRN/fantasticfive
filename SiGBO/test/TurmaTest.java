import org.junit.*;

import controllers.Cadastro;
import controllers.Banco;

import java.sql.SQLException;
import java.util.*;
import play.test.*;
import models.*;

public class TurmaTest extends UnitTest {

    @Test
    public void testAddTurma() throws SQLException {
    	
    	int ano = 1;
    	int nivel = 2;
    	String sala = "A";
    	
    	Cadastro.addTurma(ano, nivel, sala);
    	List<Turma> turmas = Turma.findAll();
    	boolean teste = false;
    	for (Turma t : turmas) {
    		if ( (t.getAno() == ano) ) {
    			teste = true;
    		}
    	}
    	assertTrue(teste);
    }

}
