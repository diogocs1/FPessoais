package jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Usuario;

public class DadosLogin extends BD{
	public void redefinirSenha (String nome, String novaSenha) throws SQLException {
		// Atualiza a tabela com a nova senha
		String sql = "UPDATE usuario SET senha = (?) WHERE nome = (?)";
		PreparedStatement st = conn.prepareStatement(sql);
		st.setString(1, novaSenha);
		st.setString(2, nome);
		st.execute();
		st.close();
	}
	public Usuario getUsuario (String nome) throws SQLException {
		/**
		 * Obtém os dados do usuário e retorna como Objeto Usuario
		 */
		// Cria um usuário "vazio" para retorno caso não existam correspondẽncias a busca no banco de dados
		Usuario user = new Usuario("-","-","-");
		if (nome.length() > 0){
			String sql = "SELECT * FROM usuario WHERE nome = (?)";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, nome);
			// Cria um objeto ResultSet (Conjunto de resultados) para a leitura
			ResultSet rs = st.executeQuery();
			// Move o cursor até o próximo (ou primeiro) objeto e verifica se existe próximo
			if (rs.next()){
				// Obtém os dados do conjunto de Resultados e cria um objeto Usuario
				user = new Usuario(rs.getInt("idusuario"),
									rs.getString("nome"), 
									rs.getString("senha"),
									rs.getString("nascimento"));
			}
			rs.close();
			st.close();
		}
		return user;
	}
}
