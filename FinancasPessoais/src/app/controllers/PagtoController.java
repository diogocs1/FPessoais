package app.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import app.Main;
import app.jdbc.DadosConta;
import app.logica.Cadastro;
import app.logica.Normaliza;
import app.model.Conta;
import app.model.Despesa;
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

public class PagtoController implements Initializable{
	private Main main;
	private ArrayList<Conta> contas;
	private ObservableList<String> listaContas;
	private Despesa despesa;

	@FXML
	private ChoiceBox<String> conta;
	@FXML
	private TextField valor;
	@FXML
	private TextField data;
	@FXML
	private Button btSalvar;
	@FXML
	private Button btCancelar;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		preencheContas();
		
		btSalvar.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				try{
					if (valor.getText().length() == 0){
						valor.setText("0");
					}
					int contaSelecionada = conta.getSelectionModel().getSelectedIndex();
					if (conta.getSelectionModel().getSelectedItem().equals("Outro")){
						Cadastro.cadastraPagamento(
								null,
								despesa,
								Normaliza.normalizaValor(valor.getText()),
								data.getText()
								);
					}else{
						Cadastro.cadastraPagamento(
								contas.get(contaSelecionada),
								despesa,
								Normaliza.normalizaValor(valor.getText()),
								data.getText()
								);
					}
					main.getControllerHome().atualizaTudo();
					main.getControllerHome().getNovaJanelaPagto().close();
				}catch (NullPointerException e) {
					Dialogs.showWarningDialog(null, "Selecione uma conta!");
					e.printStackTrace();
				}
			}
			
		});		
	}
	
	public void preencheContas (){
		try {
			contas = new DadosConta().getContas();
			listaContas = FXCollections.observableArrayList();
			for (Conta conta: contas){
				listaContas.add(conta.toString());
			}
			listaContas.add("Outro");
			conta.setItems(listaContas);
		} catch (SQLException e) {
			Dialogs.showErrorDialog(null, "Não é possível obter suas contas! \n \n" + e.getMessage());
		}
		
	}
	
	
	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public Despesa getDespesa() {
		return despesa;
	}

	public void setDespesa(Despesa despesa) {
		this.despesa = despesa;
	}

	
}
