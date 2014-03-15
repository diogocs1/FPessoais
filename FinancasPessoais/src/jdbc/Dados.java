package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import model.Usuario;

public class Dados {
	/*
	 * Classe de consulta e gravação no banco de dados
	 * Possui métodos que executam comandos SQL
	 * 		conn --> Atributo do tipo Connection (java.sql.Connection) que recebe a conexão gerada pela 
	 * 				 classe conexão e obtida pelo método getConexao()
	 */
	private Connection conn;
	
	public Dados () throws SQLException {
		//Obtém a conexão e salva no atributo sempre que a classe é instanciada
		conn = Conn.getConexao();
	}

	public void criarUsuario (Usuario user) throws SQLException{
		/*
		 * Executa query sobre a conexão para gravar os atributos de usuário
		 * no banco de dados
		 */
		String sql = "insert into usuario (nome, senha, nascimento)" + 
					"values (?,?,?)";
		// Cria um PreparedStatement (Preparação de tipo para comandos SQL)
		PreparedStatement st = conn.prepareStatement(sql);
		// Define os valores das interrogações
		st.setString(1, user.getNome());
		st.setString(2, user.getSenha());
		st.setString(3, user.getNascimento());
		// Executa e fecha o statement
		st.execute();
		st.close();
	}
	public Usuario getUsuario (String nome) throws SQLException {
		/*
		 * Obtém os dados do usuário e retorna como Objeto Usuario
		 */
		// Cria um usuário "vazio" para retorno caso não existam correspondẽncias a busca no banco de dados
		Usuario user = new Usuario("-","-","-");
		if (nome.length() > 0){
			String sql = "select * from usuario where nome = (?)";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, nome);
			// Cria um objeto ResultSet (Conjunto de resultados) para a leitura
			ResultSet rs = st.executeQuery();
			// Move o cursor até o próximo (ou primeiro) objeto e verifica se existe próximo
			if (rs.next()){
				// Obtém os dados do conjunto de Resultados e cria um objeto Usuario
				user = new Usuario(rs.getString("nome"), 
									rs.getString("senha"),
									rs.getString("nascimento"));
			}
			rs.close();
			st.close();
		}
		return user;
	}
	public void redefinirSenha (String nome, String novaSenha) throws SQLException {
		// Atualiza a tabela com a nova senha
		String sql = "update usuario set senha = (?) where nome = (?)";
		PreparedStatement st = conn.prepareStatement(sql);
		st.setString(1, novaSenha);
		st.setString(2, nome);
		st.execute();
		st.close();
	}
	public ArrayList<String> getBancos () throws SQLException {
		String sql = "select * from bancos";
		PreparedStatement st = conn.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		
		ArrayList<String> bancos = new ArrayList<String>();
		while (rs.next()){
			bancos.add(rs.getString("idbanco")+ " - "+ rs.getString("nome"));
		}
		rs.close();
		st.close();
		return bancos;
	}
}
