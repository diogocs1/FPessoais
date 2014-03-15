package app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;


public class CadastroContaController{
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
	
	@FXML
	public void initialize () {
		
	}
}
