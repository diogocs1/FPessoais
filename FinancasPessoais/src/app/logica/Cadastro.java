package app.logica;

import java.sql.SQLException;
import java.util.Date;

import app.jdbc.DadosConta;
import app.jdbc.DadosDespesa;
import app.jdbc.DadosPagamento;
import app.jdbc.DadosUsuario;
import app.model.Acao;
import app.model.Conta;
import app.model.Despesa;
import app.model.Pagamento;
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
				Dialogs.showErrorDialog(null, "Problema no banco de dados.\n \n" + e.getMessage());
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
	public static void depositaValor (Conta contaAtual, double valor) throws SQLException, NullPointerException{
		contaAtual.depositar(valor);
		contaAtual.addAcao(new Acao(new Date(), "Valor depositado: "+valor));
		new DadosConta().editaConta(contaAtual, contaAtual);
	}
	public static void sacaValor (Conta contaAtual, double valor) {
		contaAtual.sacar(valor);
		contaAtual.addAcao(new Acao(new Date(), "Valor sacado: "+valor));
		try {
			new DadosConta().editaConta(contaAtual, contaAtual);
		} catch (SQLException | NullPointerException e) {
			Dialogs.showErrorDialog(null, "Não é possível sacar! \n \n" + e.getMessage());
		}
	}
	public static boolean cadastraDespesa (Usuario usuario, String descricao, String vencimento, String prioridade,String status, double valor ){
		if (Verifica.campoVazio(descricao) && Verifica.campoVazio(vencimento) && Verifica.campoVazio(prioridade) && Verifica.campoVazio(valor) && Verifica.campoVazio(status)){
			try {
				Despesa novaDespesa = new Despesa(usuario, descricao, vencimento, prioridade,status, valor);
				new DadosDespesa().cadastraDespesa(novaDespesa);
				return true;
			} catch (SQLException e) {
				Dialogs.showErrorDialog(null, "Problema no banco de dados! \n \n" + e.getMessage());
			} catch (IllegalArgumentException e){
				Dialogs.showErrorDialog(null, e.getMessage());
			}
		}
		return false;
	}
	public static void removeDespesa(Despesa despesa) {
		try {
			new DadosDespesa().removeDespesa(despesa);
			Dialogs.showInformationDialog(null, "Despesa Removida!");
		} catch (SQLException e) {
			Dialogs.showErrorDialog(null, "Problema no banco de dados! \n \n" + e.getMessage());
		}
	}
	public static boolean editaDespesa(Despesa editaDesp, Usuario user,
			String descricao, String vencimento, String prioridade, String status,
			Double valor) {
		if (Verifica.campoVazio(descricao) && Verifica.campoVazio(vencimento)&& Verifica.campoVazio(prioridade) && Verifica.campoVazio(status)){
			Despesa novaDespesa = new Despesa(user, descricao, vencimento, prioridade, status, valor);
			try{
				new DadosDespesa().editaDespesa(editaDesp, novaDespesa);
				return true;
			}catch (SQLException e){
				Dialogs.showWarningDialog(null, "Problema no banco de dados.\n \n" + e.getMessage());
			}
		}else{
			Dialogs.showWarningDialog(null, "Preencha todos os campos!");
		}
		
		return false;
	}
	public static void cadastraPagamento(Conta conta,Despesa despesa, double valor, String data) {
		if (Verifica.campoVazio(data)){
			if (valor == 0){
				valor = despesa.getValor();
			}
			try{
				Pagamento pgto = new Pagamento(despesa, valor, data);
				System.out.println(despesa.getId());
				new DadosPagamento().criaPagamento(pgto);
				Dialogs.showInformationDialog(null, "Pagamento Efetuado!");
			}catch (IllegalArgumentException e){
				Dialogs.showErrorDialog(null, "Não foi possível realizar o pagamento, verifique todos so campos");
			}catch (SQLException e){
				Dialogs.showErrorDialog(null, "Não foi possível salvar! Aqui \n \n" + e.getSQLState());
			}
		}
	}
}
