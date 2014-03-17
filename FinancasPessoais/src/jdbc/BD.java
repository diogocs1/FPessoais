package jdbc;

import java.sql.Connection;

public abstract class BD {
	protected final Connection conn = Conn.getConexao();
}
