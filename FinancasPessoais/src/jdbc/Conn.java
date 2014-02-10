package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {
	/*
	 * Classe de conexão com o Banco de dados MySQL, usando o driver com.mysql.jdbc.Driver
	 * 
	 */
	public static Connection getConexao() throws SQLException {
		try {
			//Retrona a conexão para uso posterior em outros métodos
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Conectando banco de dados...");
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/FinancasPessoais", "fp", "fp");
		} catch (ClassNotFoundException e) {
			throw new SQLException(e.getMessage());
		}
	}
}
