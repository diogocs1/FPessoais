package app.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import app.model.Despesa;

public class DadosDespesa extends BD {
	public void cadastraDespesa (Despesa despesa) throws SQLException{
		PreparedStatement stmt = conn.prepareStatement("INSERT INTO gastos (descricao,vencimento,prioridade,valor,status,usuario_idusuario) VALUES (?,?,?,?,?,?)");
		stmt.setString(1, despesa.getDescricao());
		stmt.setString(2, despesa.getVencimento());
		stmt.setString(3, despesa.getPrioridade());
		stmt.setDouble(4, despesa.getValor());
		stmt.setString(5, despesa.getStatus());
		stmt.setInt(6, despesa.getPessoa().getId());
		
		stmt.execute();
		stmt.close();
	}
}
