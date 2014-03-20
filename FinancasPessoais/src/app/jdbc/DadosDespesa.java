package app.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	public ArrayList<Despesa> getDespesas () throws SQLException{
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM gastos,usuario WHERE usuario_idusuario = idusuario");
		ResultSet rs = stmt.executeQuery();
		
		ArrayList<Despesa> despesas = new ArrayList<Despesa>();
		while (rs.next()){
			Despesa despesa = new Despesa(
					rs.getInt("idgastos"),
					new DadosLogin().getUsuario(rs.getString("nome")),
					rs.getString("descricao"),
					rs.getString("vencimento"),
					rs.getString("prioridade"),
					rs.getString("status"),
					rs.getDouble("valor")
					);
			despesas.add(despesa);
		}
		stmt.close();
		rs.close();
		return despesas;
	}

	public void removeDespesa(Despesa despesa) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("DELETE FROM gastos WHERE idgastos = ?");
		stmt.setInt(1, despesa.getId());
		
		stmt.execute();
		stmt.close();
	}

	public void editaDespesa(Despesa editaDesp, Despesa novaDespesa) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("UPDATE gastos SET descricao = ?, vencimento = ?, prioridade = ?, valor = ?, status = ? WHERE idgastos = ?");
		stmt.setString(1, novaDespesa.getDescricao());
		stmt.setString(2, novaDespesa.getVencimento());
		stmt.setString(3, novaDespesa.getPrioridade());
		stmt.setDouble(4, novaDespesa.getValor());
		stmt.setString(5, novaDespesa.getStatus());
		stmt.setInt(6, editaDesp.getId());
		
		stmt.execute();
		stmt.close();
	}
}
