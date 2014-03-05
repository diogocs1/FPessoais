package app.controllers;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import model.Usuario;
import app.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Dialogs;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import jdbc.Dados;

public class LoginController{
	/**
	 * Possui como atributos elementos declarados no FXML (@FXML), apenas os elementos
	 * controláveis.
	 * É necessário idenficar os atributos no FXML (feito através do SceneBuilder)
	 * e identificar essa classe como Controller do FXML em uso. 
	 */
	private Main main;

	@FXML
	private TextField txUsuario;
	@FXML
	private PasswordField txSenha;
	@FXML
	private Button btEntrar;
	@FXML
	private Button btCadastrar;
	@FXML
	private Button btRedefinir;
	@FXML
	private VBox redefinir;
	// Elementos do VBox:
		@FXML
		private TextField rdfUsuario;
		@FXML
		private TextField rdfNascimento;
		@FXML
		private PasswordField rdfSenha;
		@FXML
		private Button btSalvar;
	@FXML
	public void initialize() {
		// Esconde o painel de redefinição de senha
		redefinir.setVisible(false);
		// Eventos de botão
		// Botão Cadastrar
		btCadastrar.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent evt){
				//Mostra a tela de cadastro
				main.cadastro();
			}
		});
		// Botão Entrar:
		btEntrar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent evt) {
				try {
					//Obtém do banco de dados os atributos do usuário através do método getUsuario()
					//a partir do nome de usuário digitado na caixa de texto que é passado comoparâmetro
					Usuario user = new Dados().getUsuario(txUsuario.getText());
					// Verifica se o usuário digitou a senha correta, à partir do método que compara o valor dos campos com o valor do atributo do objeto pesquisado
					if (verificaUsuario (user, txUsuario.getText(), txSenha.getText())){
//						Main.inicio();
					}
				} catch (SQLException e) {
					Dialogs.showErrorDialog(null, "Problema no Banco de dados: \n" + e.getMessage());
					System.out.println(e.getMessage());
				}
			}
		});
		// Botão "Esqueci a senha"
		btRedefinir.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent evt) {
				// Mostra a caixa (VBox) de redefinição de senha
				redefinir.setVisible(true);
			}
		});
		// Redefinição de senha
			btSalvar.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle (ActionEvent evt) {
					try {
						// Consulta o usuário a partir do texto digitado
						Usuario user = new Dados().getUsuario(rdfUsuario.getText());
						// Verifica se os valores passados são iguais aos valores salvos no Banco de dados
						if (rdfVerifiUsuario (user, rdfUsuario.getText(), rdfNascimento.getText() ) ){
							// Grava a nova senha no Banco de Dados
							new Dados().redefinirSenha(user.getNome(), rdfSenha.getText());
							Dialogs.showInformationDialog(null, "Senha redefinida!");
							redefinir.setVisible(false);
						}
					} catch (SQLException e) {
						Dialogs.showErrorDialog(null, "Erro no Banco de dados \n \n" + e.getMessage());
					}
				}
			});
	}
	
	
	
	public boolean verificaUsuario (Usuario user, String nome, String senha) {
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
			Dialogs.showWarningDialog(null, "Usu�rio n�o est� cadastrado");
		}
		return vf;
	}
	public boolean rdfVerifiUsuario (Usuario user, String nome, String nasc) {
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
	public Main getMain() {
		return main;
	}
	public void setMain(Main main) {
		this.main = main;
	}

}
