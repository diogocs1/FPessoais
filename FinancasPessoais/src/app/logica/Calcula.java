package app.logica;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.control.Dialogs;
import app.jdbc.DadosConta;
import app.jdbc.DadosDespesa;
import app.model.Conta;
import app.model.Despesa;

public class Calcula {
	private static double saldoTotal = 0;
	private static double debitoTotal = 0;
	
	public static String somaSaldo (){
		saldoTotal = 0;
		try {
			ArrayList<Conta> contas = new DadosConta().getContas();
			for (Conta conta: contas){
				saldoTotal += conta.getSaldo();
			}
		} catch (SQLException e) {
			Dialogs.showErrorDialog(null, "Problema no Banco de dados! \n"+
											"Não é possível calcular o saldo total"	+ "\n " + "Erro: "+ e.getSQLState());
		}
		return "R$ "+saldoTotal;
	}
	public static String somaDespesas (){
		debitoTotal = 0;
		try{
			ArrayList<Despesa> despesas = new DadosDespesa().getDespesas();
			for (Despesa despesa: despesas){
				debitoTotal += despesa.getValor();
			}
		}catch (SQLException e){
			Dialogs.showErrorDialog(null, "Problema no Banco de dados! \n"+
					"Não é possível calcular o débito total"	+ "\n " + "Erro: "+ e.getSQLState());		
		}
		return "R$ "+debitoTotal;
	}
	public static String saldoPrevisto (){
		double saldoPrevisto = saldoTotal - debitoTotal;
		return "R$ "+saldoPrevisto;
	}
}