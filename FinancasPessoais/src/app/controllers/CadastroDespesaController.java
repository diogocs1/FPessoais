package app.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import app.Main;
import app.logica.Cadastro;
import app.logica.Normaliza;
import app.logica.Verifica;
import app.model.Despesa;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialogs;
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
	//Editar
	private Despesa editaDesp;
	private boolean edita;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Valores dos ChoiceBox
		prioridade.setItems(FXCollections.observableArrayList("Importante / Urgente","Importante / Não urgente","Não importante / Urgente", "Não importante / Não urgente"));
		status.setItems(FXCollections.observableArrayList("Falta pagar","Pago"));
		
		//Ações dos botões
		btSalvar.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				boolean cadastro = false;
				if (Verifica.verificaNascimento(vencimento.getText())){
					try{
						if (edita){
							cadastro = Cadastro.editaDespesa(
									editaDesp,
									main.getUser(), 
									descricao.getText(), 
									vencimento.getText(),
									prioridade.getValue(),
									status.getValue(),
									Normaliza.normalizaValor(valor.getText())
									);
						}else{
							cadastro = Cadastro.cadastraDespesa(
									main.getUser(), 
									descricao.getText(), 
									vencimento.getText(),
									prioridade.getValue(),
									status.getValue(),
									Normaliza.normalizaValor(valor.getText())
									);
						}
						if (cadastro){
							main.getControllerHome().atualizaTudo();
							main.getControllerHome().getNovaJanelaDespesas().close();
						}
					}catch (Exception e){
						Dialogs.showErrorDialog(null,"Erro ao Salvar! \n \n"+ e.getMessage());					}
				}
			}
		});
		btCancelar.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				main.getControllerHome().getNovaJanelaDespesas().close();
			}
		});
	}


	public Main getMain() {
		return main;
	}
	public void setMain(Main main) {
		this.main = main;
	}


	public void editaDespesa(Despesa editar) {
		setEditaDesp(editar);
		this.descricao.setText(editar.getDescricao());
		this.vencimento.setText(editar.getVencimento());
		this.prioridade.setValue(editar.getPrioridade());
		this.valor.setText(String.valueOf(editar.getValor()));
		this.status.setValue(editar.getStatus());;
		setEdita(true);
		
	}


	public Despesa getEditaDesp() {
		return editaDesp;
	}


	public void setEditaDesp(Despesa editaDesp) {
		this.editaDesp = editaDesp;
	}


	public boolean isEdita() {
		return edita;
	}


	public void setEdita(boolean edita) {
		this.edita = edita;
	}

}
