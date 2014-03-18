package app.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import app.Main;
import app.jdbc.DadosConta;
import app.logica.Cadastro;
import app.logica.Normaliza;
import app.model.Conta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.TextField;


public class CadastroContaController implements Initializable{
	
	private Main main;
	
	@FXML
	private TextField titular;
	@FXML
	private ChoiceBox<String> banco;
	@FXML
	private ChoiceBox<String> tipo;
	@FXML
	private TextField numero;
	@FXML
	private TextField saldo;
	@FXML
	private Button btSalvar;
	@FXML
	private Button btCancelar;
	// Edição de conta
	private boolean edita = false;
	private Conta editaConta;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		// Pega o usuário atual
		// Preenche os ChoiceBox
		try {
			ObservableList<String> listaBancos = FXCollections.observableArrayList(new DadosConta().getBancos());
			banco.setItems(listaBancos);
			
		} catch (SQLException e) {
			Dialogs.showErrorDialog(null, "Erro no Banco de dados \n \n" + e.getMessage());
		}
		tipo.setItems(FXCollections.observableArrayList("Conta Corrente","Conta Poupança", "Conta FácilS"));
		// FIM

		// Ações dos botões
		btSalvar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent evt) {
				try {
					if (edita){
						Cadastro.editaConta(editaConta, main.getUser(), banco.getValue(), numero.getText(), tipo.getValue(), Normaliza.normalizaValor(saldo.getText()));
					}else{
						Cadastro.cadastraConta(main.getUser(), banco.getValue(), numero.getText(), tipo.getValue(), Normaliza.normalizaValor(saldo.getText()));
					}
					main.getControllerHome().atualizaTabela();
					main.getControllerHome().getNovaJanelaConta().close();
				} catch (Exception e) {
					Dialogs.showErrorDialog(null, e.getMessage());
				}
				
			}
		});
		
		btCancelar.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent evt){
				main.getControllerHome().getNovaJanelaConta().close();
			}
		});
		// FIM
	}
	public void editaConta (Conta conta){
		setEditaConta(conta);
		this.titular.setText(conta.getPessoa().getNome());
		this.banco.setValue(conta.getBanco());
		this.tipo.setValue(conta.getTipo());
		this.numero.setText(conta.getConta());
		this.saldo.setText(String.valueOf(conta.getSaldo()));
		setEdita(true);
	}
	
	public void setMain(Main main) {
		this.main = main;
	}
	public boolean isEdita() {
		return edita;
	}
	public void setEdita(boolean edita) {
		this.edita = edita;
	}
	public Conta getEditaConta() {
		return editaConta;
	}
	public void setEditaConta(Conta editaConta) {
		this.editaConta = editaConta;
	}
}
