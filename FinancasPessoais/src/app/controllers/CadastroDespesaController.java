package app.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import app.Main;
import app.logica.Cadastro;
import app.logica.Normaliza;
import app.logica.Verifica;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CadastroDespesaController implements Initializable{
	private Main main;
	
	@FXML
	private TextArea descricao;
	@FXML
	private TextField vencimento;
	@FXML
	private ChoiceBox<String> prioridade;
	@FXML
	private TextField valor;
	
	@FXML
	private ChoiceBox<String> status;
	// Botões
	@FXML
	private Button btSalvar;
	@FXML
	private Button btCancelar;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Ações dos botões
		btSalvar.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				if (Verifica.verificaNascimento(vencimento.getText())){
					Cadastro.cadastraDespesa(
							main.getUser(), 
							descricao.getText(), 
							vencimento.getText(),
							prioridade.getValue(),
							status.getValue(),
							Normaliza.normalizaValor(valor.getText())
							);
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
