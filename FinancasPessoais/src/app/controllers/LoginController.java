package app.controllers;

import java.sql.SQLException;

import model.Usuario;
import app.Main;
import app.logica.Verifica;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Dialogs;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import jdbc.DadosLogin;

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
	// Elementos do VBox (Redefinição de Senha):
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
					Usuario user = new DadosLogin().getUsuario(txUsuario.getText());
					// Verifica se o usuário digitou a senha correta, à partir do método que compara o valor dos campos com o valor do atributo do objeto pesquisado
					if (Verifica.verificaUsuario (user, txUsuario.getText(), txSenha.getText())){
						main.inicio();
						main.setUser(user);
					}
				} catch (SQLException e) {
					Dialogs.showErrorDialog(null, "Problema no Banco de dados:\n \n" + e.getMessage());
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
						Usuario user = new DadosLogin().getUsuario(rdfUsuario.getText());
						// Verifica se os valores passados são iguais aos valores salvos no Banco de dados
						if (Verifica.rdfVerifiUsuario (user, rdfUsuario.getText(), rdfNascimento.getText() ) ){
							// Grava a nova senha no Banco de Dados
							new DadosLogin().redefinirSenha(user.getNome(), rdfSenha.getText());
							Dialogs.showInformationDialog(null, "Senha redefinida!");
							redefinir.setVisible(false);
						}
					} catch (SQLException e) {
						Dialogs.showErrorDialog(null, "Problema no Banco de dados \n \n" + e.getMessage());
					}
				}
			});
	}
	public Main getMain() {
		return main;
	}
	public void setMain(Main main) {
		this.main = main;
	}

}
