package app.controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import app.logica.CBModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import jdbc.Dados;


public class CadastroContaController{
	@FXML
	private TextField titular;
	@FXML
	private ChoiceBox<String> banco;
	private CBModel bancoModel;
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
	
	public CadastroContaController() {
		bancoModel = new CBModel();
		try {
			bancoModel.setModel(new Dados().getBancos());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		banco.setSelectionModel(bancoModel);
	}
	
	@FXML
	public void initialize () {
		
		
		btSalvar.setOnAction(new EventHandler<ActionEvent>() {
			public void handle (ActionEvent evt) {
				
			}
		});
	}
}
