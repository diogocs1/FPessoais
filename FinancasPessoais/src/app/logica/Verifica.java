package app.logica;

import javax.swing.JOptionPane;

import app.model.Usuario;
import javafx.scene.control.Dialogs;

public class Verifica {
	public static boolean verificaUsuario (Usuario user, String nome, String senha) {
		/**
		 * Recebe como parâmetros um Objeto do tipo usuário, recuperado do banco de dados
		 * o nome e a senha digitados nos campos de login.
		 * Verifica se o nome do usuário combina com a senha digitada e a senha salva no banco de dados
		 * Retorna true ou false
		 */
		boolean vf = false;
		if (user.getNome().equals(nome)){
			if (user.getSenha().equals(senha)) {
				vf = true;
			}else{
				Dialogs.showWarningDialog(null, "Senha Inválida!");
			}
		}else{
			Dialogs.showWarningDialog(null, "Usuário não está cadastrado");
		}
		return vf;
	}
	public static boolean rdfVerifiUsuario (Usuario user, String nome, String nasc) {
		/**
		 * Verifica o nome e a data de nascimento recebidos com parâmetros
		 * e compara com o valor resgatado do banco de dados no objeto "user"
		 */
		boolean vf = false;
		if (user.getNome().equals(nome)){
			if (user.getNascimento().equals(nasc)) {
				vf = true;
			}else{
				Dialogs.showWarningDialog(null, "Data de nascimento inválida");
			}
		}else{
			Dialogs.showWarningDialog(null, "O Usuário não está cadastrado!");
		}
		return vf;
	}
	public static boolean verificaCampos (String nome, String senha, String dnascimento){
		/*
		 * Verifica se os valores passados como parâmetros (valores dos campos) são vazios
		 * retornando true ou false
		 */
		boolean verif = false;
		if (nome.length() == 0 || senha.length() == 0 || dnascimento.length() == 0) JOptionPane.showMessageDialog(null, "Erro de Integridade: Preencha todos os campos!");
		else verif = true;
		return verif;
	}
	public static boolean verificaSenha (String senha, String confirmaSenha) {
		/**
		 * Verifica se a senha dos dois campos confirma
		 * retornando true ou false
		 */
		boolean verif = false;
		if (senha.equals(confirmaSenha)) verif = true;
		else JOptionPane.showMessageDialog(null, "Senha não confirmada!");
		return verif;
	}
	public static boolean verificaNascimento (String nascimento) {
		/**
		 * Verifica formato e quantidade de carácteres do campo data de nascimento
		 * retorna true ou false para a validação
		 */
		String[] nasc = nascimento.split("/");
		boolean verifi = false;
		if (nasc.length == 3){
			verifi = true ;
		}else{
			Dialogs.showErrorDialog(null, "Data inválida");
		}
		return verifi;
	}
	public static boolean campoVazio (String campo){
		boolean verifi = false;
		if (!campo.isEmpty()){
			verifi = true;
		}
		return verifi;
	}
	public static boolean campoVazio (double campo){
		boolean verifi = false;
		if (!String.valueOf(campo).isEmpty()){
			verifi = true;
		}
		return verifi;
	}
	public static boolean validaData (String sdata) throws IllegalArgumentException{
		String[] formataVencimento = sdata.split("/");
		int[] data = new int[3];
		for (int i = 0; i < formataVencimento.length; i++){
			data[i] = Integer.parseInt(formataVencimento[i]);
		}
		if (data[0] <= 31 && data[1] <= 12 && data[2] > 1900){
			if (data[1] == 2 && data[0] > 29){
				throw new IllegalArgumentException("Data Inválida");
			}else if (data[0] <= 30 && (data[1] % 2 == 0 && data[1] < 7 || data[1] % 2 != 0 && data[1] > 8) ){
				return true;
			}else if (data[0] <= 31 && ! (data[1] % 2 == 0 && data[1] < 7 || data[1] % 2 != 0 && data[1] > 8) ){
				return true;
			}else{
				throw new IllegalArgumentException("Data Inválida");
			}
		}else{
			throw new IllegalArgumentException("Data Inválida!");
		}
	}
}
