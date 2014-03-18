package app.jdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import app.model.Conta;
import app.model.Acao;

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
		// Adiciona a ação ao histórico
		addHistorico(novaConta.getHistorico().get(0));
		
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
		// Adiciona a última ação ao histórico
		int posHist = novaConta.getHistorico().size() - 1;
		addHistorico(
				novaConta.getHistorico().get(posHist)
				);
		stmt.close();
	}
	public void removeConta (Conta conta) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("DELETE FROM conta WHERE idconta = ?");
		PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM historico WHERE conta_idconta = ?");
		
		stmt.setInt(1, conta.getId());
		stmt2.setInt(1, conta.getId());
		
		stmt.execute();
		stmt2.execute();
		stmt.close();
		stmt2.close();
	}
	public void addHistorico (Acao acao) throws SQLException{
		PreparedStatement stmt = conn.prepareStatement("INSERT INTO historico (data,descricao) VALUES (?,?)");
		stmt.setDate(1, (Date) acao.getData());
		stmt.setString(2, acao.getDescricao());
		stmt.execute();
		stmt.close();
	}
}
