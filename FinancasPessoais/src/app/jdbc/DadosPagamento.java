package app.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import app.model.Pagamento;

public class DadosPagamento extends BD{
	public void criaPagamento (Pagamento pagamento) throws SQLException{
		PreparedStatement stmt = conn.prepareStatement("INSERT INTO pagamento (valor,data,gastos_idgastos, conta_idconta) VALUES (?,?,?,?)");
		PreparedStatement stmt2 = conn.prepareStatement("UPDATE gastos SET status = 'Pago' WHERE idgastos = ?");
		
		stmt.setDouble(1, pagamento.getValor());
		stmt.setString(2, pagamento.getData());
		stmt.setInt(3, pagamento.getDespesa().getId());
		if (pagamento.getConta() != null){
			stmt.setInt(4, pagamento.getConta().getId());
		}else{
			stmt.setInt(4, 0);
		}
		stmt2.setInt(1, pagamento.getDespesa().getId());
		
		stmt2.execute();
		stmt2.close();
		stmt.execute();
		stmt.close();
	}
}
