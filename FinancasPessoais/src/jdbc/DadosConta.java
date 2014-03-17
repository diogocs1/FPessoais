package jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Conta;

public class DadosConta extends BD{
	public ArrayList<String> getBancos () throws SQLException {
		String sql = "SELECT * FROM bancos";
		PreparedStatement st = conn.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		
		ArrayList<String> bancos = new ArrayList<String>();
		while (rs.next()){
			bancos.add(rs.getString("idbanco")+ "-"+ rs.getString("nome"));
		}
		rs.close();
		st.close();
		return bancos;
	}
	public void criaConta (Conta novaConta) throws SQLException {
		String sql = "INSERT INTO conta (banco,num,tipo,saldo,usuario_idusuario) VALUES (?,?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, novaConta.getBanco());
		stmt.setString(2, novaConta.getConta());
		stmt.setString(3, novaConta.getTipo());
		stmt.setDouble(4, novaConta.getSaldo());
		stmt.setInt(5, novaConta.getPessoa().getId());
		
		stmt.execute();
		stmt.close();
	}
	public ArrayList<Conta> getContas () throws SQLException {
		String sql = "SELECT * FROM conta, usuario WHERE usuario_idusuario = idusuario;";
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		ArrayList<Conta> contas = new ArrayList<Conta>();
		ResultSet rs = stmt.executeQuery();
		while (rs.next()){
			Conta conta = new Conta(rs.getInt("idconta"),
									new DadosLogin().getUsuario(rs.getString("nome")),
									rs.getString("banco"),
									rs.getString("num"),
									rs.getString("tipo"),
									rs.getDouble("saldo")
									);
			contas.add(conta);
		}
		return contas;
	}
	public void editaConta (Conta antigaConta, Conta novaConta) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("UPDATE conta SET banco = ?, num = ?, tipo = ?, saldo = ? WHERE idconta = ?");
		stmt.setString(1, novaConta.getBanco());
		stmt.setString(2, novaConta.getConta());
		stmt.setString(3, novaConta.getTipo());
		stmt.setDouble(4, novaConta.getSaldo());
		stmt.setInt(5, antigaConta.getId());
		
		stmt.execute();
		stmt.close();
	}
	public void removeConta (Conta conta) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("DELETE FROM conta WHERE idconta = ?");
		stmt.setInt(1, conta.getId());
		stmt.execute();
		stmt.close();
	}

}
