package app.controllers;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import model.Usuario;
import app.Main;
import app.verif.Verificacao;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import jdbc.Dados;

public class CadastroController{
	/*
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
		/*
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
				// Obtém os dados da caixa de texto e senhas
				String nome = txtUsuario.getText();
				String senha = pfSenha.getText();
				String confirmaSenha = pfConfirmaSenha.getText();
				String dnascimento = txtNascimento.getText();
				// Verifica se os campos foram preenchidos
				if (Verificacao.verificaCampos(nome, senha, dnascimento) ){
					// Verifica se a senha confirma nos dois campos
					if (Verificacao.verificaSenha (senha, confirmaSenha)) {
						// Verifica a data de nascimento (formato e quantidade de carácteres)
						if (Verificacao.verificaNascimento(dnascimento)){
							// Cria um objeto usuário para gerar a linha no Banco de Dados
							// Usa os dados das caixas de texto
							Usuario user = new Usuario(nome, senha, dnascimento);
							try {
								// Instancia a classe que contém como atributo a conexão com o Banco de Dados
								// conectando a aplicação com o servidor e executa o método criarUsuario()
								// passando como parâmetros o usuário criado anteriormente
								new Dados().criarUsuario(user);
								JOptionPane.showMessageDialog(null, "Usuário Cadastrado!");
							}catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e){
								JOptionPane.showMessageDialog(null, "Usuário já está cadastrado!");
							} catch (SQLException e){
								JOptionPane.showMessageDialog(null, "Erro no Banco de Dados. \n" + e.getMessage());
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
