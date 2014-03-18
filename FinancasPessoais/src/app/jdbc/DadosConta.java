package app.jdbc;

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
		novaConta = getIdConta(novaConta);
		addHistorico(novaConta.getHistorico().get(0), novaConta);
		
		stmt.close();
	}
	public Conta getIdConta (Conta novaConta) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("SELECT idconta FROM conta WHERE banco = ? AND num = ? AND tipo = ?");
		stmt.setString(1, novaConta.getBanco());
		stmt.setString(2, novaConta.getConta());
		stmt.setString(3, novaConta.getTipo());
		
		ResultSet rs = stmt.executeQuery();
		if (rs.next()){
			novaConta.setId(rs.getInt("idconta"));
		}
		System.out.println(novaConta.getId());
		stmt.close();
		return novaConta;
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
			conta.setHistorico(getHistorico(conta));
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
				novaConta.getHistorico().get(posHist),
				novaConta
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
	public void addHistorico (Acao acao, Conta conta) throws SQLException{
		PreparedStatement stmt = conn.prepareStatement("INSERT INTO historico (data,descricao,conta_idconta) VALUES (?,?,?)");
		stmt.setString(1,acao.getData());
		stmt.setString(2, acao.getDescricao());
		stmt.setInt(3, conta.getId());
		stmt.execute();
		stmt.close();
	}
	public ArrayList<Acao> getHistorico (Conta conta) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM historico WHERE conta_idconta = ?");
		stmt.setInt(1, conta.getId());
		ResultSet rs = stmt.executeQuery();
		
		ArrayList<Acao> acoes = new ArrayList<Acao>();
		
		while (rs.next()){
			Acao acao = new Acao(
								rs.getInt("idhistorico"),
								rs.getString("data"),
								rs.getString("descricao")
								);
			acoes.add(acao);
		}
		return acoes;
	}
}
