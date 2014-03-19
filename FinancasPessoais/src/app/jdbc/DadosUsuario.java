package app.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import app.model.Usuario;

public class DadosUsuario extends BD{
	public void criarUsuario (Usuario user) throws SQLException{
		/**
		 * Executa query sobre a conexão para gravar os atributos de usuário
		 * no banco de dados
		 */
		String sql = "INSERT INTO usuario (nome, senha, nascimento)" + 
					"VALUES (?,?,?)";
		// Cria um PreparedStatement (Preparação de tipo para comandos SQL)
		PreparedStatement st = conn.prepareStatement(sql);
		// Define os valores das interrogações
		st.setString(1, user.getNome());
		st.setString(2, user.getSenha());
		st.setString(3, user.getNascimento());
		// Executa e fecha o statement
		st.execute();
		st.close();
		conn.close();
	}
}
