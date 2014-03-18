package app.logica;

import java.sql.SQLException;
import java.util.Date;

import app.jdbc.DadosConta;
import app.jdbc.DadosUsuario;
import app.model.Acao;
import app.model.Conta;
import app.model.Usuario;
import javafx.scene.control.Dialogs;

public class Cadastro {
	public static void cadastraUsuario (String usuario, String senha,String confirmaSenha, String nascimento){
		// Verifica se os campos foram preenchidos
		if (Verifica.verificaCampos(usuario, senha, nascimento) ){
			// Verifica se a senha confirma nos dois campos
			if (Verifica.verificaSenha (senha, confirmaSenha)) {
				// Verifica a data de nascimento (formato e quantidade de carácteres)
				if (Verifica.verificaNascimento(nascimento)){
					// Cria um objeto usuário para gerar a linha no Banco de Dados
					// Usa os dados das caixas de texto
					Usuario user = new Usuario(usuario, senha, nascimento);
					try {
						// Instancia a classe que contém como atributo a conexão com o Banco de Dados
						// conectando a aplicação com o servidor e executa o método criarUsuario()
						// passando como parâmetros o usuário criado anteriormente
						new DadosUsuario().criarUsuario(user);
						Dialogs.showInformationDialog(null, "Usuário Cadastrado!");
					} catch (SQLException e){
						Dialogs.showErrorDialog(null, "Erro no Banco de Dados. \n" + e.getMessage());
						System.out.println(e.getMessage());
					}
				}
			}
		}
	}
	public static void cadastraConta (Usuario pessoa, String banco, String conta,String tipo, double saldo){
		if (Verifica.campoVazio(banco) && Verifica.campoVazio(conta)&& Verifica.campoVazio(tipo) && Verifica.campoVazio(saldo)){
			Conta novaConta = new Conta(pessoa, banco, conta, tipo, saldo);
			try{
				new DadosConta().criaConta(novaConta);
			}catch (SQLException e){
				Dialogs.showWarningDialog(null, "Problema no banco de dados.\n \n" + e.getMessage());
			}
		}else{
			Dialogs.showWarningDialog(null, "Preencha todos os campos!");
		}
	}
	public static void editaConta (Conta antigaConta, Usuario pessoa, String banco, String conta,String tipo, double saldo){
		if (Verifica.campoVazio(banco) && Verifica.campoVazio(conta)&& Verifica.campoVazio(tipo) && Verifica.campoVazio(saldo)){
			Conta novaConta = new Conta(pessoa, banco, conta, tipo, saldo);
			// Adiciona a edição ao histórico
			novaConta.addAcao(
					new Acao(new Date(), novaConta.toHistorico())
					);
			try{
				new DadosConta().editaConta(antigaConta, novaConta);
			}catch (SQLException e){
				Dialogs.showWarningDialog(null, "Problema no banco de dados.\n \n" + e.getMessage());
			}
		}else{
			Dialogs.showWarningDialog(null, "Preencha todos os campos!");
		}
	}
	public static void removeConta (Conta conta){
		try {
			new DadosConta().removeConta(conta);
			Dialogs.showInformationDialog(null, "Conta Removida!");
		} catch (SQLException e) {
			Dialogs.showErrorDialog(null, "Problema no banco de dados! \n \n" + e.getMessage());
		}
	}
}
