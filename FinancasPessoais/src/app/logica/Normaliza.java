package app.logica;

import javafx.scene.control.Dialogs;

public class Normaliza {
	public static Double normalizaValor (String valor){
		String dvalor = valor.replace(',', '.');
		try {
			return Double.parseDouble(dvalor);
		}catch (NumberFormatException e){
			Dialogs.showErrorDialog(null, "Digite apenas números (com vírgula ou ponto) no campo saldo! \n \n" + "Erro: "+ e.getMessage());
//			throw new Exception("Valor inválido!");
		}
		return 0.0;
	}
}
