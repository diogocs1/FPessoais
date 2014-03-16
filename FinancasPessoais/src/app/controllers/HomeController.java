package app.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import app.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HomeController implements Initializable{
	//Instância Principal
	private Main main;
	//Cadastro de contas
		@FXML
		private Button novaConta;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		novaConta.setOnAction(new EventHandler<ActionEvent>(){
			public void handle (ActionEvent evt){
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/CadastroConta.fxml"));
					AnchorPane novaConta = (AnchorPane) loader.load();
					Stage novaJanela = new Stage();
					Scene cena = new Scene(novaConta);
					novaJanela.setTitle("Nova Conta");
					novaJanela.initModality(Modality.WINDOW_MODAL);
					novaJanela.initOwner(main.getPrimaryStage());
					novaJanela.setScene(cena);
					novaJanela.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
