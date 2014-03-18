package app.logica;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.control.Dialogs;
import app.jdbc.DadosConta;
import app.model.Conta;

public class Calcula {
	public static String somaSaldo (){
		double saldoTotal = 0;
		try {
			ArrayList<Conta> contas = new DadosConta().getContas();
			for (Conta conta: contas){
				saldoTotal += conta.getSaldo();
			}
		} catch (SQLException e) {
			Dialogs.showErrorDialog(null, "Problema no Banco de dados! \n \n" + "Erro: "+ e.getSQLState());
		}
		return "R$ "+saldoTotal;
	}

}
