package models;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import play.test.UnitTest;

public class AlunoDisciplinasTest extends UnitTest {

	@Test
	public void testIsTurmaAtual() throws SQLException {
		
		String NomeDisciplina = "Matematica";
		long turma = 1;
		
		Disciplina disciplina = new Disciplina(NomeDisciplina, turma);
    	disciplina.save();
    	
    	Aluno aluno = new Aluno("Duarte");
    	aluno.setTurmaAtual(disciplina.getTurma());
    	aluno.save();
    	
    	AlunoDisciplinas alunodisciplinas = new AlunoDisciplinas(disciplina.getId(), aluno.getId());
    	alunodisciplinas.save();
    	
    	boolean res = alunodisciplinas.isTurmaAtual();
    	assertFalse(res);
	}

}
