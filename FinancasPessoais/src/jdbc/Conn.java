package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {
	/*
	 * Classe de conexão com o Banco de dados MySQL, usando o driver com.mysql.jdbc.Driver
	 * 
	 */
	public static Connection getConexao() {
		try {
			Class.forName("org.sqlite.JDBC");
			System.out.println("Conectando banco de dados...");
			return DriverManager.getConnection("jdbc:sqlite:FinancasPessoais.db");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}