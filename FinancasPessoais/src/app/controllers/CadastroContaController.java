package app.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import jdbc.Dados;


public class CadastroContaController implements Initializable{
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		
		try {
			ObservableList<String> listaBancos = FXCollections.observableArrayList(new Dados().getBancos());
			banco.setItems(listaBancos);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		btSalvar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent evt) {
				System.out.println("Salvo!");
			}
		});
	}
}
