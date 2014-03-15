package app.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import app.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import model.Conta;

public class HomeController implements Initializable{
	//Instância Principal
	private Main main;
	//Cadastro de contas
		@FXML
		private TableView<Conta> tabelaContas;
		@FXML
		private Button novaConta;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		novaConta.setOnAction(new EventHandler<ActionEvent>(){
			public void handle (ActionEvent evt){
				
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
