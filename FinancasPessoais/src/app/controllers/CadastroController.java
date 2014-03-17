package app.controllers;

import app.Main;
import app.logica.Cadastro;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
				Cadastro.cadastraUsuario(txtUsuario.getText(), pfSenha.getText(),pfConfirmaSenha.getText(), txtNascimento.getText());
				// Após o fim do cadastro, retorna para a tela de Login
				main.login();
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
