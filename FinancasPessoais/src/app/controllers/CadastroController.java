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
import jdbc.Dados;

public class CadastroController{
	/**
	 * Possui como atributos elementos declarados no FXML (@FXML), apenas os elementos
	 * controláveis.
	 * É necessário idenficar os atributos no FXML (feito através do SceneBuilder)
	 * e identificar essa classe como Controller do FXML em uso. 
	 */
	private Main main;
	
	@FXML
	private TextField txtUsuario;
	@FXML
	private PasswordField pfSenha;
	@FXML
	private PasswordField pfConfirmaSenha;
	@FXML
	private Button btSalvar;
	@FXML
	private Button btCancelar;
	@FXML
	private TextField txtNascimento;
	
	@FXML
	public void initialize() {
		/**
		 * Método padrão para inicializar o controlador FXML
		 * 
		 * Classes anônimas implementadas para configurar ações para os elementos do
		 * FXML, implementando o método sobrescrito (handle) da classe EventHandle
		 */
		// Botão Cancelar
		btCancelar.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent evt){
				// Chama o método estático que coloca o login na tela
				main.login();
			}
		});
		// Botão salvar
		btSalvar.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent evt){
				// Verifica se os campos foram preenchidos
				if (Verifica.verificaCampos(txtUsuario.getText(), pfSenha.getText(), txtNascimento.getText()) ){
					// Verifica se a senha confirma nos dois campos
					if (Verifica.verificaSenha (pfSenha.getText(), pfConfirmaSenha.getText())) {
						// Verifica a data de nascimento (formato e quantidade de carácteres)
						if (Verifica.verificaNascimento(txtNascimento.getText())){
							// Cria um objeto usuário para gerar a linha no Banco de Dados
							// Usa os dados das caixas de texto
							Usuario user = new Usuario(txtUsuario.getText(), pfSenha.getText(), txtNascimento.getText());
							try {
								// Instancia a classe que contém como atributo a conexão com o Banco de Dados
								// conectando a aplicação com o servidor e executa o método criarUsuario()
								// passando como parâmetros o usuário criado anteriormente
								new Dados().criarUsuario(user);
								Dialogs.showInformationDialog(null, "Usuário Cadastrado!");
							} catch (SQLException e){
								Dialogs.showErrorDialog(null, "Erro no Banco de Dados. \n" + e.getMessage());
								System.out.println(e.getMessage());
							}
							// Após o fim do cadastro, retorna para a tela de Login
							main.login();
						}
					}
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
